package com.skilldistillery.attyManage.services;

import com.skilldistillery.attyManage.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);
}
