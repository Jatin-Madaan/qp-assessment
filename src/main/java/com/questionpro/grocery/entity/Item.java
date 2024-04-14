package com.questionpro.grocery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="item_id")
	private Long itemId;
	
	private String name;
	
	private Double price;
	
	private String brand;
	
	private Long quantity;

	
	@OneToOne(mappedBy = "item")
	@JsonIgnore
	private Grocery grocery;
}
