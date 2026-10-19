package com.learn.exceptionslist;

import java.util.ArrayList;

/**
 * @file ExceptionsList.java
 * @description: Demonstrates safe type casting on an ArrayList of mixed types.
 * including strings, characters, and integers, using instanceof to avoid a ClassCastException.
 */
public class ExceptionsList {
    public static void main(String[] args) {

        ArrayList<Object> myList = new ArrayList<>();
        myList.add("Hello, World");
        myList.add('z');
        myList.add(4);
        myList.add("13");

        for (int i = 0; i < myList.size(); i++) {
            Object obj = myList.get(i);

            /**
             * Prevents the exception entirely using defensive type-checking.
             * The cast only runs when the object is already an Integer.
             */
            if (obj instanceof Integer) {
                Integer castedValue = (Integer) obj;
                System.out.println(" > Successfully casted index " + i + ": " + castedValue);
            } else {
                System.out.println(" >> Skipped index " + i + ": Value '" + obj + "' is not an Integer.");
            }
        }
        System.out.println("Finished!");
    }
}
