package com.roomreservation.reservationservice.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationServiceAPI {

    @PostMapping
    public ResponseEntity<?> reserveRoom(
            @RequestBody Integer startDate,
            @RequestBody Integer daysNum,
            @RequestBody Integer guestNum,
            @RequestBody String roomType,
            @RequestBody String clientId // client should be created in another request and service
    ) {
        // Na poziomie API gateway powinno być jeszcze zweryfikowana tożsamość użytkownika
        return new ResponseEntity<>(HttpStatus.ACCEPTED); // or some other
    }

}
