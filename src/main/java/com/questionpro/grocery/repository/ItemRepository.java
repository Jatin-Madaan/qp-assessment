package com.questionpro.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.questionpro.grocery.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
}
