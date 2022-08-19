package com.application.blog.service;

import java.util.List;

import com.application.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
    UserDto getUserById(Integer usrId);
    
    List<UserDto> getAllUsers();
    
    void deleteUser(Integer userId);
}
