package com.example.hotel.repositories;

import com.example.hotel.entities.Room;
import com.example.hotel.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {


}
