package com.roomreservation.reservationservice.rest;

import com.roomreservation.reservationservice.core.dto.*;
import com.roomreservation.reservationservice.core.service.ReservationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
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
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> createReservation(@Validated(ReservationDto.OnCreate.class) @RequestBody ReservationDto initialData) {
        reservationService.createReservation(initialData);
//        return new ResponseEntity<>("Sukces", HttpStatus.OK);
        return new ResponseEntity<>("Usługa jeszcze nie jest gotowa", HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/reservation/{id}")
    @ApiOperation(value = "Pobiera szczegóły rezerwacji o danym ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces", response = ReservationDto.class),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> getReservation(@ApiParam @Min(value = 1) @PathVariable Integer id) {
        ReservationDto result = reservationService.getReservation(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>("Usługa jeszcze nie jest gotowa", HttpStatus.NOT_IMPLEMENTED);

    }

    @GetMapping("/reservations/{page}")
    @ApiOperation(value = "Zwraca listę wszystkich rezerwacji, uwzględniając paginację i sortowanie.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces", responseContainer = "List", response = ReservationDto.class),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żadaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> getReservations(
            @ApiParam @Min(1) @NotNull @PathVariable Integer page,
            @ApiParam @Min(1) @NotNull @RequestBody Integer size) {
        List<?> result = reservationService.getReservations(page, size);
//        return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>("Usługa jeszcze nie jest gotowa", HttpStatus.NOT_IMPLEMENTED);
    }

// TODO: Later implement this endpoint
//    @GetMapping("/reservations/")
//    @ApiOperation(value = "Zwraca listę wszystkich rezerwacji, spełniających dane kryteria, uwzględniając paginację.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Sukces", response = ReservationDto.class),
//            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
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
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
//            @ApiResponse(code = 403, message = "Błąd autoryzacji"), //TODO: After JWT implementation
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> updateReservation(@ApiParam @PathVariable @NotNull Integer id, @ApiParam @PathVariable @NotNull ReservationDto newData) {
        reservationService.updateReservation(id, newData);
//        return new ResponseEntity<>("Sukces", HttpStatus.OK);
        return new ResponseEntity<>("Usługa jeszcze nie jest gotowa", HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/reservation/{id}")
    @ApiOperation(value = "Anuluje rezerwację o danym ID.")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Sukces"),
//            @ApiResponse(code = 403, message = "Błąd autoryzacji"), //TODO: After JWT implementation
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> cancelReservation(@ApiParam @PathVariable Integer reservationId) {
        reservationService.cancelReservation(reservationId);
//        return new ResponseEntity<>("Sukces", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PatchMapping("/reservation/{id}/status")
    @ApiOperation(value = "Aktualizuje status rezerwacji o danym ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
//            @ApiResponse(code = 403, message = "Błąd autoryzacji"), //TODO: After JWT implementation
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 501, message = "Usługa jeszcze nie jest gotowa")
    })
    public ResponseEntity<?> changeReservationStatus(@ApiParam @PathVariable Integer reservationId, @RequestBody String status) {
        reservationService.changeReservationStatus(reservationId, status);
//        return new ResponseEntity<>("Sukces", HttpStatus.OK);
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
