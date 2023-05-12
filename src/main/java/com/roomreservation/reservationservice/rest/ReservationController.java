package com.roomreservation.reservationservice.rest;

import com.roomreservation.reservationservice.core.dto.ReservationDto;
import com.roomreservation.reservationservice.core.entities.Reservation;
import com.roomreservation.reservationservice.core.service.ReservationService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/testNetworking")
    public void testNetworking() {
        // http://container-name:container-port/endpoint
        // String baseUrl = "http://room-service:8080/mock"; // in case of deploying to Docker
        String baseUrl = "http://localhost:8080/mock"; // in case of running locally
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try{
            response = restTemplate.getForEntity(baseUrl, String.class);
            System.out.println(response.getBody());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO
    /**
     * Create new record in reservations table with provided parameters: (startDate, daysNum, guestNum, roomType, clientId, status)
     * In case of startDate is before or is more than a year after current date,
     * in case of daysNum less or equal to 0,
     * in case of guestNum equal to 0/null or that exceed roomType capacity,
     * in case of no available room types in selected period of time,
     * in case of no user registered in DB with selected ID,
     * then there will be returned HTTP 400 "Bad Request" error message with detailed description.
     *
     * @param startDate - first day of stay passed in sql.Date format
     * @param daysNum   - number of days of reservation
     * @param guestNum  - number of guests
     * @param roomType  - one of possible room types: Pojedynczy, Podwojny, Apartament
     * @param services  - list of selected additional services, of selected: śniadanie, obiadokolacje, pobyt all-inclusive,
     *                  codzienne sprzątanie, wypożyczenie sprzętu sportowego
     * @param price     - ostateczna cena za rezerwację
     * @param clientId  - id klienta, który jest przypisany do rezerwacji
     * @return status of request and reservationId
     */
    @PostMapping
    public ResponseEntity<?> reserveRoom(
            @RequestBody Integer startDate,
            @RequestBody Integer daysNum,
            @RequestBody Integer guestNum,
            @RequestBody String roomType,
            @RequestBody List<String> services,
            @RequestBody Double price,
            @RequestBody String currency,
            @RequestBody Integer clientId // client should be created in another request and service
    ) {
        // Na poziomie API gateway powinno być jeszcze zweryfikowana tożsamość użytkownika

        // Wyślij potwierdzenie rezerwacji

        return new ResponseEntity<>(HttpStatus.ACCEPTED); // or some other
    }

    /**
     * Return list of all reservations with parameters such as:
     * status, list of chosen services, room number, client contact data.
     *
     * @return list of all reservations in JSON format
     */
    @GetMapping("/all")
    public ResponseEntity<?> getReservationsList(@PathVariable Integer page, Integer size, String direction, String... sortBy) {
        return new ResponseEntity<>(reservationService.getReservations(page, size, direction, sortBy), HttpStatus.OK);
    }

    // TODO
    /**
     * Return list of all client reservations with parameters such as:
     * status, list of chosen services, room number, client contact data.
     *
     * @param clientId - ID of client that is related to this reservation
     * @return list of all client reservations in JSON format
     */
    @GetMapping
    public ResponseEntity<?> getClientReservationsList(@RequestBody Integer clientId) {
        return null;
    }

    // TODO
    /**
     * Within this request it is possible to change status of reservation.
     * There are no rule which limit expected status based on current one.
     *
     * @param reservationId - ID of reservation
     * @param status - expected status
     * @return request status
     */
    @PatchMapping
    public ResponseEntity<?> changeReservationStatus(Integer reservationId, String status) {
        return null;
    }

    // TODO
    /**
     * Within this request it is possible to cancel pointed reservation.
     * It is impossible to cancel realized or canceled reservation.
     * In such case service will respond with 400 "Bad Request" HTTP message.
     *
     * @param reservationId - ID of reservation
     * @return request status
     */
    @PatchMapping
    public ResponseEntity<?> cancelReservation(Integer reservationId) {
        return null;
    }

}
