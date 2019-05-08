package com.springboot.example.store.services;

import com.springboot.example.store.entities.Item;
import com.springboot.example.store.respositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    public Optional<Item> findItemById(long id){
        return itemRepository.findById(id);
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public void removeItem(long id) {
        itemRepository.deleteById(id);
    }
}
