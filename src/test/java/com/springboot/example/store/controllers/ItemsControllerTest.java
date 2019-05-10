package com.springboot.example.store.controllers;

import com.springboot.example.store.builders.ItemBuilder;
import com.springboot.example.store.entities.Item;
import com.springboot.example.store.services.ItemService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ItemsController.class)
public class ItemsControllerTest extends AbstractTest{

    @MockBean
    ItemService itemService;


//    @Ignore
    @Test
    public void when_getAllItem_it_should_return_status_OK() throws Exception {
        String uri = "/items";
        Item item1 = new ItemBuilder().category("bag").id(1).name("Widcraft").quantity(123).price(12332).build();
        when(itemService.findAllItems()).thenReturn(Arrays.asList(item1));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<Item> itemlist = this.mapFromJson(content, List.class);
        assertTrue(itemlist.size() > 0);
        verify(itemService, times(1)).findAllItems();
    }

//    @Ignore
    @Test
    public void when_getAnItemById_it_should_return_status_OK() throws Exception {
        String uri = "/items/4";
        Item item1 = new ItemBuilder().category("bag").id(1).name("Widcraft").quantity(123).price(12332).build();
        when(itemService.findItemById(isA(Long.class))).thenReturn(Optional.of(item1));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Item item = this.mapFromJson(content, Item.class);
        assertNotNull(item);
        verify(itemService, times(1)).findItemById(isA(Long.class));
    }

//    @Ignore
    @Test
    public void when_addItemToStore_it_should_return_status_Created() throws Exception {
        Item item1 = new ItemBuilder().category("bag").id(1).name("Widcraft").quantity(123).price(12332).build();
        String uri = "/items";
        when(itemService.addItem(isA(Item.class))).thenReturn(item1);

        String inputJson = super.mapToJson(item1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        verify(itemService, times(1)).addItem(isA(Item.class));
    }

//    @Ignore
    @Test
    public void when_deleteItemInStore_it_should_return_No_Content() throws Exception {
        long id = 123;
        String uri = "/items/"+id;
        doNothing().when(itemService).removeItem(isA(Long.class));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        verify(itemService, times(1)).removeItem(isA(Long.class));
    }
}