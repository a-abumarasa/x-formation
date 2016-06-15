/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.x.formation.test.restaurant.controller;

import com.x.formation.test.restaurant.entity.Item;
import com.x.formation.test.restaurant.entity.Category;

/**
 * Helping class used in initialize the menu, by adding categories and items
 *
 * @author aabum
 */
public class MenuBuilder {

    /**
     * Add a new category to another exist category
     * <p>
     * @param name The name of the new category
     * @param parent the parent category
     * @return the added category
     */
    public static Category addCategory(Category parent, String name) {
        Category category = new Category(name);
        return addCategory(parent, category);
    }

    /**
     * Add an exist category to another exist category
     * <p>
     * @param category The child category
     * @param parent the parent category
     * @return the added category
     */
    public static Category addCategory(Category parent, Category category) {
        if (category.getParent() != null) {
            category.getParent().getSubCategories().remove(category);
        }
        category.setParent(parent);
        parent.getSubCategories().add(category);
        return category;
    }

    /**
     * Delete a category from the menu
     * <p>
     * @param category The category wanted to be deleted
     */
    public static void removeCategory(Category category) {
        category.getParent().getSubCategories().remove(category);
        category.setParent(null);
    }

    /**
     * Add a new Item to a category
     * <p>
     * @param category The category needed to contain the item
     * @param name the name of the new Item
     * @param price the price of the new item
     * @return the added item
     */
    public static Item addItem(Category category, String name, float price) {
        Item item = new Item(name, price);
        return addItem(category, item);
    }

    /**
     * Add an exist Item to a category, and remove it from the old category
     * <p>
     * @param category The category needed to contain the item
     * @param item the exist item
     * @return the added Item
     */
    public static Item addItem(Category category, Item item) {
        item.setCategory(category);
        category.getItems().add(item);
        return item;
    }

    /**
     * Delete an item from the menu
     * <p>
     * @param item The item wanted to be deleted
     */
    public static void removeItem(Item item) {
        item.getCategory().getItems().remove(item);
        item.setCategory(null);
    }
}
