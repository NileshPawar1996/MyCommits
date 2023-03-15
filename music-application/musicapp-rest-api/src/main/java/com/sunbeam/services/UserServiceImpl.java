package com.sunbeam.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.exceptions.*;
import com.sunbeam.pojos.User;
import com.sunbeam.repos.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public String saveUser(User user) {
		User persistentUser = userRepo.save(user);
		return "User registration success! ID: " + persistentUser.getId();
	}

	@Override
	public User findUserById(Long id) {
		User user = userRepo.findById(id).orElseThrow(() -> new CustomCentralException("User Id Invalid!"));
		// Setting password null so password won't be exposed
		user.setPassword(null);
		return user;
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepo.findByEmail(email);
		// Setting password null so password won't be exposed
		user.setPassword(null);
		return user;
	}

	@Override
	public User updateUser(Long id, User user) {
		User persistentUser = userRepo.findById(id).orElseThrow(() -> new CustomCentralException("User Id Invalid!"));
		persistentUser.setEmail(user.getEmail());
		persistentUser.setFirstName(user.getFirstName());
		persistentUser.setLastName(user.getLastName());
		persistentUser.setAddress(user.getAddress());
		persistentUser.setPhone(user.getPhone());
		User updatedUser = userRepo.save(persistentUser);
		// Setting password null so password won't be exposed
		updatedUser.setPassword(null);
		return updatedUser;
	}
}