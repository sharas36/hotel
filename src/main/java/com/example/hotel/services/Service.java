package com.example.hotel.services;

import com.example.hotel.Hotel;
import com.example.hotel.entities.Reservation;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.Visitor;
import com.example.hotel.repositories.ReservationRepository;
import com.example.hotel.repositories.RoomRepository;
import com.example.hotel.repositories.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.spi.RegisterableService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {


    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private VisitorRepository visitorRepository;


    public synchronized Room availableByDate(Reservation reservation) {

        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            Date startDate = reservation.getStartDate();
            Date endDate = reservation.getEndDate();

            List<Reservation> availableDate = room.getReservationList();

            availableDate = availableDate.stream()
                    .filter(r -> r.getEndDate().getTime() >= startDate.getTime()).sorted().collect(Collectors.toList());

            if (availableDate.isEmpty()) {
                reservation.setRoomNum(room);
                saveRoom(room);
                return room;
            }
            for (Reservation res : availableDate) {
                if (res.getStartDate().getTime() > endDate.getTime()) {
                    reservation.setRoomNum(room);
                    saveRoom(room);
                    return room;
                }
            }

        }
        return null;
    }

    public Reservation addReservation(Reservation reservation) {
        Room room = availableByDate(reservation);
        if(room != null) {
            reservation.setRoomNum(room);
            saveReservation(reservation);
            return reservation;
        }
        return null;
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);

    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservation(int num) {
        return reservationRepository.findById(num);

    }

    public void saveVisitor(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    public void addVisitorToReservation(int reservationId, int visitorId) {

        Visitor visitor = visitorRepository.getReferenceById(visitorId);

        Reservation reservation = reservationRepository.getReferenceById(reservationId);

        if (!visitorRepository.findById(visitorId).isPresent() ||
                !reservationRepository.findById(reservationId).isPresent()) {
            System.out.println("reservation is or visitor are not exist");
        } else {
            reservation.addVisitor(visitor);
            reservationRepository.save(reservation);
        }


    }

    public List<Reservation> getReservationBetweenTwoDate(Date date1, Date date2) {
        return reservationRepository.findByStartDateAfterAndEndDateBefore(date1, date2);
    }

    public List<Visitor> getByFirstName(String firstName) {
        return visitorRepository.findByFirstName(firstName);
    }

    public List<Visitor> getByLastName(String lastName) {
        return visitorRepository.findByFirstName(lastName);
    }

    public List<Visitor> getVisitorByAges(int FromAge, int untilAge) {
        return visitorRepository.findByAgeBetween(FromAge, untilAge);
    }

    public void deleteRoom(Room room) {
        roomRepository.deleteById(room.getRoomId());
    }

    public Room getRoom(int id) {
        return roomRepository.getReferenceById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getAllReservationsOfRoom(int roomId) {
        return roomRepository.findById(roomId).getReservationList();
    }
}

