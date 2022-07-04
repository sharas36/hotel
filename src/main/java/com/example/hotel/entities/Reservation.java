package com.example.hotel.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"resNum", "startDate", "finish", "endDate"})
@Builder
public class Reservation implements Comparable<Reservation> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resNum;

    private java.sql.Date startDate;

    private java.sql.Date endDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE,})
    private List<Visitor> visitors;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_num")
    @JsonIgnoreProperties("reservationList")
    private Room roomNum;


    @Override
    public int compareTo(Reservation o) {
        return this.getEndDate().getTime() > o.getEndDate().getTime() ? 1 :
                this.getEndDate().getTime() < o.getEndDate().getTime() ? -1 : 0;
    }

    public void addVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

}
