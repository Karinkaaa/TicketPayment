package dev.repository;

import dev.entities.Bid;
import dev.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByStatus(Status status);
}
