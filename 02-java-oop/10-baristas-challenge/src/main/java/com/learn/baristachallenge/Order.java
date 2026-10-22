package com.learn.baristachallenge;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * @file Order.java
 * @description Models a single café order with a customer name, ordered items, and a ready status.
 */
public class Order {

    // === MEMBER VARIABLES === //
    private String customerName;
    private boolean isReady;
    private ArrayList<Item> orderedItems;

    // === CONSTRUCTORS ======= //
    /* Creates an order for a guest with no items. */
    public Order() {
        this.customerName = "Guest";
        this.orderedItems = new ArrayList<>();
    }

    /**
     * Creates an order for the named customer with no items.
     * @param customerName the name of the customer placing the order
     */
    public Order(String customerName) {
        this.customerName = customerName;
        this.orderedItems = new ArrayList<>();
    }

    // === METHODS =========== //
    /**
     * Adds an item to this order.
     * @param item the menu item to add
     */
    public void addOrderedItem(Item item) {
        orderedItems.add(item);
    }

    /**
     * Builds a status message based on whether the order is ready.
     * @return a personalized message for the customer
     */
    public String getStatusMessage() {
        if (isReady) {
            return String.format("%s, your order is ready.", customerName);
        }
        return String.format("Thank you for waiting, %s. Your order will be ready shortly.", this.customerName);
    }

    /**
     * Sums the price of every item in this order.
     * @return the total cost of the order
     */
    public double calculateTotal() {
        double total = 0;
        for (Item item : orderedItems) {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Prints a receipt for the customer, which was a bonus feature I added.
     * Orders with no items show an error line instead of a total.
     */
    public void displayReceipt() {
        System.out.println("\n+-----------------------+");
        System.out.println("|        Receipt        |");
        System.out.println("| --------------------- |");
        System.out.printf("| Customer: %-11s |\n", this.customerName);

        // edge-case 1: don't print items if nothing was ordered
        // otherwise, print items to receipt
        if (orderedItems.isEmpty()) {
            System.out.println("|    Error: No items    |");
        } else {
            for (Item item : orderedItems) {
                System.out.printf("| %-11s -- $%.2f  |\n", item.getName(), item.getPrice());
            }
            System.out.println("| --------------------- |");
            System.out.printf("| %13s: %-6s |\n", "Total",
                    String.format("$%.2f", calculateTotal())
            );
        }
        System.out.println("+-----------------------+");
    }

    // === GETTER & SETTERS === //
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isReady() {
        return isReady;
    }
    public void setReady(boolean ready) {
        isReady = ready;
    }

    public ArrayList<Item> getOrderedItems() {
        return orderedItems;
    }
    public void setOrderedItems(ArrayList<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
