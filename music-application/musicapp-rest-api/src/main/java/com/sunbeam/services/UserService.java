package com.sunbeam.services;

import com.sunbeam.pojos.User;

public interface UserService {

	String saveUser(User user);

	User findUserById(Long id);

	User findByEmail(String email);

	User updateUser(Long id, User user);

}