package com.cgtest.registration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cgtest.registration.dao.UserDao;
import com.cgtest.registration.model.User;
import com.cgtest.registration.service.UserManager;

@Component("userManager")
@Transactional
public class UserManagerImpl implements UserManager {
	
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public boolean exists(User u) {
		return userDao.checkUserExistsWithName(u.getUsername());
		
	}
	
	/* (non-Javadoc)
	 * @see com.bjsxt.registration.service.impl.UserManager#add(com.bjsxt.registration.model.User)
	 */
	public void save(User u){
		userDao.save(u);
	}
	
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return this.userDao.getUsers();
	}

	public User loadById(String id) {
		// TODO Auto-generated method stub
		return this.userDao.loadById(id);
	}
	
	public User getUserByUsername(String username){
		return userDao.getUserByUsername(username);
	}
}
