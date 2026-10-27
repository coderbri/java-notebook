package org.learn.routingpractice.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @file: controllers/MainController.java
 * @description: Handles all requests under /chengyu and returns plain-text idioms.
 */
@RestController
@RequestMapping("/chengyu")
public class MainController {

    /* Pool of extra chengyu served by the /random route. */
    private final List<String> randomChengyu = List.of(
            "Random chengyu: “水滴石穿。” （Shuǐ dī shí chuān）— Dripping water pierces stone. " +
                    "Small daily effort, like one commit or one practice problem a day, adds up to big results over time.",
            "Random chengyu: “厚积薄发。” （Hòu jī bó fā）— Gather deeply, then release gradually. " +
                    "The skills you quietly build now will be ready when opportunity arrives, so keep learning.",
            "Random chengyu: “乘风破浪。” （Chéng fēng pò làng）— Ride the wind and break the waves. " +
                    "Meet challenges head-on and use the momentum of change to move forward, even when the waters are rough."
    );

    /* Generator used to pick a random index from the pool. */
    private final Random RANDOM = new Random();

    // ==================================================== //

    /* Welcomes the user at localhost:8080/chengyu. */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String welcome() {
        return "<h1>Welcome to the Chéngyǔ Web App!</h1>" +
                "<h3>Explore:</h3>" +
                "<ol>" +
                    "<li>/chengyu/yesterday,</li>" +
                    "<li>/chengyu/today, or</li>" +
                    "<li>/chengyu/tomorrow.</li>" +
                "</ol>";
    }

    /* Returns yesterday's motivation at localhost:8080/chengyu/yesterday. */
    @RequestMapping(value = "/yesterday", method = RequestMethod.GET)
    public String yesterday() {
        return "Yesterday's chengyu: “百折不挠。” （Bǎi zhé bù náo）— Though bent a hundred times, never yield. " +
                "Every bug you fixed and every setback you pushed through is proof that you can keep going, " +
                "and yesterday's struggles are today's strength.";
    }

    /* Returns today's motivation at localhost:8080/chengyu/today. */
    @RequestMapping(value = "/today", method = RequestMethod.GET)
    public String today() {
        return "Today's chengyu: “守得云开见月明。” （Shǒu dé yún kāi jiàn yuè míng）— Stay until the clouds clear, and the moon appears. " +
                "When the code won't compile or progress feels slow, keep showing up, " +
                "because clarity often arrives right after the hardest stretch.";
    }

    /* Returns tomorrow's motivation at localhost:8080/chengyu/tomorrow. */
    @RequestMapping(value = "/tomorrow", method = RequestMethod.GET)
    public String tomorrow() {
        return "Tomorrow's chengyu: “前程似锦。” （Qián chéng sì jǐn）— A future as bright as brocade. " +
                "Every skill you build today is another thread in that brocade, so your coding journey is wide open.";
    }

    /* Returns a random motivation at localhost:8080/chengyu/random. */
    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public String random() {
        return randomChengyu.get(RANDOM.nextInt(randomChengyu.size()));
    }
}
