package com.gridnine.testing;

import java.security.InvalidParameterException;
import java.util.List;

public class FilterByArrivalDateBeforeDepartureDateImpl implements FlightFilter {

    /**
     * Method for filtering by rule: arrival date is before departure date
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

        return flights.stream()
                .parallel()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .toList();
    }

}
