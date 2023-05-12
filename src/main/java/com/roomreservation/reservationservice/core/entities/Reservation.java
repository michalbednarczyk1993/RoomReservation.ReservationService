package com.roomreservation.reservationservice.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter
@Entity
public class Reservation {
    @Id
    private Integer id;
    private Date startDate;
    private Integer daysNum;
    private Integer guestNum;
    private String roomType;
    private List<String> services;
    private String status;
    private Integer roomNumber;
    private Double price;
    private String currency;
    private Integer clientId; // FK
}
