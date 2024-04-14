package com.questionpro.grocery.entity;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="username")
	private String username;
	
	private String password;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_type")
	private Type type;
	
	@OneToMany(mappedBy="user")
	private Set<Order> orders;
	
}
