package com.skilldistillery.attyManage.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.attyManage.entities.User;
import com.skilldistillery.attyManage.services.AuthService;


@RestController
@CrossOrigin({"*", "http://localhost/"})
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("register")
	public User register(@RequestBody User user, HttpServletResponse res) {
	  if (user == null) {
	     res.setStatus(400);
	     return null;
	  }
	  user = authService.register(user);
	  return user;
	}
	
	/**
	 * @param principal: cause Spring Security to authenticate the client's credentials
	 * according to the configuration in our security configuration class.
	 * @return
	 * If the credentials sent by the client are authentic, the method will execute.
If invalid credentials are sent, Spring Security will skip the method and automatically return 401 Unauthorized.
If no credentials are sent, no Principal will be created.
	 */
	 
	@GetMapping("authenticate")
	//Injecting a Principal in our controller method will 
	public User authenticate(Principal principal, HttpServletResponse res) {
	  if (principal == null) { // no Authorization header sent
	     res.setStatus(401);
	     res.setHeader("WWW-Authenticate", "Basic");
	     return null;
	  }
	  return authService.getUserByUsername(principal.getName());
	}

}
