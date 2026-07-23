package com.learn.alfredbot;

import java.util.Scanner;

/**
 * Test driver for AlfredQuotes — exercises guest greetings, date
 * announcements, and conversation responses.
 */
public class AlfredTest {

    private static final AlfredQuotes alfredBot = new AlfredQuotes();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        testGuestGreetings();
        testDateAnnouncement();
        testConversationResponses();

        scanner.close();
    }

    /**
     * Tests the three guestGreeting() overloads: no-arg, name-only
     * (auto-detected time of day), and explicit day period + name.
     */
    private static void testGuestGreetings() {
        System.out.println("\n======= GUEST GREETING TEST =======");

        System.out.println(alfredBot.guestGreeting());
        System.out.println(alfredBot.guestGreeting("Ava Lovelace"));

        System.out.print("\nTo whom do I have the honor of assisting? ");
        String guestName = scanner.nextLine();
        System.out.println(alfredBot.guestGreeting("morning", guestName));
    }

    /**
     * Tests both date announcement formats, letting the
     * user pick which one to see via console input.
     */
    private static void testDateAnnouncement() {
        System.out.println("\n===== DATE ANNOUNCEMENT TEST ======");

        System.out.print("How would you like to know the date? (regular/simplified) ");
        String askDateFormat = scanner.nextLine();

        String announceDate;
        switch (askDateFormat.trim().toLowerCase()) {
            case "regular":
                announceDate = alfredBot.dateAnnouncement();
                break;
            case "simplified":
                announceDate = alfredBot.dateAnnouncementSimplified();
                break;
            default:
                announceDate = "Invalid choice. Please choose 'simplified' or 'regular'.\n";
                break;
        }
        System.out.println(announceDate);
    }

    /**
     * Tests respondToConversation() against three preset conversations
     * covering the Alexis, Alfred, and default response branches.
     */
    private  static void testConversationResponses() {
        System.out.println("\n=== CONVERSATION RESPONSE TEST ====");

        String conversation1 = "Alexis! Play some lo-fi beats.";
        System.out.println(alfredBot.respondToConversation(conversation1));

        String conversation2 = "I can't find my yo-yo. Maybe Alfred will know where it is.";
        System.out.println(alfredBot.respondToConversation(conversation2));

        String conversation3 = "Maybe I should watch the Lion King...";
        System.out.println(alfredBot.respondToConversation(conversation3));
    }
}
