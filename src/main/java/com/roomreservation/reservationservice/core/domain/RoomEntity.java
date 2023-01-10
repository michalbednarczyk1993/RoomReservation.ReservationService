package com.roomreservation.reservationservice.core.domain;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="room")
public class RoomEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private RoomTypeEntity typeId;
}
