package com.roomreservation.reservationservice.core.dto;

import com.roomreservation.reservationservice.core.entities.ReservationEntity;
import com.roomreservation.reservationservice.core.entities.ReservationServicesEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Builder;

import javax.validation.constraints.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@ApiModel(description = "Szczegóły na temat rezerwacji")
public record ReservationDto(
        @ApiModelProperty(value = "Data rozpoczęcia pobytu, w formacie yyyy-mm-dd", example = "2023-10-25")
        @Null(groups = {OnUpdate.class})
        @NotNull(groups = {OnCreate.class})
        @FutureOrPresent(groups = {OnCreate.class})
        LocalDate startDate,
        @ApiModelProperty(value = "Czas trwania pobytu", example = "1")
        @Null(groups = {OnUpdate.class})
        @NotNull(groups = {OnCreate.class})
        @Positive
        Integer daysNum,
        @ApiModelProperty(value = "Ilość gości w pokoju", example = "1")
        @Null(groups = {OnUpdate.class})
        @NotNull(groups = {OnCreate.class})
        @Positive
        Integer guestNum,
        @ApiModelProperty(value = "Typ pokoju", allowableValues="SINGLE_ROOM, DOUBLE_ROOM, APARTMENT")
        @Null(groups = {OnUpdate.class})
        @NotNull(groups = {OnCreate.class})
        RoomType roomType,
        @ApiModelProperty(value = "Lista serwisów, które są zawarte w rezerwacji")
        @Null(groups = {OnUpdate.class})
        @NotNull(groups = {OnCreate.class})
        List<Service> services,
        @ApiModelProperty(
                value = "Cena rezerwacji",
                notes = "Jeżeli waluta jest inna niż PLN, to została ona przeliczona po stawce ustalonej w chwili dokonania rezerwacji"
        )
        @Null(groups = {OnUpdate.class})
        @NotNull(groups = {OnCreate.class})
        Double price,
        @ApiModelProperty(value = "Waluta rezerwacji")
        @Null(groups = {OnUpdate.class})
        @NotNull(groups = {OnCreate.class})
        Currency currency,
        @ApiModelProperty(value = "Status rezerwacji")
        Status status,
        @ApiModelProperty(
                value = "ID klienta, którego dotyczy rezerwacja",
                notes = "Jest tworzony przez UserService, a tutaj jest jedynie przekazywany"
        )
        @NotNull
        Integer clientId
        ) {

    public interface OnCreate{}
    public interface OnUpdate{}

    public static ReservationDto toDto(@NotNull ReservationEntity entity) {
        return ReservationDto.builder()
                .startDate(entity.getStartDate().toLocalDate())
                .daysNum(entity.getDaysNum())
                .guestNum(entity.getGuestNum())
                .roomType(entity.getRoomType())
                .services(entity.getServices().stream().map(ReservationServicesEntity::getService).toList())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .status(entity.getStatus())
                .clientId(entity.getClientId())
                .build();
    }

    public ReservationEntity toReservationEntity() {
        return ReservationEntity.builder()
                .startDate(Date.valueOf(startDate))
                .daysNum(daysNum)
                .guestNum(guestNum)
                .roomType(roomType)
                .status(status)
                .price(price)
                .currency(currency)
                .clientId(clientId)
                .build();
    }

    public List<ReservationServicesEntity> toReservationservicesEntity(ReservationEntity reservationId) {
        List<ReservationServicesEntity> result = new ArrayList<>();
        this.services.forEach(service -> result.add(ReservationServicesEntity.builder()
                        .reservation(reservationId)
                        .service(service)
                .build()));
        return result;
    }


    public ReservationEntity updateEntity(ReservationEntity entity) {
        if (startDate != null) entity.setStartDate(Date.valueOf(startDate));
        if (daysNum != null) entity.setDaysNum(daysNum);
        if (guestNum != null) entity.setGuestNum(guestNum);
        if (roomType != null) entity.setRoomType(roomType);
        if (status != null) entity.setStatus(status);
        if (price != null) entity.setPrice(price);
        if (currency != null) entity.setCurrency(currency);
        return entity;
    }
}
