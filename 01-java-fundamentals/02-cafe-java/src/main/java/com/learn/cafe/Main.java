package com.learn.cafe;

public class Main {
    public static void main( String[] args) {
        // * ======== APP VARIABLES ========= * //
        // * --------- Messages ------------ * //
        String generalGreeting = "\nWelcome to Café Java, %s.\n";
        String pendingMessage = "%s, your order will be ready shortly.\n";
        String readyMessage = "%s, your order is ready!\n";
        String displayTotalMessage = "Your total is $%.2f.\n\n";

        // * --------- Menu Items ---------- * //
        double mochaPrice = 3.5;
        double dripCoffeePrice = 2.15;
        double lattePrice = 4.16;
        double cappuccinoPrice = 4.36;

        // * ------- Customer Names -------- * //
        String customer1 = "Cindhuri";
        String customer2 = "Noah";
        String customer3 = "Sam";
        String customer4 = "Jimmy";

        // * ------- Order Statuses -------- * //
        boolean isReadyOrder1 = false;
        boolean isReadyOrder2 = true;
        boolean isReadyOrder3 = true;
        boolean isReadyOrder4 = true;

        // * ======= APP SIMULATION ======== * //
        System.out.println("\n+------------------------+");
        System.out.println(  "|       Café Java        |");
        System.out.println(  "+------------------------+");

        // ① Cindhuri orders a drip coffee. Display order status.
        System.out.printf(generalGreeting, customer1);
        System.out.printf(
                !isReadyOrder1 ? pendingMessage
                : readyMessage + displayTotalMessage, customer1, dripCoffeePrice
        );

        // ② Noah orders a cappuccino. Display message if ready.
        System.out.printf(generalGreeting, customer2);
        if ( isReadyOrder2 ) {
            System.out.printf(readyMessage, customer2);
            System.out.printf(displayTotalMessage, cappuccinoPrice);
        } else {
            System.out.printf(pendingMessage, customer2);
        }

        // ③ Sam orders 2 lattes. Display total.
        System.out.printf(generalGreeting, customer3);
        double samsLatteTotal = lattePrice * 2;

        if ( isReadyOrder3 ) {
            System.out.printf(readyMessage, customer3);
            System.out.printf(displayTotalMessage, samsLatteTotal);
        } else {
            System.out.printf(pendingMessage, customer3);
        }

        // ④ Jimmy realizes that he was charged for a coffee, but had ordered a mocha.
        double jimmysTotal = dripCoffeePrice+mochaPrice;
        System.out.printf(generalGreeting, customer4);
        System.out.printf(readyMessage, customer4);
        System.out.printf(displayTotalMessage, jimmysTotal);

        System.out.println(customer4 + ": Hey! I didn't order a coffee! I only ordered a mocha!");
        System.out.println("Barista: We're so sorry, sir. We'll fix that right up.");
        System.out.printf(readyMessage, customer4);
        System.out.printf(displayTotalMessage, mochaPrice);
    }
}
