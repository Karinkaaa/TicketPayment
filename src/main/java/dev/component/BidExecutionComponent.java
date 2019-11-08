package dev.component;

import dev.entities.Bid;
import dev.entities.Status;
import dev.repository.BidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class BidExecutionComponent {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BidRepository bidRepository;

    @Value("${payService}")
    private String payService;

    @Scheduled(fixedRate = 60 * 1000)
    public void payment() throws IOException, InterruptedException {

        logger.info("Method payment()");

        List<Bid> bids = bidRepository.findByStatus(Status.IN_PROGRESS);
        Status status = getStatus();

        logger.info("New status: " + status);

        for (Bid bid : bids) {
            bid.setStatus(status);
            bidRepository.save(bid);
            return;
        }
    }

    private Status getStatus() throws IOException, InterruptedException {

        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(payService))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Status code: " + String.valueOf(response.statusCode()));
        logger.info("Response body: " + String.valueOf(response.body()));

        return Status.valueOf(response.body().replaceAll("\"", ""));
    }
}
