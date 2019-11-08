package dev.controller;

import dev.entities.Bid;
import dev.repository.BidRepository;
import dev.validation.BidValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BidAcceptanceController {

    private Logger logger = LoggerFactory.getLogger(BidAcceptanceController.class);

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BidValidator validator;

    @GetMapping(produces = "application/json")
    public List<Bid> mainPage() {

        logger.info("Main page");
        return bidRepository.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Long save(@RequestBody Bid bid) {

        logger.info("Method save()");

        if (validator.isValid(bid)) {

            logger.info("Bid is valid");
            bidRepository.save(bid);

            return bid.getId();
        }

        logger.error("Bid is not valid");
        return null;
    }
}
