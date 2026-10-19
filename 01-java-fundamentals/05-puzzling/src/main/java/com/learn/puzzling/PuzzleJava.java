package com.learn.puzzling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @file PuzzleJava.java
 * @description: Provides randomized puzzle utilities for client-side operations.
 * including dice rolls, random letters, password generation, and array shuffling.
 */
public class PuzzleJava {

    /** Shared random number generator used by every method in this class. */
    Random randomMachine = new Random();

    /**
     * Generates ten random rolls.
     * Uses the two-argument nextInt, whose upper bound is exclusive, so 21 produces values from 1 to 20.
     * @return A list of ten random integers between 1 and 20, inclusive.
     */
    public List<Integer> getTenRolls() {
        List<Integer> rolledNumbers = new ArrayList<>();

        for (int i=0; i<10; i++) {
            int randomNum = randomMachine.nextInt(1,21);  // generates a random number between 0 and 9
            rolledNumbers.add(randomNum);
        }
        return rolledNumbers;
    }

    /**
     * The letters available for random selection.
     * Declared static so a single copy is shared by every instance, and final so it cannot be modified.
     */
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    /* ------ Get Random Letter ------ */
    /**
     * Picks a random letter from the alphabet.
     * Chooses a random index from 0 to 25 and returns the character at that position.
     * @return A random lowercase letter.
     */
    public char getRandomLetter() {
        int index = randomMachine.nextInt(ALPHABET.length());
        return ALPHABET.charAt(index);
    }

    /* ------ Generate Password ------ */
    /**
     * Builds a random eight-letter password.
     * Appends to a StringBuilder instead of using +=, which would create and discard a new String on every pass.
     * @return A random eight-character lowercase password.
     */
    public String generatePassword() {
        StringBuilder password = new StringBuilder();

        for (int i=0; i<8; i++) {
            password.append(getRandomLetter());
        }
        return password.toString();
    }

    /* ---- Get New Password Set ----- */
    /**
     * Generates a set of random eight-letter passwords.
     * @param length the number of passwords to generate.
     * @return A list containing the requested number of passwords.
     */
    public ArrayList<String> getNewPasswordSet(int length) {
        ArrayList<String> passwordList = new ArrayList<>();
        for (int i=0; i<length; i++) {
            passwordList.add(generatePassword());
        }
        return passwordList;
    }

    /* =========================================
     *             NINJA BONUSES
     * ========================================= */
    /* ----------- Shuffle Array ------------ */
    /**
     * Ninja Bonus: shuffles an integer array in place using the Fisher-Yates algorithm.
     * Starts at the last index and swaps each element with a random element at or before it.
     * The bound i + 1 is exclusive, so an element can also stay where it is.
     * @param array the array to shuffle, which is modified in place.
     */
    public void shuffleArray(int[] array) {
        for (int i=array.length -1; i>0; i--) {
            int index = randomMachine.nextInt(i+1);
            // Hold the value at the random index, move the current value into that slot,
            // then place the held value at the current index.
            int tempVal = array[index]; // if        a = b
            array[index] = array[i];    // then make b = c
            array[i] = tempVal;         // finally,  c = a
        }
    }

    /* ----- Shuffle Array (Overloaded) ----- */
    /**
     * Ninja Bonus: overloaded version of shuffleArray for character arrays.
     * Serves as the helper that scrambles the characters in generatePassword(int length).
     * @param array the character array to shuffle, which is modified in place.
     */
    public void shuffleArray(char[] array) {
        for (int i=array.length -1; i>0; i--) {
            int index = randomMachine.nextInt(i+1);
            char tempVal = array[index];
            array[index] = array[i];
            array[i] = tempVal;
        }
    }

    /* --- Generate Password (Overloaded) --- */
    /**
     * Ninja Bonus: overloaded version of generatePassword that builds a password of a given length.
     * Alternates random letters and digits, then shuffles the characters so no pattern remains.
     * @param length the number of characters in the password.
     * @return A shuffled password of the requested length.
     */
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        for (int i=0; i<length; i++) {
            if (i%2 == 0) { // even index => add a random letter
                password.append(getRandomLetter());
            } else {        // odd index => add random digit
                password.append(randomMachine.nextInt(10));
            }
        }
        char[] pwCharacterArray = password.toString().toCharArray();
        shuffleArray(pwCharacterArray);
        String finalizedPassword = new String(pwCharacterArray);
        return finalizedPassword;
    }
}

