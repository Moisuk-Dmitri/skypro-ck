package com.gridnine.testing;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

public class FilterBySubCurrentTimeImpl implements FlightFilter {

    @Override
    public List<Flight> filter(final List<Flight> flights) {
        if (flights.isEmpty()) {
            throw new InvalidParameterException("Empty flight list");
        }

        return flights.stream()
                .parallel()
                .filter(flight -> LocalDateTime.now().isAfter(flight.getSegments().getFirst().getDepartureDate()))
                .toList();
    }

}
