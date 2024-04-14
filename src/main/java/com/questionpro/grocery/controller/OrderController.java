package com.questionpro.grocery.controller;

import com.questionpro.grocery.dto.GroceryDTO;
import com.questionpro.grocery.entity.Order;
import com.questionpro.grocery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add-order")
    ResponseEntity<Order> addOrder(@RequestBody GroceryDTO groceryDTO){
        return ResponseEntity.ok(orderService.addOrder(groceryDTO));
    }

    @GetMapping("/get-orders/{userId}")
    ResponseEntity<List<Order>> findOrdersByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.findOrdersByUserId(userId));
    }
}
