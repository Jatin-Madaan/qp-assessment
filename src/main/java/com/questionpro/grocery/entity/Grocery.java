package com.questionpro.grocery.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grocery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="grocery_id")
	private Long groceryId;
	
	@OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
	private Item item;
	
	private Long quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
	@JsonIgnore
	private Order orders;
	
}
