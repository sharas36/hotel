package com.example.hotel.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visitors")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private int age;



    @ManyToMany(mappedBy = "visitors", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Reservation> reservations = new ArrayList<>();


    public void addRes(Reservation res) {
        this.reservations.add(res);
    }
}
