package com.learn.puzzling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPuzzleJava {
    public static void main(String[] args) {

        PuzzleJava appTest = new PuzzleJava();

        /* -------- Get Ten Rolls -------- */
        System.out.println("\n=== Get Ten Rolls ====================");
        List<Integer> list = appTest.getTenRolls();
        System.out.println(list);

        /* ------ Get Random Letter ------ */
        System.out.println("\n=== Get Random Letter ================");
        char randomLetter = appTest.getRandomLetter();
        System.out.println(randomLetter);

        /* ------ Generate Password ------ */
        System.out.println("\n=== Generate Password ================");
        String password = appTest.generatePassword();
        System.out.println(password);

        /* ---- Get New Password Set ----- */
        System.out.println("\n=== Get New Password Set =============");
        ArrayList<String> passwordList = appTest.getNewPasswordSet(4);
        System.out.println(passwordList);

        /* -------- Shuffle Array -------- */
        System.out.println("\n=== Shuffle Array ====================");
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.printf("Original array: %s\n", Arrays.toString(array));
        appTest.shuffleArray(array);
        System.out.printf("Shuffled array: %s\n", Arrays.toString(array));

        /* --- Shuffle Array (Overloaded) --- */
        System.out.println("\n=== Shuffle Array (Overloaded) ======= √");
        char[] array2 = {'j', 'a', 'v', 'a', 't', 'e', 's', 't'};
        System.out.printf("Original char array: %s\n", Arrays.toString(array2));
        appTest.shuffleArray(array2);
        System.out.printf("Shuffled char array: %s\n", Arrays.toString(array2));

        /* --- Generate Password (Overloaded) --- */
        System.out.println("\n=== Generate Password (Overloaded) ===");
        String password7 = appTest.generatePassword(7);
        System.out.printf("Finalized 7-char password: %s\n", password7);

        String password8 = appTest.generatePassword(8);
        System.out.printf("Finalized 8-char password: %s\n", password8);
    }
}
