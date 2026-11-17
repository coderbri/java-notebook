package org.learn.pathvariables.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @file: controller/CustomErrorController
 * @descriptoin: Replaces the Whitelabel Error page with a chengyu-themed error page.
 */
@RestController
public class CustomErrorController implements ErrorController {

    /**
     * Handles every error Spring forwards to /error and keeps the real HTTP status code.
     */
    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object attribute = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int status = (attribute == null) ? 500 : Integer.parseInt(attribute.toString());

        String body = switch (status) {
            case 404 -> page("404 Not Found",
                            "That page doesn't exist, so check your URL for typos. " +
                            "Try /chengyu, /chengyu/today, /chengyu/travel/kyoto, or /chengyu/lotto/8.");
            case 400 -> page("400 Bad Request",
                            "That value isn't valid here, so double-check it. " +
                            "For example, /chengyu/lotto/ needs a whole number like 8.");
            default -> page(status + " Something Went Wrong",
                            "Something went wrong, but every setback is a chance to learn. Please try again.");
        };

        return ResponseEntity.status(status).contentType(MediaType.TEXT_HTML).body(body);
    }

    /**
     * Builds the HTML for an error page with a heading, a message, and a link home.
     */
    private String page(String title, String message) {
        return "<h1>" + title + "</h1>" +
                "<p>" + message + "</p>" +
                "<p><a href=\"/chengyu\">Return to the welcome page</a></p>";
    }
}