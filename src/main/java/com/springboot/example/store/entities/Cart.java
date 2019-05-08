package com.springboot.example.store.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor()
public class Cart {

    private long customerId;
    private long id;
    private List<Item> itemList = new ArrayList<Item>();

}
