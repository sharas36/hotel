package com.example.hotel.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"resNum", "start", "finish", "roomNum"})
@Builder
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resNum;

    private LocalDate start;

    private LocalDate finish;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Visitor> visitors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_num")
    private Room roomNum;


}
