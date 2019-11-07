package dev.validation;

import dev.entities.Bid;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BidValidator {

    private boolean isValidDateTime(LocalDateTime dateTime) {

        LocalDateTime nowDateTime = LocalDateTime.now();

        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();

        boolean isValidYear = (year >= nowDateTime.getYear());
        boolean isValidMonth = (month >= nowDateTime.getMonthValue());
        boolean isValidDay = (day >= nowDateTime.getDayOfMonth());
        boolean isValidHour = (hour >= nowDateTime.getHour());
        boolean isValidMinute = (minute >= nowDateTime.getMinute());

        return isValidYear
                && isValidMonth
                && isValidDay
                && isValidHour
                && isValidMinute;
    }

    private boolean isValidRouteNumber(String routNumber) {

        if (routNumber == null) return false;
        return routNumber.length() == 8;
    }

    public boolean isValid(Bid bid) {

        if (bid == null) return false;

        return isValidDateTime(bid.getDateTime())
                && isValidRouteNumber(bid.getRouteNumber());
    }
}
