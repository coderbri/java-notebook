package com.learn.cafebizlogic;

import java.util.ArrayList;

/**
 * @file: TestCafe.java
 * @description: Driver class for executing unit test scenarios on CafeUtil features.
 * Validates streak calculations, order totals, menu rendering, discount charts, and waitlisting customers.
 */
public class TestCafe {
    /** Instantiated instance of utility class to execute feature tests. */
    private static final CafeUtil appTest = new CafeUtil();

    public static void main(String[] args) {

        System.out.println("\n+------------------------+");
        System.out.println(  "|       Café Java        |");
        System.out.println(  "+------------------------+");

        /* ========== APP TEST CASES ========== */

        // 1. Reward Streak Goal Test
        System.out.println("\n----- Streak Goal Test -------");
        int numWeeks = 15;
        System.out.printf("Purchases needed by week 10: %s\n", appTest.getStreakGoal());
        System.out.printf("Purchases needed by week %d: %s\n", numWeeks, appTest.getStreakGoal(numWeeks));

        // 2. Order Total Calculation Test
        System.out.println("\n----- Order Total Test -------");
        double[] prices = { 2.15, 4.36, 4.16, 3.5 };
        double total = appTest.getOrderTotal(prices);
        System.out.printf("Total order prices: $%.2f\n", total);

        // 3. Menu Rendering Tests (With & Without Prices)
        System.out.println("\n----- Display Menu Test ------");
        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("drip coffee");
        menuItems.add("cappuccino");
        menuItems.add("latte");
        menuItems.add("mocha");
        appTest.displayMenu(menuItems);

        ArrayList<Double> itemPrices = new ArrayList<>();
        itemPrices.add(2.15);
        itemPrices.add(4.36);
        itemPrices.add(4.16);
        itemPrices.add(3.50);
        // appTest.displayMenu(menuItems, itemPrices);
        boolean success = appTest.displayMenu(menuItems, itemPrices);
        if (!success)
            System.out.println("Please fix the menu data and try again.");

        // 4. Bulk Price Discount Chart Test
        System.out.println("\n----- Price Chart for Menu Items ------");
        String item1 = "Columbian Coffee Grounds";
        double price1 = 15.00;
        int maxQty1 = 3;
        appTest.printPriceChart(item1, price1, maxQty1);

        String item2 = "Starbucks Coffee Beans";
        double price2 = 24.00;
        int maxQty2 = 10;
        appTest.printPriceChart(item2, price2, maxQty2);

        // 5. Customer Waitlist Interactive Test
        System.out.println("\n------- Customer Waitlist Test --------");
        ArrayList<String> customers = new ArrayList<>();
        appTest.addCustomers(customers);
    }
}
