package dev.validation;

import dev.entities.Bid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BidValidator {

    private Logger logger = LoggerFactory.getLogger(BidValidator.class);

    private int[] getArrayFromDateTime(Long dateTime) {

        int[] array = new int[5];
        int divider;

        for (int i = 0; i < array.length; i++) {

            if (i != 2) divider = 100;
            else divider = 10000;

            array[i] = (int) (dateTime % divider);
            dateTime /= divider;
        }

        logger.info("Created array from variable 'dateTime'");
        return array;
    }

    private LocalDateTime getDateTimeFromArray(int[] array) {

        int counter = 0;
        int minute = array[counter++];
        int hour = array[counter++];
        int year = array[counter++];
        int month = array[counter++];
        int day = array[counter];

        logger.info("Created variable 'localDateTime' from array");
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    private boolean isValidDateTime(Long dateTime) {

        int[] array = getArrayFromDateTime(dateTime);

        LocalDateTime localDateTime = getDateTimeFromArray(array);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime currentDateTimePlusYear = currentDateTime.plusYears(1);

        return localDateTime.isAfter(currentDateTime)
                && localDateTime.isBefore(currentDateTimePlusYear);
    }

    private boolean isValidRouteNumber(String routNumber) {

        if (routNumber == null) {
            logger.error("Variable 'routNumber' is null");
            return false;
        }

        if (routNumber.length() != 8) {
            logger.error("Length of variable 'routNumber' > or < 8");
            return false;
        }

        char ch;
        for (int i = 0; i < routNumber.length(); i++) {
            ch = routNumber.charAt(i);

            if (!Character.isAlphabetic(ch) && !Character.isDigit(ch)) {
                logger.error("Incorrect symbol in variable 'routeNumber'");
                return false;
            }
        }

        logger.info("Variable 'routeNumber' is valid");
        return true;
    }

    public boolean isValid(Bid bid) {

        if (bid == null) {
            logger.error("Object 'bid' is null");
            return false;
        }
        return isValidDateTime(bid.getDateTime())
                && isValidRouteNumber(bid.getRouteNumber());
    }
}
