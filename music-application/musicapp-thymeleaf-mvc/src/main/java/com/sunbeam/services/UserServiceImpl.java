package com.sunbeam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.daos.UserDao;
import com.sunbeam.entities.User;

@Transactional
@Service
public class UserServiceImpl {
	@Autowired
	private UserDao userDao;
	
	public User findUserByEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}
	
	public User findUserByEmailAndPassword(String email, String password) {
		User user = userDao.findByEmail(email);
		if(user != null && user.getPassword().equals(password))
			return user;
		return null;
	}
	
	public User saveUser(User user) {
		return userDao.save(user);
	}
}
