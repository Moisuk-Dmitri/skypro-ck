package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterTest {

    List<Flight> flightList;

    @BeforeEach
    void setup() {
        flightList = new ArrayList<>(List.of(
                new Flight(List.of(
                        new Segment(LocalDateTime.now().minusHours(5), LocalDateTime.now().plusHours(2)),
                        new Segment(LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(15)))),
                new Flight(List.of(
                        new Segment(LocalDateTime.now().minusHours(5), LocalDateTime.now().minusHours(10)),
                        new Segment(LocalDateTime.now().minusHours(8), LocalDateTime.now().plusHours(15)))),
                new Flight(List.of(
                        new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(10)),
                        new Segment(LocalDateTime.now().plusHours(11), LocalDateTime.now().minusHours(15))))
        ));
    }

    @Test
    @DisplayName("filter by sub current time test - positive")
    void shouldFilterBySubCurrentTime() {
        Assertions.assertEquals(new FilterBySubCurrentTimeImpl().filter(flightList), List.of(flightList.get(2)));
    }

    @Test
    @DisplayName("filter by sub current time test - negative (empty flights' list)")
    void shouldThrowExceptionWhenEmptyParamInSubCurrentTime() {
        Assertions.assertThrows(
                InvalidParameterException.class,
                () -> new FilterBySubCurrentTimeImpl().filter(List.of())
        );
    }

    @Test
    @DisplayName("filter by arrival before departure test - positive")
    void shouldFilterByArrivalDateBeforeDepartureDate() {
        Assertions.assertEquals(new FilterByArrivalDateBeforeDepartureDateImpl().filter(flightList), List.of(flightList.get(0)));
    }

    @Test
    @DisplayName("filter by arrival before departure test - negative (empty flights' list)")
    void shouldThrowExceptionWhenEmptyParamInArrivalDateBeforeDepartureDate() {
        Assertions.assertThrows(
                InvalidParameterException.class,
                () -> new FilterByArrivalDateBeforeDepartureDateImpl().filter(List.of())
        );
    }

    @Test
    @DisplayName("filter by transfer waiting time exceeded 2 hours test - positive")
    void shouldFilterByTransferWaitingTimeExceeded() {
        Assertions.assertEquals(new FilterByTransferWaitingTimeExceededImpl().filter(flightList), List.of(flightList.get(1), flightList.get(2)));
    }

    @Test
    @DisplayName("filter by transfer waiting time exceeded 2 hours test - negative (empty flights' list)")
    void shouldThrowExceptionWhenEmptyParamInTransferWaitingTimeExceeded() {
        Assertions.assertThrows(
                InvalidParameterException.class,
                () -> new FilterByTransferWaitingTimeExceededImpl().filter(List.of())
        );
    }
}
