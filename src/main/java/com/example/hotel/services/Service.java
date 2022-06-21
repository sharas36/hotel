package com.example.hotel.services;

import com.example.hotel.Hotel;
import com.example.hotel.entities.Reservation;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.Visitor;
import com.example.hotel.repositories.ReservationRepository;
import com.example.hotel.repositories.RoomRepository;
import com.example.hotel.repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private VisitorRepository visitorRepository;


    public List<Room> availableRoomsByDate(LocalDate date){
        Hotel hotel = Hotel.getInstance();
        List<Room> allRooms = hotel.getRooms();
        List<Room> availableRooms = new ArrayList<>();
        for (Room room: allRooms) {
            if(room.isAvailable(date)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }





//    public List<Integer> allAvailableRoomsByDate(LocalDate date) {
//        Hotel hotel = Hotel.getInstance();
//        List<Integer> roomNums = new ArrayList<>();
//        for (Room room : hotel.getRooms()) {
//            if (room.isAvailable()) {
//                roomNums.add(room.getRoomNum());
//            }
//        }
//        return roomNums;
//    }

    public Boolean isThisRoomAvailableNow(int roomId) {
        Room room = (Room) roomRepository.findById(roomId);
        if (room.isAvailable(LocalDate.now())) {
            return true;
        }
        return false;
    }

//    public int howMuchNotAvalableByDate(LocalDate date1, LocalDate date2) {
//        return reservationRepository.findByStartBeforeAndEndAfter(date1, date2).size();
//    }

//    public List<Room> AvalableTomorrowAndNotToday() {
//
//        List<Reservation> resTomorrow = reservationRepository.findByStartBeforeAndEndAfter(LocalDate.now().plusDays(1));
//        List<Reservation> resToday = reservationRepository.findByStartBeforeAndEndAfter(LocalDate.now());
//        resToday.removeAll(resTomorrow);
//        List<Room> rooms = new ArrayList<>();
//        for (Reservation res : resTomorrow) {
//            rooms.add(res.getRoomNum());
//        }
//
//        return rooms;
//    }

    public Room findAvailableRoom(LocalDate start, LocalDate end) {
        Hotel hotel = Hotel.getInstance();
        List<Room> allRooms = hotel.getRooms();
        for (Room room : allRooms) {
            List<Reservation> roomRes = new ArrayList<>();
            for (Reservation res : room.getReservationList()) {
                if (res.getStart().isAfter(end)) {
                    return room;
                }
                if (res.getFinish().isBefore(start)) {
                    return room;
                }
            }
        }
        return null;
    }

    public void addRes(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public Reservation getReservation(int num) {
        return reservationRepository.findById(num).get();
    }
}
