package com.skilldistillery.attyManage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.attyManage.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User getUserById(int id);
	User findByUsername(String username);

}
