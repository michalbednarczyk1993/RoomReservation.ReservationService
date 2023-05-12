package com.roomreservation.reservationservice.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.time.Year;

@RestController
public class StatisticsServiceAPI {

    /**
     * Return statistic data with popularity of room types with different additional services over time.
     * This data distinguish interest in specified room types (represented by getRoomTypeDetails() request) and
     * number of reserved rooms. Data structure will be as below:
     *
     * Na wykresie może wybrać jakie dane chce prezentować oraz na jakiej przestrzeni czasu
     * czyli tutaj muszę zmienić
     * {
     *     room_type : ...,
     *     services : {...},
     *     reserved : ...,
     *     searched : ...
     * }
     *
     * @return data in JSON format and request status
     */
    @GetMapping
    public ResponseEntity<?> showReservationQueryStats() {
       return null;
    }

    @GetMapping
    public ResponseEntity<?> getOccupancy(Year year, Month month) {
        return null;
    }

}
