package com.questionpro.grocery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionpro.grocery.dto.UserDTO;
import com.questionpro.grocery.entity.User;
import com.questionpro.grocery.repository.UserRepository;

import ch.qos.logback.classic.Logger;

import java.util.logging.*; 

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
    private ModelMapper modelMapper;
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);
    
	public UserService() {
		this.modelMapper = new ModelMapper();
	}
    
	public UserDTO addUser(UserDTO user) {
		LOGGER.info("user:::::" + user);
		
		User usr = modelMapper.map(user, User.class);
		usr = userRepository.save(usr);
		return modelMapper.map(usr, UserDTO.class);
	}
	
	public List<UserDTO> getUsers() {
		List<UserDTO> users = userRepository.findAll().stream()
			.map(usr -> modelMapper.map(usr, UserDTO.class))
			.collect(Collectors.toList());
	
		return users;
	}
	
	public UserDTO findById(Long id) {
		UserDTO user = modelMapper.map(userRepository.findById(id), UserDTO.class);
	
		return user;
	}



}
