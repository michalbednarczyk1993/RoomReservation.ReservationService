package com.roomreservation.reservationservice.rest;

import com.roomreservation.reservationservice.core.dto.ReservationDto;
import com.roomreservation.reservationservice.core.service.TestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Profile("!prod")
@RestController
public class TestController {

    @Value("${service.room.url}")
    String roomServiceUrl;

    final TestService testService;

    final RetryableRequestService retryService;

    public TestController(TestService testService, RetryableRequestService retryService) {
        this.testService = testService;
        this.retryService = retryService;
    }

    @PostMapping("/test/save")
    @ApiOperation(value = "Zapisuje dane testowe w bazie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 400, message = "Nieprawidłowe dane w żądaniu"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> saveData() {
        testService.saveRecord();
        return new ResponseEntity<>("Sukces", HttpStatus.OK);
    }

    @GetMapping("/test/read")
    @ApiOperation(value = "Odczytuje ostatnio zapisane dane testowe w bazie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
    })
    public ResponseEntity<?> readData() {
        testService.readData();
        return new ResponseEntity<>("Sukces", HttpStatus.OK);
    }

    @GetMapping("/testNetworking")
    @ApiOperation(value = "Testuje połączenie z innym mikroserwisem")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sukces"),
            @ApiResponse(code = 500, message = "Błąd serwera"),
            @ApiResponse(code = 503, message = "Błąd połączenia z zewnętrznym serwisem")
    })
    public ResponseEntity<?> testNetworking() {
        String url = roomServiceUrl + "/mock";
        ResponseEntity<?> result = retryService.executeWithRetry(url, String.class);
        return result;
    }


}
