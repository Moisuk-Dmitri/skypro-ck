package com.gridnine.testing;

import java.security.InvalidParameterException;
import java.sql.Time;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FilterByTransferWaitingTimeExceededImpl implements FlightFilter {

    /**
     * Method for filtering by rule: transfer waiting time is above 2 hours
     *
     * @param flights List of flights to be filtered
     *
     * @return List of flights, satisfying the condition
     */
    @Override
    public List<Flight> filter(final List<Flight> flights) {
        if (flights.isEmpty()) {
            throw new InvalidParameterException("Empty flight list");
        }

        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            long transferWaitingTime = 0;
            for (int currSegment = 0; currSegment < flight.getSegments().size() - 1; currSegment++) {
                transferWaitingTime += ChronoUnit.HOURS.between(flight.getSegments().get(currSegment).getArrivalDate(),
                        flight.getSegments().get(currSegment + 1).getDepartureDate());
            }

            if (transferWaitingTime <= 2) {
                result.add(flight);
            }
        }

        return result;
    }

}
