package com.gridnine.testing;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Фильтр 1 (Вылет до текущего момента времени): " + new FilterBySubCurrentTimeImpl().filter(flights));
        System.out.println("Фильтр 2 (Сегменты с датой прилёта раньше даты вылета): " + new FilterByArrivalDateBeforeDepartureDateImpl().filter(flights));
        System.out.println("Фильтр 3 (Перелеты, где общее время, проведённое на земле, превышает два часа): " + new FilterByTransferWaitingTimeExceededImpl().filter(flights));
    }

}
