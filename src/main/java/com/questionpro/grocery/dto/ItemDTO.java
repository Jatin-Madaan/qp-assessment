package com.questionpro.grocery.dto;

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
public class ItemDTO {
	private Long itemId;
	
	private String name;
	
	private Double price;
	
	private String brand;
	
	private Long quantity;
}
