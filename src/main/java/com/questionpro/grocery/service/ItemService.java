package com.questionpro.grocery.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.questionpro.grocery.entity.Type;
import com.questionpro.grocery.entity.User;
import com.questionpro.grocery.exception.UserException;
import com.questionpro.grocery.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.questionpro.grocery.dto.ItemDTO;
import com.questionpro.grocery.entity.Item;
import com.questionpro.grocery.exception.ItemException;
import com.questionpro.grocery.repository.ItemRepository;


import ch.qos.logback.classic.Logger;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;
	
    private ModelMapper modelMapper;
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);
    
    public ItemService() {
    	this.modelMapper = new ModelMapper();
    }
    
    public ItemDTO addItem(Long userId, ItemDTO itemDTO) {
		Type type = userRepository.findUserTypeById(userId);
		if(type == null || type.equals(""))
			throw new UserException("Invalid Credentials!!");
		if(type.equals(Type.USER))
			throw new UserException("Access Denied!!");

		Item item = modelMapper.map(itemDTO, Item.class);
    	ItemDTO dto = modelMapper.map(itemRepository.save(item), ItemDTO.class);
		return dto;
    }
    
    public ItemDTO findItemById(Long userId, Long itemId) {
		Type type = userRepository.findUserTypeById(userId);
		if(type == null || type.equals(""))
			throw new UserException("Invalid Credentials!!");
		if(type.equals(Type.USER))
			throw new UserException("Access Denied!!");

    	Optional<Item> item = itemRepository.findById(itemId);
    	if(item.isPresent()) {
    		return modelMapper.map(item.get(), ItemDTO.class);
    	}else {
    		throw new ItemException("No Such Item Exists with ID:" + itemId);
    	}
    }
    
    public ItemDTO updateItem(Long userId, ItemDTO itemDTO) {
		Type type = userRepository.findUserTypeById(userId);
		if(type == null || type.equals(""))
			throw new UserException("Invalid Credentials!!");
		if(type.equals(Type.USER))
			throw new UserException("Access Denied!!");

		Optional<Item> item = itemRepository.findById(itemDTO.getItemId());
		if(item.isPresent()) {
			Item itemObj = item.get();
			itemObj.setBrand(itemDTO.getBrand());
			itemObj.setName(itemDTO.getName());
			itemObj.setPrice(itemDTO.getPrice());
			itemObj.setQuantity(itemDTO.getQuantity());
			return modelMapper.map(itemRepository.save(itemObj), ItemDTO.class);
		}else {
			throw new ItemException("No Such Item Exists with ID:" + itemDTO.getItemId());
		}
    }

	public ItemDTO updateInventory(Long userId, Long id, Long quantity) {
		Type type = userRepository.findUserTypeById(userId);
		if(type == null || type.equals(""))
			throw new UserException("Invalid Credentials!!");
		if(type.equals(Type.USER))
			throw new UserException("Access Denied!!");

		Optional<Item> item = itemRepository.findById(id);
		if(item.isPresent()) {
			Item itemObj = item.get();
			itemObj.setQuantity(quantity);
			return modelMapper.map(itemRepository.save(itemObj), ItemDTO.class);
		}else {
			throw new ItemException("No Such Item Exists with ID:" + id);
		}
	}

	public ItemDTO deleteItemById(Long userId, Long id){
		Type type = userRepository.findUserTypeById(userId);
		if(type == null || type.equals(""))
			throw new UserException("Invalid Credentials!!");
		if(type.equals(Type.USER))
			throw new UserException("Access Denied!!");

		Optional<Item> item = itemRepository.findById(id);
		if(item.isPresent()) {
			ItemDTO dto = modelMapper.map(item.get(), ItemDTO.class);
			itemRepository.delete(item.get());
			return dto;
		}else {
			throw new ItemException("No Such Item Exists with ID:" + id);
		}
	}

	public List<ItemDTO> findAllItems(Long userId){
		Type type = userRepository.findUserTypeById(userId);
		if(type == null || type.equals(""))
			throw new UserException("Invalid Credentials!!");
		if(type.equals(Type.USER))
			throw new UserException("Access Denied!!");

		return itemRepository.findAll().stream().map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());
	}

	public List<ItemDTO> findAllAvailableItems(){
		return itemRepository.findAll().stream().filter(item -> item.getQuantity() > 0).map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());
	}
}
