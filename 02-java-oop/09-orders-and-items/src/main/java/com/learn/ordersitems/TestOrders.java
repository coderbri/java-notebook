package com.learn.ordersitems;

import java.util.ArrayList;

/**
 * @file TestOrder.java
 * @description: Driver class that simulates four Café Java orders.
 * Builds the menu, creates the orders, adds items, and updates each order's status.
 */
public class TestOrders {
    public static void main(String[] args) {

        // === MEMBER VARIABLES - Messages //
        String generalGreeting = "Welcome to Café Java, ";
        String pendingMessage = ", your order will be ready shortly";
        String readyMessage = ", your order is ready!";
        String displayTotalMessage = "Your total is: $";

        // === APP VARIABLES - Menu Items //
        Item dripCoffee = new Item("Drip Coffee", 2.15);
        Item mocha = new Item("Mocha", 3.50);
        Item latte = new Item("Latte", 4.16);
        Item cappuccino = new Item("Cappuccino", 4.36);

        // Store Items in a Menu list for easy display
        ArrayList<Item> menu = new ArrayList<>();
        menu.add(dripCoffee);
        menu.add(mocha);
        menu.add(latte);
        menu.add(cappuccino);

        // Display Menu
        System.out.println("\n+---------------------------+");
        System.out.println("|      Café Java - Menu     |");
        System.out.println("+---------------------------+");
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf("| %d. %-14s | $%.2f |\n", i+1,
                    menu.get(i).getName(),
                    menu.get(i).getPrice());
        }
        System.out.println("+---------------------------+\n");

        Order order1 = new Order("Cindhuri");
        Order order2 = new Order("Jimmy");
        Order order3 = new Order("Noah");
        Order order4 = new Order("Sam");

        // System.out.println("\n+------ PRINT AN ORDER ------+");
        // System.out.println(order1);         // com.learn.ordersitems.Order@5b2133b1
        // System.out.println(order1.total);   // 0.0

        // === APPLICATION SIMULATION === //
        System.out.println("+-------- ORDER 1 -----------+");
        System.out.printf("%s%s.\n", generalGreeting, order1.customerName);
        order1.orderedItems.add(mocha);
        order1.calculateTotal();
        order1.displayStatus(pendingMessage, readyMessage, displayTotalMessage);

        System.out.println("\n+---- (UPDATED) ORDER 1 -----+");
        order1.markReady();
        System.out.println( order1.isReady
                ? String.format("Ready: %s\n", order1.isReady)
                + String.format("%s%s\n", order1.customerName, readyMessage)
                + String.format("%s%.2f\n", displayTotalMessage, order1.total)
                : String.format("%s%s.\n", order1.customerName, pendingMessage)
        );

        System.out.println("\n+-------- ORDER 2 -----------+");
        System.out.printf("%s%s.\n", generalGreeting, order2.customerName);
        order2.orderedItems.add(dripCoffee);
        order2.calculateTotal();
        order2.markReady();
        order2.displayStatus(pendingMessage, readyMessage, displayTotalMessage);

        System.out.println("\n+--------- ORDER 3 ----------+");
        System.out.printf("%s%s.\n", generalGreeting, order3.customerName);
        order3.orderedItems.add(cappuccino);
        order3.calculateTotal();
        order3.displayStatus(pendingMessage, readyMessage, displayTotalMessage);

        System.out.println("\n+--------- ORDER 4 ----------+");
        System.out.printf("%s%s.\n", generalGreeting, order4.customerName);
        order4.orderedItems.add(latte);
        order4.calculateTotal();
        order4.displayStatus(pendingMessage, readyMessage, displayTotalMessage);

        System.out.println("\n+---- (UPDATED) ORDER 4 -----+");
        order4.orderedItems.add(latte);
        order4.orderedItems.add(latte);
        order4.calculateTotal();
        order4.markReady();
        order4.displayStatus(pendingMessage, readyMessage, displayTotalMessage);
    }
}