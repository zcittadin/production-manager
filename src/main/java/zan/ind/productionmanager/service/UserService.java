package zan.ind.productionmanager.service;

import zan.ind.productionmanager.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);
}
