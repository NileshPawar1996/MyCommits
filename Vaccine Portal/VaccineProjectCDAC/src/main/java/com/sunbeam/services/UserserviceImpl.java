package com.sunbeam.services;



import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.DTO.Credential;
import com.sunbeam.DTO.DTOEntityConverter;
import com.sunbeam.DTO.UserDto;
import com.sunbeam.daos.UserDao;
import com.sunbeam.entities.Role;
import com.sunbeam.entities.User;
@Transactional
@Service
public class UserserviceImpl {
//now current service
	@Autowired
	private UserDao userdao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DTOEntityConverter dtoconverter;
	
	public User findUserById(int id)
	{
		User user=userdao.findById(id);
		return user;
	}
	
	
	public User findUserBymobileNumber(String mobilenumber)
	{
		User user=userdao.findByMobileNumber(mobilenumber);
		return user;
	}
	
	//  email       | password       | password
	
	//sign in processs
	/*
	public UserDto findUserByEmailAndPassword(Credential cred)
	{
		User dbuser=userdao.findByEmail(cred.getEmail());
		String rawPassword= cred.getPassword();
		if(dbuser != null && passwordEncoder.matches(rawPassword, dbuser.getPassword()))
		{
			UserDto result= dtoconverter.toUserDTO(dbuser);
		     result.setPassword("***********");
		  
		     return result;
		     
	}
		return null;
	}	
*/
	public UserDto findUserBymobilenumberAndPassword(Credential cred)
	{
		String mobile = cred.getMobileNumber();
		System.out.println("Mobile number :" + mobile);
		User dbuser=findUserBymobileNumber(cred.getMobileNumber());
		System.out.println("dbuser :" +dbuser);
		String rawPassword= cred.getPassword();
		if(dbuser != null && passwordEncoder.matches(rawPassword, dbuser.getPassword()))
		{
			UserDto result= dtoconverter.toUserDTO(dbuser);
					result.setPassword("***********");
		  
		     return result;
		     
	}
		return null;
	}	

	
	
	//signup process
	

	public Map<String, Object> SaveUser(UserDto userDto) {
		String rawPassword= userDto.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		userDto.setPassword(encPassword);
		userDto.setRole(Role.CITIZEN);
		User user= dtoconverter.toUserEntity(userDto);
		user=userdao.save(user);
		return Collections.singletonMap("inserted id", user.getId());
		
	}

	public User addAdmin(User admin) {
		
		return userdao.save(admin);
				
	}
	
}
