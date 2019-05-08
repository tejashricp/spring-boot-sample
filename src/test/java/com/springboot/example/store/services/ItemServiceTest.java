package com.springboot.example.store.services;

import com.springboot.example.store.entities.Item;
import com.springboot.example.store.respositories.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        Item item = new Item("abcd",12,1234,"bag");
        when(itemRepository.save(isA(Item.class))).thenReturn(item);

        Item returnItem = itemService.addItem(item);

        assertNotNull(returnItem);
        assertEquals(item,returnItem);
    }

    @Test
    public void when_findItemById_it_should_return_item_if_present() {
        Item item = new Item("abcd",12,1234,"bag");
        when(itemRepository.findById(isA(Long.class))).thenReturn(Optional.of(item));

        Optional<Item> returnItem = itemService.findItemById(12);

        assertNotNull(returnItem);
        assertEquals(item,returnItem.get());
    }

    @Test
    public void when_findAllItems_it_should_return_all_items_present() {
        Item item = new Item("abcd",12,1234,"bag");
        when(itemRepository.findAll()).thenReturn(Arrays.asList(item));

        List<Item> returnItem = itemService.findAllItems();

        assertNotNull(returnItem);
        assertEquals(Arrays.asList(item),returnItem);
    }

    @Test
    public void when_removeItem_it_should_return() {
        doNothing().when(itemRepository).deleteById(isA(Long.class));

        itemService.removeItem(12);

        verify(itemRepository).deleteById(isA(Long.class));
    }
}