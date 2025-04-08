package com.gridnine.testing;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface FlightFilter {

    List<Flight> filter(List<Flight> flights);

}
