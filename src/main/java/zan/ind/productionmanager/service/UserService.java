package zan.ind.productionmanager.service;

import java.util.List;

import zan.ind.productionmanager.dto.UserDTO;
import zan.ind.productionmanager.model.User;

public interface UserService {
	
	public List<User> findAll();

	public User findUserByEmail(String email);

	public void saveUser(UserDTO userDTO);
}
