package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.entities.TestEntity;
import com.roomreservation.reservationservice.core.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    final TestRepository testRepository;

    public void saveRecord() {
        testRepository.save(new TestEntity());
    }

    public Object readData() {
        List<?> all = testRepository.findAll();
        return all.stream().skip(all.size() - 1).findFirst().orElse(null);
    }

}
