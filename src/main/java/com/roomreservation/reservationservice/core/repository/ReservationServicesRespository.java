package com.roomreservation.reservationservice.core.repository;

import com.roomreservation.reservationservice.core.entities.ReservationServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationServicesRespository extends JpaRepository<ReservationServicesEntity, Integer> {
}
