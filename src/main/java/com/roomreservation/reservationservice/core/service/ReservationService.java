package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.dto.ReservationDto;
import com.roomreservation.reservationservice.core.entities.Reservation;
import com.roomreservation.reservationservice.core.repository.ReservationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDto getReservation(Integer id) {
        //if (id < 0) throw new InvalidDataException(); //TODO implement exception class
        Reservation reservation = this.reservationRepository.getReferenceById(id);
        //if (reservation == null) throw new ResourceNotFoundException(); //TODO implement exception class
        return ReservationDto.toDto(reservation);
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
