package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.dto.*;
import com.roomreservation.reservationservice.core.entities.Reservation;
import com.roomreservation.reservationservice.core.repository.ReservationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;

    ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void createReservation(ReservationDto reservation) {
        reservationRepository.save(reservation.toEntity());
    }

    public ReservationDto getReservation(Integer id) {
        Reservation reservation = this.reservationRepository.getReferenceById(id);
        return ReservationDto.toDto(reservation);
    }

    public List<ReservationDto> getReservations(Integer page, Integer size) {
        return reservationRepository
                .findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(ReservationDto::toDto)
                .toList();
    }

    public void updateReservation(Integer id, ReservationDto newData) {
        Reservation entity = reservationRepository.getReferenceById(id);
        reservationRepository.save(newData.updateEntity(entity));
    }

    public void cancelReservation(Integer reservationId) {
        changeReservationStatus(reservationId, Status.CANCELED.toString());
    }

    public void changeReservationStatus(Integer reservationId, String status) {
        Reservation entity = reservationRepository.getReferenceById(reservationId);
        entity.setStatus(Status.valueOf(status));
        reservationRepository.save(entity);
    }
}
