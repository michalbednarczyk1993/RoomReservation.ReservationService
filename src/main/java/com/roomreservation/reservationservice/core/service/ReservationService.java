package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.dto.ReservationDto;
import com.roomreservation.reservationservice.core.repository.IReservationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    IReservationRepository reservationRepository;

    ReservationService(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDto> getReservations(Integer page, Integer size, String direction, String... sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), sortBy);

        return reservationRepository
                .findAll(pageRequest)
                .getContent()
                .stream()
                .map(ReservationDto::toDto)
                .toList();
    }

}
