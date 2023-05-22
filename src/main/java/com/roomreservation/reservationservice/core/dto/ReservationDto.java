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
        @ApiModelProperty(value = "Data rozpoczęcia pobytu") @NotNull Date startDate,
        @ApiModelProperty(value = "Czas trwania pobytu") @NotNull @Min(value = 1) Integer daysNum,
        @ApiModelProperty(value = "Ilość gości w pokoju") @NotNull @Min(value = 1) Integer guestNum,
        @ApiModelProperty(value = "Typ pokoju", allowableValues="BASIC, PREMIUM")@NotNull RoomType roomType,
        @ApiModelProperty(value = "Lista serwisów, które są zawarte w rezerwacji") @NotNull List<Service> services,
        @ApiModelProperty(
                value = "Cena rezerwacji",
                notes = "Jeżeli waluta jest inna niż PLN, to została ona przeliczona po stawce ustalonej w chwili dokonania rezerwacji"
        ) @NotNull Double price,
        @ApiModelProperty(value = "Waluta rezerwacji") @NotNull Currency currency,
        @ApiModelProperty(value = "Status rezerwacji") @NotNull Status status,
        @ApiModelProperty(
                value = "ID klienta, którego dotyczy rezerwacja",
                notes = "Jest tworzony przez UserService, a tutaj jest jedynie przekazywany"
        ) @NotNull Integer clientId
        ) {

    public static ReservationDto toDto(Reservation entity) {
        return null;
        //return new ReservationDto(entity.getId(), entity.getStatus(), entity.getServices(), entity.getRoomNumber());
    }
}
