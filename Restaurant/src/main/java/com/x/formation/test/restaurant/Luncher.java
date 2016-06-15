package com.x.formation.test.restaurant;

import com.x.formation.test.restaurant.controller.OrdersSystem;

/**
 * The Luncher of the system 
 * @author aabum
 */
public class Luncher {

    public static void main(String[] args) {
        OrdersSystem.getInstatnce().start();
    }
}
