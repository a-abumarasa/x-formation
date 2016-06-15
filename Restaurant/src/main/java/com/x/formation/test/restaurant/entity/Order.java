
package com.x.formation.test.restaurant.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represent an order for a client, contains a list of items and the data of the order 
 * @author aabum
 */
public class Order {

    private static int counter;
    private final int id;
    private final Date date;
    private final ArrayList<Item> items;

    public Order() {
        id = counter++;
        date = new Date();
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (Item item :items) {
            totalPrice+=item.getPrice();
        }
        return totalPrice;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

}
