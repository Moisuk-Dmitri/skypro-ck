package com.gridnine.testing;

import java.security.InvalidParameterException;
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
            int transferWaitingTimeMin = 0;
            for (int currSegment = 0; currSegment < flight.getSegments().size() - 1; currSegment++) {

                int departureTimeMin = flight.getSegments().get(currSegment + 1).getDepartureDate().getHour() * 60 +
                        flight.getSegments().get(currSegment + 1).getDepartureDate().getMinute();
                int arrivalTimeMin = flight.getSegments().get(currSegment).getArrivalDate().getHour() * 60 +
                        flight.getSegments().get(currSegment).getArrivalDate().getMinute();
                transferWaitingTimeMin += Math.abs(departureTimeMin - arrivalTimeMin);

                if (transferWaitingTimeMin > 120) {
                    result.add(flight);
                    break;
                }
            }
        }

        return result;
    }

}
