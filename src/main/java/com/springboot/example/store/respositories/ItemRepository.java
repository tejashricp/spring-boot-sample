package com.springboot.example.store.respositories;

import com.springboot.example.store.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
