package com.cgtest.registration.service;

import java.util.List;

import com.cgtest.registration.model.User;
public interface UserManager {

	public boolean exists(User u);
	
	public void save(User user);
	
	public List<User> getUsers();
	
	public User loadById(String id);
	
	public User getUserByUsername(String username);
}