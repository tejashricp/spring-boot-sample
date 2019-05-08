package com.springboot.example.store.controllers;

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
        when(itemService.findAllItems()).thenReturn(Arrays.asList(new Item("abcd",1,123,"bag")));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        List<Item> itemlist = this.mapFromJson(content, List.class);
        assertTrue(itemlist.size() > 0);
    }

//    @Ignore
    @Test
    public void when_getAnItemById_it_should_return_status_OK() throws Exception {
        String uri = "/items/4";
        when(itemService.findItemById(isA(Long.class))).thenReturn(Optional.of(new Item("abcd", 4, 123, "bag")));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Item item = this.mapFromJson(content, Item.class);
        assertNotNull(item);
    }

//    @Ignore
    @Test
    public void when_addItemToStore_it_should_return_status_Created() throws Exception {
        Item item = new Item("name1",1234,224,"book");
        String uri = "/items";
        when(itemService.addItem(isA(Item.class))).thenReturn(item);

        String inputJson = super.mapToJson(item);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
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
    }
}