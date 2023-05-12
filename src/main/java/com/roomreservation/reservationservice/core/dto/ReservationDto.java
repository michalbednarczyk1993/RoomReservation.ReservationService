package com.roomreservation.reservationservice.core.dto;

import com.roomreservation.reservationservice.core.entities.Reservation;

import java.util.List;

public record ReservationDto(
        Integer id,
        String status,
        List<String> services,
        Integer roomNumber) {

    public static ReservationDto toDto(Reservation entity) {
        return new ReservationDto(entity.getId(), entity.getStatus(), entity.getServices(), entity.getRoomNumber());
    }
}
