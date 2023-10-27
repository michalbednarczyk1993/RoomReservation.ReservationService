package com.roomreservation.reservationservice.core.repository;

import com.roomreservation.reservationservice.core.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.annotation.ApplicationScope;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
}
