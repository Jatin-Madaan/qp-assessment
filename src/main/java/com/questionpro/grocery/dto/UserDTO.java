package com.questionpro.grocery.dto;

import com.questionpro.grocery.entity.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
	private Long userId;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private Type type;
}
