package com.springboot.example.store.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@NoArgsConstructor
@Entity
@Getter(AccessLevel.PUBLIC) @Setter
public class Item {
    String name;

    @Id
    @GeneratedValue
    long id;

    long price;

    String category;

    int quantity;

    public Item(String name, long id, long price, String category) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                price == item.price &&
                quantity == item.quantity &&
                Objects.equals(name, item.name) &&
                Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, price, category, quantity);
    }
}
