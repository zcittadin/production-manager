package zan.ind.productionmanager.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import zan.ind.productionmanager.model.User;
import zan.ind.productionmanager.service.UserService;

@Service
public class TokenAuthenticationService {

	@Autowired
	private UserService userService;

	static UserService service;

	// EXPIRATION_TIME = 10 dias
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MySecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	@PostConstruct
	private void init() {
		service = this.userService;
	}

	static void addAuthentication(HttpServletResponse response, String userEmail) {

		User user = service.findUserByEmail(userEmail);

		Gson gson = new Gson();

		List<String> userRoles = new ArrayList<>();
		user.getRoles().forEach(ur -> {
			userRoles.add(ur.getRole());
		});

		String roles = gson.toJson(userRoles);

		Claims claims = Jwts.claims().setSubject(userEmail);
		claims.put("userId", user.getId());
		claims.put("role", roles);

		String JWT = Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		try {
			response.getWriter().print(JWT);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			String userName = claims.getSubject();
			Date expirationTime = claims.getExpiration();

			if (expirationTime.compareTo(new Date()) < 0) {
				return null;
			}

			if (userName != null) {
				return new UsernamePasswordAuthenticationToken(userName, null, Collections.emptyList());
			}
		}
		return null;
	}

}