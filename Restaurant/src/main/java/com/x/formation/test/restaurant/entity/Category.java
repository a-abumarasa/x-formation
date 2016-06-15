package com.x.formation.test.restaurant.entity;

import java.util.ArrayList;

/**
 * Represent a node of the menu, which can contain 
 * <p>
 * 1. list of another categories
 * <p>
 * 2. list of items
 * <p>
 * it cannot contain sub-categories and items in the same time
 * @author aabum
 */
public class Category {

    private String name;
    private Category parent;
    private ArrayList<Category> subCategories;
    private ArrayList<Item> items;

    public Category() {
        subCategories = new ArrayList<>();
        items = new ArrayList<>(); 
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public Category(String name, Category parent) {
        this();
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public ArrayList<Item> getItems() {
        return items;
    } 
    
    public int numberOfItems() {
        return Math.max(subCategories.size(), items.size());
    }
}
