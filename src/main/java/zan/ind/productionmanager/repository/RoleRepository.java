package zan.ind.productionmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zan.ind.productionmanager.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
