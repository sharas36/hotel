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


    public synchronized Boolean availableByDate(Reservation reservation) {

        Date startDate = reservation.getStartDate();

        List<Reservation> availableDate = reservationRepository.findByEndDateGreaterThanEqual(startDate);

        availableDate = availableDate.stream().sorted().collect(Collectors.toList());

        if (availableDate.isEmpty()) {
            Room room = new Room();
            room.setRoomId(new Random().nextInt(25) + 1);
            System.out.println("your reservation been saved");
            reservation.setRoomNum(room);
            saveReservation(reservation);
            return true;

        } else if (availableDate.get(0).getStartDate().getTime() <= reservation.getEndDate().getTime()) {
            System.out.println("this date not available for this room please try another date");
            return false;
        } else {
            reservation.setRoomNum(availableDate.get(0).getRoomNum());
            saveReservation(reservation);
            System.out.println("your reservation benn sussed your room number is " + reservation.getRoomNum().getRoomNum());
            return true;
        }

    }

    public synchronized boolean isRoomAvailable(Room room, Reservation reservation) {

        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();

        List<Reservation> availableDate = room.getReservationList();

        availableDate = availableDate.stream()
                .filter(r -> r.getEndDate().getTime() >= startDate.getTime()).collect(Collectors.toList());


        if (availableDate.isEmpty()) {
            System.out.println("your reservation been saved");
            saveReservation(reservation);
            return true;
        } else if (!availableDate.isEmpty() && availableDate.get(0).getStartDate().getTime() <= endDate.getTime()) {
            System.out.println("this date not available for this room please try another date");
            return false;
        } else {
            reservation.setRoomNum(availableDate.get(0).getRoomNum());
            saveReservation(reservation);
            return true;
        }

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

    public List<Visitor> getVisitorByAge(int FromAge, int untilAge) {
        return visitorRepository.findByAgeBetween(FromAge, untilAge);
    }

    public List<Reservation> stamToCheck(Date date1, Date date2) {
        return reservationRepository.findByEndDateGreaterThanEqualAndStartDateLessThanEqual(date1, date2);
    }


}

