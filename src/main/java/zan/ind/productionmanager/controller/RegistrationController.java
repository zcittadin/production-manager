package zan.ind.productionmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
