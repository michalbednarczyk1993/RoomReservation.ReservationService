package com.roomreservation.reservationservice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RetryableRequestService {

    @Value("${app.retry.max.count}")
    int maxRetryCount;

    @Value("${app.retry.delay.ms}")
    int retryDelay;

    public <T> ResponseEntity<T> executeWithRetry(String url, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> response = null;
        int retryCount = 0;
        String message = "";

        while (retryCount < maxRetryCount) {
            try {
                response = restTemplate.getForEntity(url, responseType);
                if (response.getStatusCode().is2xxSuccessful()) {
                    break;
                }
            } catch (HttpServerErrorException e) {
                System.out.println("Server error, retrying...: " + e.getMessage());
                message = Optional.ofNullable(e.getMessage()).orElse("");
            } catch (RestClientException e) {
                System.out.println("Client error, retrying...: " + e.getMessage());
                message = Optional.ofNullable(e.getMessage()).orElse("");
            }

            retryCount++;
            if (retryCount < maxRetryCount) {
                try {
                    TimeUnit.MILLISECONDS.sleep(retryDelay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Successful response: " + response.getBody());
        } else {
            System.out.println("Failed to get a successful response after " + retryCount + " retries.");
            throw new RestClientException(message);
        }
        return response;
    }
}
