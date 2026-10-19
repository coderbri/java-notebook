package com.learn.hashmatique;

import java.util.HashMap;

/**
 * @file Hashmatique.java
 * @description: Demonstrates a HashMap by storing a track list of song titles and lyrics.
 * including adding entries, retrieving one by its key, and printing every entry.
 */
public class Hashmatique {
    public static void main(String[] args) {

        /** Maps each track title (key) to its lyrics (value). */
        HashMap<String, String> trackList = new HashMap<>();

        /** Adds four songs with put(key, value), each stored by its title. */
        trackList.put("A Little Sweet", "You're the right character at the right time...");
        trackList.put("What You Mean to Me", "These words are as light as snowflakes...");
        trackList.put("Coming", "I've got a million whys...");
        trackList.put("Firework", "Passing through the world...");

        /** Pulls out one of the songs by its track title. */
        System.out.printf(" > Pulled-out track: %s\n\n", trackList.get("Coming"));

        printTrackList(trackList);
    }
    /**
     * Prints every track in the list as its title followed by its lyrics.
     * Iterates over entries rather than keys, so each value arrives with its key and no second lookup is needed.
     * @param trackList the map of track titles to lyrics.
     */
    public static void printTrackList(HashMap<String, String> trackList) {
        for (HashMap.Entry<String, String> entry : trackList.entrySet()) {
            System.out.println(
                    String.format("Track: %s — Lyrics: %s\n", entry.getKey(), entry.getValue())
            );
        }
    }
}
