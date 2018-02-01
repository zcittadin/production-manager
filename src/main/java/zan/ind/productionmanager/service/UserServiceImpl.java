package zan.ind.productionmanager.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import zan.ind.productionmanager.dto.UserDTO;
import zan.ind.productionmanager.model.Role;
import zan.ind.productionmanager.model.User;
import zan.ind.productionmanager.repository.RoleRepository;
import zan.ind.productionmanager.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		user.setActive(userDTO.getActive());
		Role userRole = roleRepository.findByRole(userDTO.getRoles());
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}