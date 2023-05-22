package com.roomreservation.reservationservice.rest;

import com.roomreservation.reservationservice.core.dto.*;
import com.roomreservation.reservationservice.core.service.ReservationService;
import io.swagger.annotations.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    @ApiOperation(value = "Tworzy nową rezerwację")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 204, message = "Brak dostępnych pokoi spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> createReservation(
            @RequestBody Date startDate,
            @RequestBody Integer daysNum,
            @RequestBody Integer guestNum,
            @RequestBody String roomType,
            @RequestBody List<String> services,
            @RequestBody Double price,
            @RequestBody String currency,
            @RequestBody Integer clientId) {
        try {
            ReservationDto.builder()
                    .startDate(startDate)
                    .daysNum(daysNum)
                    .guestNum(guestNum)
                    .roomType(RoomType.valueOf(roomType))
                    .services(services.stream().map(Service::valueOf).collect(Collectors.toList()))
                    .price(price)
                    .currency(Currency.valueOf(currency))
                    .status(Status.UNPAID)
                    .clientId(clientId)
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Nieprawidłowe dane w żądaniu");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        //    try {
        //        Rezerwacja createdRezerwacja = rezerwacjaService.createRezerwacja(rezerwacja); // raczej konstruktor lub builder
        //        return new ResponseEntity<>(createdRezerwacja, HttpStatus.OK);
        //    } catch (InvalidDataException e) {
        //        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        //    } catch (NoAvailableRoomException e) {
        //        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        //    } catch (Exception e) {
        //        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        //    }}
    }

    @GetMapping("/reservation/{id}")
    @ApiOperation(value = "Pobiera szczegóły rezerwacji o danym ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces", response = ReservationDto.class),
            @ApiResponse(code = 204, message = "Brak rezerwacji o danym ID."),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> getReservation(@ApiParam @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//            ReservationDto reservationDto = reservationService.getReservation(id); // TODO Implement service method
//            return new ResponseEntity<>(reservationDto, HttpStatus.OK);
//        } catch (InvalidDataException e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        } catch (ResourceNotFoundException e) {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reservations/{page}")
    @ApiOperation(value = "Zwraca listę wszystkich rezerwacji, uwzględniając paginację i sortowanie.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces", response = ReservationDto.class),
            @ApiResponse(code = 204, message = "Brak rezerwacji spełniających dane kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żadaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> getReservations(
            @ApiParam @PathVariable Integer page,
            @ApiParam @RequestBody Integer size,
            @ApiParam @RequestBody String direction,
            @ApiParam @RequestBody String... sortBy) {
        //return new ResponseEntity<>(reservationService.getReservations(page, size, direction, sortBy), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

// TODO: Later implement this endpoint
//    @GetMapping("/reservations/")
//    @ApiOperation(value = "Zwraca listę wszystkich rezerwacji, spełniających dane kryteria, uwzględniając paginację i sortowanie.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Sukces", response = ReservationDto.class),
//            @ApiResponse(code = 204, message = "Brak rezerwacji spełniających dane kryteria"),
//            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żadaniu"),
//            @ApiResponse(code = 500, message = "Błąd serwera"),
//            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
//    })
//    public ResponseEntity<?> getReservationsWithParams(@ApiParam @PathVariable Integer id) {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }

    @PutMapping("/reservation/{id}")
    @ApiOperation(value = "Aktualizuje rezerwację o danym ID.")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
//            @ApiResponse(code = 403, message = "Błąd autoryzacji"), //TODO: After JWT implementation
            @ApiResponse(code = 404, message = "Brak rezerwacji o danym ID."),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> updateReservation(@ApiParam @PathVariable Integer id) {
        // Zwraca zaktualizowany obiekt rezerwacji.
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/reservation/{id}")
    @ApiOperation(value = "Anuluje rezerwację o danym ID.")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Sukces"),
//            @ApiResponse(code = 403, message = "Błąd autoryzacji"), //TODO: After JWT implementation
            @ApiResponse(code = 404, message = "Brak rezerwacji o danym ID."),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> cancelReservation(@ApiParam @PathVariable Integer reservationId) {
        // zmiana stanu na Canceled
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PatchMapping("/reservation/{id}/status")
    @ApiOperation(value = "Aktualizuje status rezerwacji o danym ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
//            @ApiResponse(code = 403, message = "Błąd autoryzacji"), //TODO: After JWT implementation
            @ApiResponse(code = 404, message = "Brak rezerwacji o danym ID."),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> changeReservationStatus(@ApiParam @PathVariable Integer reservationId, @RequestBody String status) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
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
}
