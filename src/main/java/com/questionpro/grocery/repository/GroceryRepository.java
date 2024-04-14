package com.questionpro.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.questionpro.grocery.entity.Grocery;

public interface GroceryRepository extends JpaRepository<Grocery, Long> {
}
