package com.roomreservation.reservationservice.core.repository;

import com.roomreservation.reservationservice.core.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
