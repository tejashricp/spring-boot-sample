package com.springboot.example.store.services;

import com.springboot.example.store.builders.ItemBuilder;
import com.springboot.example.store.entities.Item;
import com.springboot.example.store.respositories.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;


    @Test
    public void when_addItem_it_should_return_saved_item() {
        Item item = new ItemBuilder().category("book").id(1).name("Harry Potter").price(1230).quantity(10).build();
        when(itemRepository.save(isA(Item.class))).thenReturn(item);

        Item returnItem = itemService.addItem(item);

        assertNotNull(returnItem);
        assertEquals(item,returnItem);
        verify(itemRepository, times(1)).save(isA(Item.class));
    }

    @Test
    public void when_findItemById_it_should_return_item_if_present() {
        Item item = new ItemBuilder().category("book").id(1).name("Harry Potter").price(1230).quantity(10).build();
        when(itemRepository.findById(isA(Long.class))).thenReturn(Optional.of(item));

        Optional<Item> returnItem = itemService.findItemById(12);

        assertNotNull(returnItem);
        assertEquals(item,returnItem.get());
        verify(itemRepository, times(1)).findById(isA(Long.class));
    }

    @Test
    public void when_findAllItems_it_should_return_all_items_present() {
        List<Item> itemList = new ArrayList<Item>();
        Item item = new ItemBuilder().category("book").id(1).name("Harry Potter").price(1230).quantity(10).build();
        Item item1 = new ItemBuilder().category("bag").id(1).name("Widcraft").price(2430).quantity(17).build();
        Item item2 = new ItemBuilder().category("shirt").id(1).name("USPolo").price(1400).quantity(2).build();
        Item item3 = new ItemBuilder().category("jeans").id(1).name("Levise").price(2900).quantity(13).build();
        itemList.add(item);
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        when(itemRepository.findAll()).thenReturn(itemList);

        List<Item> returnItems = itemService.findAllItems();

        assertNotNull(returnItems);
        assertEquals(itemList,returnItems);
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    public void when_removeItem_it_should_return() {
        doNothing().when(itemRepository).deleteById(isA(Long.class));

        itemService.removeItem(12);

        verify(itemRepository).deleteById(isA(Long.class));
    }
}