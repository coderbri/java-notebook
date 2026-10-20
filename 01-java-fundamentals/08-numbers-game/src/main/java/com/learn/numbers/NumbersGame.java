package com.learn.numbers;

import java.util.Random;
import java.util.Scanner;

/**
 * @file NumbersGame.java
 * @description: A console guessing game where the player has three tries to guess a number from 0 to 10.
 * including input validation, a quit option, and the ability to play multiple rounds.
 */
public class NumbersGame {

    // === MEMBER VARIABLES === //
    private static final int MIN_NUMBER = 0;    // The lowest number the game can pick.
    private static final int MAX_NUMBER = 10;   // The highest number the game can pick.
    private static final int MAX_GUESSES = 3;   // The number of valid guesses allowed per round.
    /** Shared random number generator used to pick each round's answer. */
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            playGame(scanner);
            playAgain = askPlayAgain(scanner);
        }
        System.out.println("\n[*] Thanks for playing! Goodbye.");
        scanner.close();
    }

    /** Prints the title banner shown at the start of each round. */
    private static void printBanner() {
        System.out.println("\n+-----------------------------------------+");
        System.out.println(  "|              NUMBERS GAME               |");
        System.out.println(  "+-----------------------------------------+");
    }

    /**
     * Plays a single round: picks a secret number and allows up to MAX_GUESSES valid guesses.
     * Only guesses inside the valid range count toward the limit, and typing 'q' ends the round early.
     * @param scanner the shared Scanner used to read the player's input.
     */
    public static void playGame(Scanner scanner) {
        printBanner();
        System.out.printf("Hello, human. I am thinking of a number between %d and %d.\n", MIN_NUMBER, MAX_NUMBER);
        System.out.printf("You have %d guesses. Type 'q' at any time to quit.\n\n", MAX_GUESSES);

        int answer = RANDOM.nextInt(MIN_NUMBER, MAX_NUMBER+1); // includes 0 to 10
        int guessCount = 0;

        while (guessCount < MAX_GUESSES) {
            System.out.printf(" > Guess %d of %d: ", guessCount+1, MAX_GUESSES);
            String guess = scanner.nextLine().trim();

            if (guess.equalsIgnoreCase("q")) {
                System.out.println("\n[*] I knew you didn't have it in you.");
                System.out.println("[*] Ending this game's session...");
                return;
            }

            try {
                int numberGuess = Integer.parseInt(guess);

                if (numberGuess < MIN_NUMBER || numberGuess > MAX_NUMBER) {
                    System.out.printf("[x] Out of range. Enter a number from %d to %d.\n", MIN_NUMBER, MAX_NUMBER);
                } else {
                    /** Counts only valid, in-range guesses toward the limit. */
                    guessCount++;

                    if (numberGuess == answer) {
                        System.out.println("[!] Lucky guess! But can you do it again?");
                        return;
                    } else if (guessCount < MAX_GUESSES) {
                        System.out.println("[x] Swing and miss! Keep trying...");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.printf(
                        "[x] Invalid input. Enter a number from %d to %d, or 'q' to quit.\n",
                        MIN_NUMBER,
                        MAX_NUMBER
                );
            }
        }
        System.out.println("\n[x] You've run out of guesses! Better luck next time.");
        System.out.printf("[i] The correct answer was: %d\n", answer);
    }

    /**
     * Asks whether the player wants another round, repeating until the answer is a clear yes or no.
     * Accepts "y" or "yes" and "n" or "no" in any letter case.
     * @param scanner the shared Scanner used to read the player's input.
     * @return true if the player wants another round, false otherwise.
     */
    private static boolean askPlayAgain(Scanner scanner) {
        while(true) {
            System.out.print("\n[?] Play again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("yes") || response.equals("y")) {
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            }
            System.out.println("[x] Please answer 'yes' or 'no'.");
        }
    }
}
