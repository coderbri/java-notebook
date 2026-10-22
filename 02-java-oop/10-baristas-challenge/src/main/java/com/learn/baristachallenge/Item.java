package com.learn.baristachallenge;

/**
 * @file Item.java
 * @description Models a single menu item with a name and a price.
 */
public class Item {

    // === MEMBER VARIABLES === //
    private String name;
    private double price;

    // === CONSTRUCTOR ======== //
    /**
     * Creates a menu item with the given name and price.
     * @param name the display name of the item
     * @param price the cost of the item in dollars
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

