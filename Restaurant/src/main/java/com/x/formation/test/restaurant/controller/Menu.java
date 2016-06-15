/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.x.formation.test.restaurant.controller;

import com.x.formation.test.restaurant.entity.Category;

/**
 * Represent the main node of the menu, which is a category with no parents
 *
 * @author aabum
 */
public class Menu {

    private static Category menu;

    public static Category getInstance() {
        if (menu == null) {
            menu = new Category();
            menu.setName("Menu");
        }
        return menu;
    }
}
