package com.learn.alfredbot;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Alfred's core logic — greetings, date announcement, and canned
 * conversation responses. Encapsulates the "how"; AlfredTest exercises it.
 */
public class AlfredQuotes {
    // --------- Guest Greeting --------- //

    /* Internal helper holding the plain greeting text.
     * Private since guestGreeting() is the only intended entry point. */
    private String basicGreeting() {
        return "\nHello, lovely to see you. How are you?";
    }

    /* Returns Alfred's default greeting, no personalization. */
    public String guestGreeting() {
        return basicGreeting();
    }

    /**
     * Returns a greeting for a named guest using a caller-supplied day period.
     * @param dayPeriod time of day to reference (e.g., "morning")
     * @param name the guest's name
     */
    public String guestGreeting(String dayPeriod, String name) {
        return String.format("Good %s, %s. How can I assist you today?", dayPeriod, name);
    }

    /**
     * Returns a greeting for a named guest, auto-detecting the day period
     * (morning/afternoon/evening) from the current system time.
     * @param name the guest's name
     */
    public String guestGreeting(String name) {
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        String dayPeriod;
        if ( currentHour >= 0 && currentHour < 12 ) {
            dayPeriod = "morning";
        } else if ( currentHour >= 12 && currentHour < 18 ) {
            dayPeriod = "afternoon";
        } else {
            dayPeriod = "evening";
        }
        return String.format("Good %s, %s. How can I assist you today?", dayPeriod, name);
    }

    // --- Date Announcement Greeting --- //

    /* Announces the current date/time using Java's default Date format. */
    public String dateAnnouncement() {
        return String.format("It is currently %s.\n", new Date());
    }

    /* Announces the current date/time in a human-readable format, fixed to EST. */
    public String dateAnnouncementSimplified() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy 'and' hh:mm a z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
        return String.format("It is currently %s.\n", dateFormat.format( new Date() ));
    }

    // ----- Respond to Conversation ---- //

    /**
     * Generates a canned response based on keywords found in the input.
     * Returns a snarky reply for "Alexis", an obliging reply for "Alfred",
     * and a neutral fallback otherwise.
     * @param conversation the input text to scan for keywords
     */
    public String respondToConversation(String conversation) {
        if (conversation.contains("Alexis")) {
            return "Right way. She certainly isn't sophisticated enough for that.";
        } else if (conversation.contains("Alfred")) {
            return "At your service. As you wish, naturally.";
        } else {
            return "Right. And with that, I shall retire.";
        }
    }
}
