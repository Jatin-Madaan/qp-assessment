package com.questionpro.grocery.entity;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="order_id")
	private Long orderId;
	

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();
	
	@OneToMany(mappedBy="orders")
	private Set<Grocery> orderedGroceries;

	private Double netBill;

	@ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id", nullable=false)
	@JsonIgnore
	private User user;

}
