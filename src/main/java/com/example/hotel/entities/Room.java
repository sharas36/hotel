package com.example.hotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "reservationList")
@Builder

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private int floorNum;

    private int roomNum;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "roomNum",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )

    @JsonIgnoreProperties("roomNum")
    private List<Reservation> reservationList;


    public Room() {
        this.floorNum = new Random().nextInt(10);
        this.roomNum = floorNum * 101 + (new Random().nextInt(54) + 1);
        this.reservationList = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
    }

    // sta, somrthing


}

