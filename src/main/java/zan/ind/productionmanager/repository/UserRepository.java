package zan.ind.productionmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zan.ind.productionmanager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}