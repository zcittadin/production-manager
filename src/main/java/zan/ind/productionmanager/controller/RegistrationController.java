package zan.ind.productionmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zan.ind.productionmanager.dto.UserDTO;
import zan.ind.productionmanager.model.User;
import zan.ind.productionmanager.service.UserService;

@RestController
@RequestMapping("/product-manager")
public class RegistrationController {

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/users")
	public List<UserDTO> getAllUser() {
		List<User> users = userService.findAll();
		List<UserDTO> dtos = new ArrayList<>();
		
		users.forEach(u -> {
			UserDTO dto = modelMapper.map(u, UserDTO.class);
			dto.setPassword(null);
			dtos.add(dto);
		});
		return dtos;
	}

	@PostMapping("/registration")
	public ResponseEntity<UserDTO> createNewUser(@Valid @RequestBody UserDTO userDTO) {
		User userExists = userService.findUserByEmail(userDTO.getEmail());
		if (userExists != null) {
			return ResponseEntity.status(HttpStatus.IM_USED).build();
		}
		userService.saveUser(userDTO);
		return ResponseEntity.ok().body(userDTO);
	}

}
