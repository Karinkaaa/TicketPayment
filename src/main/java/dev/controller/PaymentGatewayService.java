package dev.controller;

import dev.entities.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PaymentGatewayService {

    @GetMapping("/service/status")
    @ResponseBody
    public Status bidStatus() {

        Status[] statuses = {Status.ERROR, Status.SUCCESS};
        return statuses[new Random().nextInt(2)];
    }
}
