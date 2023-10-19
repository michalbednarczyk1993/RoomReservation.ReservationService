package com.roomreservation.reservationservice.core.repository;

import com.roomreservation.reservationservice.core.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Integer> {
}
