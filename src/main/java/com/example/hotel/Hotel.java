package com.example.hotel;

import com.example.hotel.entities.Room;
import lombok.*;

import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@ToString(of = {"name"})
@Builder
@NoArgsConstructor

public class Hotel {

    private static Hotel instance = new Hotel();

    @Id
    private String name;

    private List<Room> rooms;

    private Hotel(String name) {
        this.name = name;
    }

    public static Hotel getInstance(){
         return instance;
    }
}
