package dev.controller;

import dev.entities.Bid;
import dev.repository.BidRepository;
import dev.validation.BidValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BidAcceptanceController {

    private Logger logger = LoggerFactory.getLogger(BidAcceptanceController.class);

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BidValidator validator;

    @GetMapping
    public String mainPage() {

        logger.info("main page");
        Bid bid = new Bid("123456");
        bidRepository.save(bid);
        return "MAIN PAGE " + bid.getDateTime() + " Route: " + bid.getRouteNumber();
    }

    @PostMapping("/{routeNumber}")
    public Long save(@PathVariable String routeNumber) {

        Bid bid = new Bid(routeNumber);

        logger.info("method save()");
        logger.info(bid.toString());

        if (validator.isValid(bid)) {

            logger.info("try save() - is valid");
            bidRepository.save(bid);
            return bid.getId();
        }
        logger.info("NOT VALID");
        return null;
    }
}
