package com.questionpro.grocery.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questionpro.grocery.dto.UserDTO;
import com.questionpro.grocery.service.UserService;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add-user")
	@ApiOperation("add user")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
		return ResponseEntity.ok(userService.addUser(user));
	}
	
	@GetMapping("/get-all-users")
	@ApiOperation("get user")
	public ResponseEntity<List<UserDTO>>getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}
	
}
