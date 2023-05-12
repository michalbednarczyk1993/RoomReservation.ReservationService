package com.roomreservation.reservationservice.core.repository;

import com.roomreservation.reservationservice.core.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
}
