package com.example.hotel.repositories;

import com.example.hotel.entities.Reservation;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

//    List<Reservation> findByStartBeforeAndEndAfter(LocalDate date1, LocalDate date2);
//
//    List<Reservation> findByRoomNum(int roomNum);
}
