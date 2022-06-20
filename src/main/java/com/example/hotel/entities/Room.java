package com.example.hotel.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@ToString(of = {"roomNum", "floorNum"})
@Builder
public class Room {

    @Id
    private int roomNum;

    private int floorNum;

    @OneToMany(mappedBy = "roomNum", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;


    public Room(){
        this.floorNum = new Random().nextInt(10);
        this.roomNum = floorNum * 100 + (new Random().nextInt(99) + 1);
        this.reservationList = new ArrayList<>();
    }

    public Boolean isAvailable(LocalDate date){
        if(this.reservationList.isEmpty()){
            return true;
        }
        for (Reservation res: reservationList) {
            if(date.isAfter(res.getStart()) && date.isBefore(res.getFinish())){
                return false;
            }
        }
        return true;
    }
}
