package dev.controller;

import dev.entities.Bid;
import dev.entities.Status;
import dev.repository.BidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BidVerificationController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BidRepository bidRepository;

    @GetMapping(path = "/status")
    public Status bidStatus(@RequestBody Long id) {

        logger.info("Method bidStatus()");

        if (bidRepository.existsById(id)) {
            logger.info("ID [" + id + "] exists");
            Optional<Bid> bid = bidRepository.findById(id);
            return bid.get().getStatus();
        }

        logger.error("ID [" + id + "] is not exists");
        return null;
    }
}
