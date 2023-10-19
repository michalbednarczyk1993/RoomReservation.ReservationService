package com.roomreservation.reservationservice.core.service;

import com.roomreservation.reservationservice.core.dto.*;
import com.roomreservation.reservationservice.core.entities.ReservationEntity;
import com.roomreservation.reservationservice.core.entities.ReservationServicesEntity;
import com.roomreservation.reservationservice.core.repository.ReservationRepository;
import com.roomreservation.reservationservice.core.repository.ReservationServicesRespository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ReservationService {
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    ReservationRepository reservationRepository;
    ReservationServicesRespository reservationServiceRespository;

    public ReservationService(ReservationRepository reservationRepository, ReservationServicesRespository reservationServiceRespository) {
        this.reservationRepository = reservationRepository;
        this.reservationServiceRespository = reservationServiceRespository;
    }


    public void createReservation(ReservationDto dto) {
        ReservationEntity reservation = dto.toReservationEntity();
        reservation = reservationRepository.save(reservation);
        List<ReservationServicesEntity> reservationServices = dto.toReservationservicesEntity(reservation);
        reservationServices.forEach(service -> reservationServiceRespository.save(service));
    }

    public ReservationDto getReservation(Integer id) {
        logger.info("ReservationService::getReservation()");
        ReservationEntity reservation = this.reservationRepository.getReferenceById(id);
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
        ReservationEntity entity = reservationRepository.getReferenceById(id);
        entity = reservationRepository.save(newData.updateEntity(entity));
        // TODO: Dokończyć naprawianie tej klasy
        List<ReservationServicesEntity> reservationServices = newData.toReservationservicesEntity(entity);
        reservationServices.forEach(service -> reservationServiceRespository.save(service));
    }

    public void cancelReservation(Integer reservationId) {
        changeReservationStatus(reservationId, Status.CANCELED.toString());
    }

    public void changeReservationStatus(Integer reservationId, String status) {
        ReservationEntity entity = reservationRepository.getReferenceById(reservationId);
        entity.setStatus(Status.valueOf(status));
        reservationRepository.save(entity);
    }

    public List<ReservationDto> getUserReservations(Integer userId, Integer page, Integer size) {
        return getReservations(page, size)
                .stream().filter(reservationDto -> reservationDto.clientId().equals(userId)).toList();
    }
}
