package com.example.hotel.entities;

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
@ToString(of = {"roomNum", "floorNum"})
@Builder


public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private int floorNum;

    private int roomNum;

    @OneToMany(mappedBy = "roomNum", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reservation> reservationList;


    public Room() {
        this.floorNum = new Random().nextInt(10);
        this.roomNum = floorNum * 100 + (new Random().nextInt(99) + 1);
        this.reservationList = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        this.reservationList.add(reservation);
    }


}

