package com.gridnine.testing;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println(flights);
        System.out.println("Вылет до текущего момента времени: " + new FilterBySubCurrentTimeImpl().filter(flights));
        System.out.println("Сегменты с датой прилёта раньше даты вылета: " + new FilterByArrivalDateBeforeDepartureDateImpl().filter(flights));
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа: " + new FilterByTransferWaitingTimeExceededImpl().filter(flights));
    }

}
