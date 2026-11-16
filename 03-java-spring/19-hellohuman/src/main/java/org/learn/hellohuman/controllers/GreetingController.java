package org.learn.hellohuman.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @file: controllers/GreetingController.java
 * @description: Handles greeting requests under /hello using optional query parameters.
 */
@RestController
@RequestMapping("/hello")
public class GreetingController {

    /* Caps the repeat count so a huge "times" value can't flood the response. */
    private static final int MAX_TIMES = 100;

    /**
     * Returns a greeting at localhost:8080/hello.
     * Examples: /hello, /hello?name=Ava, /hello?name=Ava&last_name=Lovelace,
     * and /hello?name=Ava&times=3.
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "last_name", required = false) String lastName,
            @RequestParam(value = "times", required = false) Integer times
    ) {
        String greeting = "Hello, " + buildName(name, lastName);
        int count = (times == null || times < 1) ? 1 : Math.min(times, MAX_TIMES);
        return String.join("\n", Collections.nCopies(count, greeting));
    }

    /**
     * Combines the first and last names, falling back to "Human" when neither is provided.
     */
    private String buildName(String name, String lastName) {
        boolean hasFirst = name != null && !name.isBlank();
        boolean hasLast = lastName != null && !lastName.isBlank();

        if (hasFirst && hasLast) {
            return name.trim() + " " + lastName.trim();
        } else if (hasFirst) {
            return name.trim();
        } else if (hasLast) {
            return lastName.trim();
        }
        return "Human";
    }
}