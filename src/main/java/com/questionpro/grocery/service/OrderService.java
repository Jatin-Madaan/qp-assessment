package com.questionpro.grocery.service;

import ch.qos.logback.classic.Logger;
import com.questionpro.grocery.dto.GroceryDTO;
import com.questionpro.grocery.dto.OrderRequestDTO;
import com.questionpro.grocery.entity.*;
import com.questionpro.grocery.exception.ItemException;
import com.questionpro.grocery.exception.UserException;
import com.questionpro.grocery.repository.GroceryRepository;
import com.questionpro.grocery.repository.ItemRepository;
import com.questionpro.grocery.repository.OrderRepository;
import com.questionpro.grocery.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryRepository groceryRepository;

    private ModelMapper modelMapper;
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);

    public OrderService() {
        this.modelMapper = new ModelMapper();
    }

    public Order addOrder(GroceryDTO groceryDTO){
        Type userType = userRepository.findUserTypeById(groceryDTO.getUserId());
        Set<Grocery> orderedGroceries = new LinkedHashSet<>();
        User user;
        if(userType == null || userType.equals("")){
            throw new UserException("User does not exists");
        }else if(userType.equals(Type.ADMIN)){
            throw new UserException("Access Denied !!!");
        }else{
            user = userRepository.getById(groceryDTO.getUserId());
        }

        Order order = new Order();
        Double orderTotal = 0D;

        for(OrderRequestDTO dto: groceryDTO.getOrders()){
            Optional<Item> item = itemRepository.findById(dto.getItemId());
            if(item.isPresent()){
                Item itemTmp = item.get();
                if(itemTmp.getQuantity() < dto.getQuantity()){
                    throw new ItemException("Quantity exceeds the available items");
                }else{
                    itemTmp.setQuantity(itemTmp.getQuantity() - dto.getQuantity());

                    Grocery grocery = new Grocery();
                    grocery.setItem(itemTmp);
                    grocery.setQuantity(dto.getQuantity());
                    //Grocery groceryOut = groceryRepository.save(grocery);

                    orderTotal += (dto.getQuantity() * itemTmp.getPrice());

                    orderedGroceries.add(grocery);
                }
            }else{
                throw new ItemException("Item not present with this Id: " + dto.getItemId());
            }
        }

        order.setUser(user);
        order.setNetBill(orderTotal);
        Order orderOut = orderRepository.save(order);
        Set<Grocery> groceries = orderedGroceries.stream().map(grocery -> {
            grocery.setOrders(orderOut);
            return groceryRepository.save(grocery);
        }).collect(Collectors.toSet());
        orderOut.setOrderedGroceries(groceries);
        return orderOut;
    }

    public List<Order> findOrdersByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Order> orders;
        if(!user.isPresent()){
            throw new UserException("User does not exists");
        }else {
            orders = orderRepository.findOrdersByUserId(userId);
        }
        return orders;
    }
}
