package com.roomreservation.reservationservice.rest;

import com.roomreservation.reservationservice.core.dto.*;
import com.roomreservation.reservationservice.core.service.ReservationService;
import com.roomreservation.reservationservice.core.service.TestService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
public class ReservationController {

    final ReservationService reservationService;
    final TestService testService;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public ReservationController(ReservationService reservationService, TestService testService) {
        this.reservationService = reservationService;
        this.testService = testService;
    }

    @PostMapping("/reservation")
    @ApiOperation(value = "Tworzy nową rezerwację")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> createReservation(@RequestBody @Valid ReservationDto initialData) {
        logger.error("Create reservation request send with data" + initialData.toString());
        reservationService.createReservation(initialData);
        return new ResponseEntity<>("Sukces", HttpStatus.OK);
    }

    @GetMapping("/reservation/{id}")
    @ApiOperation(value = "Pobiera szczegóły rezerwacji o danym ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces", response = ReservationDto.class),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> getReservation(@PathVariable @ApiParam @Positive Integer id) {
        ReservationDto result = reservationService.getReservation(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/reservation/all/{page}")
    @ApiOperation(value = "Zwraca listę wszystkich rezerwacji, uwzględniając paginację i sortowanie.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces", responseContainer = "List", response = ReservationDto.class),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żadaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> getReservations(
            @ApiParam @Positive @PathVariable Integer page,
            @ApiParam @Positive @RequestBody Integer size) {
        List<?> result = reservationService.getReservations(page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/reservation/all/{userId}/{page}")
    @ApiOperation(value = "Zwraca listę rezerwacji dla danego użytkownika")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 403, message = "Błąd autoryzacji"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> getUserReservations(@ApiParam @Positive @PathVariable Integer userId,
                                                 @ApiParam @Positive @PathVariable Integer page,
                                                 @ApiParam @Positive @RequestBody Integer size) {
        List<ReservationDto> reservations = reservationService.getUserReservations(userId, page, size);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PutMapping("/reservation/{id}")
    @ApiOperation(value = "Aktualizuje rezerwację o danym ID.")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 403, message = "Błąd autoryzacji"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> updateReservation(@ApiParam @PathVariable @NotNull Integer id,
                                               @ApiParam @PathVariable @NotNull ReservationDto newData) {
        reservationService.updateReservation(id, newData);
        return new ResponseEntity<>("Sukces", HttpStatus.OK);
    }

    @DeleteMapping("/reservation/{id}")
    @ApiOperation(value = "Anuluje rezerwację o danym ID.")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 403, message = "Błąd autoryzacji"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> cancelReservation(@ApiParam @PathVariable Integer reservationId) {
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>("Sukces", HttpStatus.OK);
    }

    @PatchMapping("/reservation/{id}/status")
    @ApiOperation(value = "Aktualizuje status rezerwacji o danym ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 403, message = "Błąd autoryzacji"),
            @ApiResponse(code = 204, message = "Brak dostępnych zasobów spełniających kryteria"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> changeReservationStatus(@ApiParam @PathVariable Integer reservationId,
                                                     @RequestBody String status
    ) {
        reservationService.changeReservationStatus(reservationId, status);
        return new ResponseEntity<>("Sukces", HttpStatus.OK);
    }
}
