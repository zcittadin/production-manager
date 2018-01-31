package zan.ind.productionmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zan.ind.productionmanager.model.User;
import zan.ind.productionmanager.service.UserService;

@RestController
@RequestMapping("/product-manager")
public class RegistrationController {

	@Autowired
	UserService userService;

	@PostMapping("/registration")
	public ResponseEntity<User> createNewUser(@Valid @RequestBody User user) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			return ResponseEntity.status(HttpStatus.IM_USED).build();
		}
		userService.saveUser(user);
		return ResponseEntity.ok().body(user);
	}

}
