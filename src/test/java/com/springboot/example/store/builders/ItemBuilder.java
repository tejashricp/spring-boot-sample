package com.springboot.example.store.builders;

import com.springboot.example.store.entities.Item;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

/*Metadata metadata = new MetadataBuilder().group("mygroup").name("myconfig")
                .value("key1", "value1").value("key2", Arrays.asList("One","Two","Three")).tags("tag1").build();
* */
@NoArgsConstructor
public class ItemBuilder {
    private long id;

    private long price;

    private String category;

    private int quantity;

    private String name;

    public ItemBuilder id(long id){
        this.id = id;
        return this;
    }

    public ItemBuilder price(long price){
        this.price = price;
        return this;
    }

    public ItemBuilder category(String category){
        this.category = category;
        return this;
    }

    public ItemBuilder name(String name){
        this.category = category;
        return this;
    }

    public ItemBuilder quantity(int quantity){
        this.quantity = quantity;
        return this;
    }

    public Item build(){
        return new Item(this.name,this.id,this.price,this.category);
    }
}
