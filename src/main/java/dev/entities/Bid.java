package dev.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String routeNumber;
    private LocalDateTime dateTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status;

    public Bid(String routeNumber) {
        this.status = Status.IN_PROGRESS;
        this.routeNumber = routeNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
