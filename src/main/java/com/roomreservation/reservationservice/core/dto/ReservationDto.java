package com.roomreservation.reservationservice.core.dto;

import com.roomreservation.reservationservice.core.entities.Reservation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import javax.validation.constraints.*;

import java.sql.Date;
import java.util.List;

@Builder
@ApiModel(description = "Szczegóły na temat rezerwacji")
public record ReservationDto(
        @ApiModelProperty(value = "Data rozpoczęcia pobytu") @Null(groups = {OnUpdate.class}) @NotNull Date startDate,
        @ApiModelProperty(value = "Czas trwania pobytu") @Null(groups = {OnUpdate.class}) @NotNull @Min(value = 1) Integer daysNum,
        @ApiModelProperty(value = "Ilość gości w pokoju") @Null(groups = {OnUpdate.class}) @NotNull @Min(value = 1) Integer guestNum,
        @ApiModelProperty(value = "Typ pokoju", allowableValues="SINGLE_ROOM, DOUBLE_ROOM, APARTMENT") @Null(groups = {OnUpdate.class}) @NotNull RoomType roomType,
        @ApiModelProperty(value = "Lista serwisów, które są zawarte w rezerwacji") @Null(groups = {OnUpdate.class}) @NotNull List<Service> services,
        @ApiModelProperty(
                value = "Cena rezerwacji",
                notes = "Jeżeli waluta jest inna niż PLN, to została ona przeliczona po stawce ustalonej w chwili dokonania rezerwacji"
        ) @Null(groups = {OnUpdate.class}) @NotNull Double price,
        @ApiModelProperty(value = "Waluta rezerwacji") @Null(groups = {OnUpdate.class}) @NotNull Currency currency,
        @ApiModelProperty(value = "Status rezerwacji") Status status,
        @ApiModelProperty(
                value = "ID klienta, którego dotyczy rezerwacja",
                notes = "Jest tworzony przez UserService, a tutaj jest jedynie przekazywany"
        ) @NotNull Integer clientId
        ) {

    public interface OnCreate{}
    public interface OnUpdate{}

    public static ReservationDto toDto(@NotNull Reservation entity) {
        return ReservationDto.builder()
                .startDate(entity.getStartDate())
                .daysNum(entity.getDaysNum())
                .guestNum(entity.getGuestNum())
                .roomType(entity.getRoomType())
                .services(entity.getServices())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .status(entity.getStatus())
                .clientId(entity.getClientId())
                .build();
    }

    public Reservation toEntity() {
        return Reservation.builder()
                .startDate(startDate)
                .daysNum(daysNum)
                .guestNum(guestNum)
                .roomType(roomType)
                .services(services)
                .status(status == null ? Status.UNPAID : status)
                .price(price)
                .currency(currency)
                .clientId(clientId)
                .build();
    }

    public Reservation updateEntity(Reservation entity) {
        if (startDate != null) entity.setStartDate(startDate);
        if (daysNum != null) entity.setDaysNum(daysNum);
        if (guestNum != null) entity.setGuestNum(guestNum);
        if (roomType != null) entity.setRoomType(roomType);
        if (services != null) entity.setServices(services);
        if (status != null) entity.setStatus(status);
        if (price != null) entity.setPrice(price);
        if (currency != null) entity.setCurrency(currency);
        return entity;
    }
}
