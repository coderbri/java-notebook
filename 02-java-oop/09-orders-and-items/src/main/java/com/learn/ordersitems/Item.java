package com.learn.ordersitems;

/**
 * @file Item.java
 * @description: Represents a menu item offered by Café Java, with a name and a price.
 * Fields are private and accessed through getters and setters.
 */
public class Item {

    // === MEMBER VARIABLES === //
    private String name;    /** The name of a menu item. */
    private double price;   /** The price of a menu item in USD. */

    // === CONSTRUCTOR ======== //
    /**
     * Creates a menu item with the given name and price.
     * @param name the name of the menu item.
     * @param price the price of the menu item in dollars.
     */
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // === GETTER & SETTERS === //
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
