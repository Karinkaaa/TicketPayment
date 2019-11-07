package dev.controller;

import dev.entities.Bid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String greeting() {

        Bid bid = new Bid("123456");
        return "MAIN PAGE " + bid.getDateTime() + " Route: " + bid.getRouteNumber();
    }
}
