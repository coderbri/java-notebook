package com.learn.ordersitems;

import java.util.ArrayList;

/**
 * @file Order.java
 * @description: Represents a customer's order at Café Java.
 * Tracks the customer's name, the items ordered, the running total, and whether the order is ready for pickup.
 */
public class Order {

    // === MEMBER VARIABLES === //
    public String customerName;
    public double total;
    public boolean isReady;
    public ArrayList<Item> orderedItems = new ArrayList<>();

    // === CONSTRUCTOR ======== //
    /**
     * Creates a new, empty order that starts with a total of 0.0 and is not ready.
     * @param customerName the name of the customer placing the order.
     */
    public Order(String customerName) {
        this.customerName = customerName;
        this.total = 0.0;
        this.isReady = false;
    }

    // === METHODS ============ //
    /**
     * Recalculates the order total by adding up the price of every ordered item.
     * Resets the total to zero first, so calling it repeatedly never double counts.
     */
    public void calculateTotal() {
        total = 0;
        for (Item item : orderedItems) {
            total += item.getPrice();
        }
    }

    /** Marks the order as ready for pickup. */
    public void markReady() {
        this.isReady = true;
    }

    /**
     * Prints the order's status, so the display logic lives in the order itself.
     * A ready order prints its ready flag, the ready message, and the total.
     * A pending order prints only the pending message.
     * @param pendingMessage text added after the customer's name while the order is pending.
     * @param readyMessage text added after the customer's name once the order is ready.
     * @param displayTotalMessage label printed before the order total.
     */
    public void displayStatus(String pendingMessage, String readyMessage, String displayTotalMessage) {
        if (this.isReady) {
            System.out.printf("Ready: %s\n", isReady);
            System.out.printf("%s%s\n", this.customerName, readyMessage);
            System.out.printf("%s%.2f\n", displayTotalMessage, this.total);
        } else {
            System.out.printf("%s%s.\n", this.customerName, pendingMessage);
        }
    }
}
