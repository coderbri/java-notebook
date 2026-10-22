package com.learn.baristachallenge;

/**
 * @file TestOrders.java
 * @description Runs a simulation of four café orders to test the Item and Order classes.
 *              Creates the menu items and orders, then walks each order through adding
 *              items, checking its status, marking it ready, and printing a receipt.
 */
public class TestOrders {
    public static void main(String[] args) {

        // === APP VARIABLES - Menu Items //
        Item dripCoffee = new Item("Drip Coffee", 2.15);
        Item mocha = new Item("Mocha", 3.50);
        Item latte = new Item("Latte", 4.16);
        Item cappuccino = new Item("Cappuccino", 4.36);

        Order order1 = new Order();
        Order order2 = new Order("Jimmy");
        Order order3 = new Order("Noah");
        Order order4 = new Order("Cindhuri");

//        // Store Items in a Menu list for easy display
//        ArrayList<Item> menu = new ArrayList<>();
//        menu.add(item1);
//        menu.add(item2);
//        menu.add(item3);
//        menu.add(item4);

        // Display Menu
//        System.out.println("\n+---------------------------+");
//        System.out.println("|      Café Java - Menu     |");
//        System.out.println("+---------------------------+");
//        for (int i = 0; i < menu.size(); i++) {
//            System.out.printf("| %d. %-14s | $%.2f |\n", i+1,
//                    menu.get(i).getName(),
//                    menu.get(i).getPrice());
//        }
//        System.out.println("+---------------------------+\n");


        // === APPLICATION SIMULATION === //
        System.out.println("========= Café Java ==========");

        System.out.println("\n+~~~~~~~~~ ORDER 1 ~~~~~~~~~~+");
        order1.addOrderedItem(cappuccino);
        order1.addOrderedItem(mocha);
        System.out.println(order1.getStatusMessage());

        order1.setReady(true);
        System.out.println(order1.getStatusMessage());
        order1.displayReceipt();

        System.out.println("\n+~~~~~~~~~ ORDER 2 ~~~~~~~~~~+");
        order2.addOrderedItem(dripCoffee);
        System.out.println(order2.getStatusMessage());

        order2.setReady(true);
        System.out.println(order2.getStatusMessage());
        order2.displayReceipt();

        /* Order 3 intentionally has no items to test the empty-order receipt. */
        System.out.println("\n+~~~~~~~~~ ORDER 3 ~~~~~~~~~~+");
        System.out.println(order3.getStatusMessage());

        order3.setReady(true);
        System.out.println(order3.getStatusMessage());
        order3.displayReceipt();

        System.out.println("\n+~~~~~~~~~ ORDER 4 ~~~~~~~~~~+");
        order4.addOrderedItem(latte);
        order4.addOrderedItem(latte);
        order4.getStatusMessage();

        order4.setReady(true);
        System.out.println(order4.getStatusMessage());
        order4.displayReceipt();
    }
}