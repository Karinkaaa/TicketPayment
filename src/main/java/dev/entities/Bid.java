package dev.entities;

import javax.persistence.*;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String routeNumber;
    private Long dateTime;

    @Enumerated(EnumType.STRING)
    private Status status = Status.IN_PROGRESS;

    public Bid() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public Long getDateTime() {
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

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("\n---BID---");

        sb.append("\nID: ").append(getId());
        sb.append("\nRoute number: ").append(getRouteNumber());
        sb.append("\nDateTime: ").append(getDateTime());
        sb.append("\nStatus: ").append(getStatus()).append("\n");

        return sb.toString();
    }
}
