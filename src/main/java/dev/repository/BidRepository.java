package dev.repository;

import dev.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
