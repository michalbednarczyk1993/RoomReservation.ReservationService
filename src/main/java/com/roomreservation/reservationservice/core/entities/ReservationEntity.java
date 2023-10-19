package com.roomreservation.reservationservice.core.entities;

import com.roomreservation.reservationservice.core.dto.Currency;
import com.roomreservation.reservationservice.core.dto.RoomType;
import com.roomreservation.reservationservice.core.dto.Status;
import lombok.*;

import javax.persistence.*;
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
@Table(name = "RESERVATION")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date startDate;

    private Integer daysNum;

    private Integer guestNum;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private RoomType roomType;

    @OneToMany(mappedBy = "reservation", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ReservationServicesEntity> services;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private Status status;

    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private Currency currency;

    private Integer clientId;

}
