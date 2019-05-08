package com.springboot.example.store.controllers;

import com.springboot.example.store.entities.Item;
import com.springboot.example.store.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemsController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public List getAllItems(){
        return itemService.findAllItems();
    }

    @GetMapping("/items/{id}")
    public Optional<Item> getItem(@PathVariable long id){
        return itemService.findItemById(id);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public long addItemToStore(@RequestBody Item item){
        Item returnItem = itemService.addItem(item);
        return returnItem.getId();
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable long id){
        itemService.removeItem(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
