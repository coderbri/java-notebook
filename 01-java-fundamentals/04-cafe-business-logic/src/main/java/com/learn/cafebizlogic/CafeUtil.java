package com.learn.cafebizlogic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @file: CafeUtil.java
 * @description: Provides core business logic and utility methods for café operations.
 * including reward tracking, order total calculations, formatted menu displays,
 * volume price chart generation, and dynamic customer waitlist management.
 */
public class CafeUtil {


    /**
     * Calculates the default total number of purchases needed to achieve a 10-week streak goal.
     * Uses a consecutive sum loop where each week requires adding that week's number in purchases.
     * @return The cumulative total number of purchases required over 10 weeks.
     */
    public int getStreakGoal() {
        int sum = 0;
        for (int i=1; i<=10; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * Overloaded version of getStreakGoal, allowing na admin to set a custom streak duration.
     * @param numWeeks the number of weeks to calculate the cumulative streak goal over.
     * @return The cumulative total number of purchases required over the given number of weeks.
     */
    public int getStreakGoal(int numWeeks) {
        int sum = 0;
        for (int i=1; i<=numWeeks; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * Sums all item prices from an order into a single total.
     * @param prices an array of double prices for each item in the order.
     * @return The combined total price of the order.
     */
    public double getOrderTotal(double[] prices) {
        double total = 0.0;
        for (int i=0; i<prices.length; i++) {
            total += prices[i];
        }
        return total;
    }

    /**
     * Prints a formatted menu table listing each item alongside its index.
     * @param menuItems the list of menu item names to display.
     */
    public void displayMenu(ArrayList<String> menuItems) {
        // Table Header + Structure
        System.out.println("+--------------------+");
        System.out.println("|  Café Java - Menu  |");
        System.out.println("+--------------------+");
        System.out.println("| No. |    Coffees   |");
        System.out.println("+-----+--------------+");

        // List menu items
        for (int i=0; i<menuItems.size(); i++) {
            String itemName = menuItems.get(i);
            System.out.printf("|  %-2d | %-12s |\n", i, itemName);
        }
        // Table Footer
        System.out.println("+--------------------+");
    }

    /**
     * Overloaded version of displayMenu that also renders each item's price.
     * Validates that both lists are the same size before printing.
     * @param menuItems the list of menu item names to display.
     * @param itemPrices teh list of prices corresponding to each menu item, by index.
     * @return true if the menu was successfully displayed, false if the input lists were mismatched in sixe.
     */
    public boolean displayMenu(ArrayList<String> menuItems, ArrayList<Double> itemPrices) {
        // check if arraylist size of menuItems is the same as itemPrices
        if (menuItems.size() != itemPrices.size()) {
            System.out.println("> Error: Menu items and prices do not match. Unable to display menu.");
             return false;
        }

        System.out.println("+---------------------------+");
        System.out.println("|      Café Java - Menu     |");
        System.out.println("+---------------------------+");
        System.out.println("| No. |   Coffees   | Price |");
        System.out.println("+-----+-------------+-------+");
        // Menu Items + Prices
        for (int i=0; i<menuItems.size(); i++) {
            System.out.printf("|  %-2d | %-11s | $%.2f |\n", i, menuItems.get(i), itemPrices.get(i));
        }
        System.out.println("+---------------------------+");
         return true;
    }

    /**
     * Prints a bulk-quantity price chart for a single product, applying an increasing
     * $0.50 discount per unit as quantity grows.
     * @param itemName the name of the product to generate a chart for.
     * @param price the base unit price of the product.
     * @param maxQuantity the highest quantity to chart pricing for.
     */
    public void printPriceChart(String itemName, double price, int maxQuantity) {
        System.out.println("+----------------------------+");
        System.out.printf("|  %-24s  |\n", itemName);
        System.out.println("+----------------------------+");
        System.out.println("|  Qty. |    Price ($USD)    |");
        System.out.println("+-------+--------------------+");

        double discount = 0.00; // initial discount value
        for (int i=1; i<=maxQuantity; i++) {
            double discountedPrice = (price * i) + discount;
            System.out.printf("|  %-4d |     $%-11.2f   |\n", i, discountedPrice);
            discount -= 0.50;
        }
        System.out.println("+----------------------------+\n");
    }

    /**
     * Interactively adds customers to the waitlist until the user enters "q" to quit.
     * After each addition, greets the new customer, reports their position in line,
     * and reprints the full waitlist.
     * @param customers the running list of customers currently on the waitlist.
     */
    public void addCustomers(ArrayList<String> customers) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPlease enter your name (type 'q' to quit): ");
            String username = scanner.nextLine();

            if (username.equalsIgnoreCase("q")) {
                System.out.println(" > Exiting Waitlist...");
                break;
            } else {
                customers.add(username);
                int position = customers.size();

                System.out.printf("\nHello, %s! ", username);
                switch (position) {
                    case 1:
                        System.out.println("Your order will be ready shortly.");
                        break;
                    case 2:
                        System.out.println("You're next in line.");
                        break;
                    default:
                        System.out.printf("There are %d people in front of you.\n", position - 1);
                        break;
                }
                System.out.println("+--------------------------+");
                System.out.println("|   Café Java - Waitlist   |");
                System.out.println("+--------------------------+");
                System.out.println("|  Place  |    Customer    |");
                System.out.println("+---------+----------------+");
                for (int lineIndex=0; lineIndex<customers.size(); lineIndex++) {
                    String customerName = customers.get(lineIndex);
                    System.out.printf("|   %-4d  |  %-13s |\n", lineIndex + 1, customerName);
                }
            }
        }
    }

}
