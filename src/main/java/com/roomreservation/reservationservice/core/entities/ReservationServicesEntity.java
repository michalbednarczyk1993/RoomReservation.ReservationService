package com.roomreservation.reservationservice.core.entities;

import com.roomreservation.reservationservice.core.dto.Service;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "RESERVATION_SERVICES")
public class ReservationServicesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "RESERVATION_ID")
    private ReservationEntity reservation;
    @Enumerated(EnumType.STRING)
    Service service;
}
