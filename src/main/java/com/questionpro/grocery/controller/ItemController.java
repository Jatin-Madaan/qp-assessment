package com.questionpro.grocery.controller;

import com.questionpro.grocery.dto.ItemDTO;
import com.questionpro.grocery.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/get-all-items")
    public ResponseEntity<List<ItemDTO>> findAllItems(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(itemService.findAllItems(userId));
    }

    @GetMapping("/get-all-available-items")
    public ResponseEntity<List<ItemDTO>> findAllAvailableItems(){
        return ResponseEntity.ok(itemService.findAllAvailableItems());
    }

    @PostMapping("/add-item")
    public ResponseEntity<ItemDTO> addItem(@RequestParam("userId") Long userId,@RequestBody ItemDTO itemDTO){
        return ResponseEntity.ok(itemService.addItem(userId, itemDTO));
    }

    @GetMapping("/get-item/{itemId}")
    public ResponseEntity<ItemDTO> findItemById(@RequestParam("userId") Long userId, @PathVariable Long itemId){
        return ResponseEntity.ok(itemService.findItemById(userId, itemId));
    }

    @PutMapping("/edit-item")
    public ResponseEntity<ItemDTO> updateItem(@RequestParam("userId") Long userId, @RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(itemService.updateItem(userId, itemDTO));
    }

    @PatchMapping("/update-inventory")
    public ResponseEntity<ItemDTO> updateInventory(@RequestParam("userId") Long userId, @RequestParam("id") Long id,
                        @RequestParam("quantity") Long quantity) {
        return ResponseEntity.ok(itemService.updateInventory(userId, id, quantity));
    }

    @DeleteMapping("/remove-item/{itemId}")
    public ResponseEntity<ItemDTO> deleteItemById(@RequestParam("userId") Long userId, @PathVariable Long itemId){
        return ResponseEntity.ok(itemService.deleteItemById(userId, itemId));
    }

}
