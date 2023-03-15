package com.roomreservation.reservationservice.rest;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ReservationController {

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
