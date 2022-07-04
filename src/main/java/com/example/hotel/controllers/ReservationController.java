package com.example.hotel.controllers;

import com.example.hotel.entities.Reservation;
import com.example.hotel.entities.Room;
import com.example.hotel.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private Service service = new Service();

    @GetMapping("/all")
//    @ResponseBody
    public ResponseEntity<?> getAllReservations() {

        List<Reservation> res = service.getAllReservations();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/allRooms")
    //  @ResponseBody
    public ResponseEntity<?> getAllRooms() {

        List<Room> rooms = service.getAllRooms();

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/getRandomRoom")
    // @ResponseBody
    public ResponseEntity<?> getRandomRoom() {

        List<Room> rooms = service.getAllRooms();
        Room room = rooms.get(new Random().nextInt(rooms.size() - 1));
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/getRandomReservation")
    //  @ResponseBody
    public ResponseEntity<?> getRandomReservation() {

        List<Reservation> reservations = service.getAllReservations();

        Reservation reservation = reservations.get(new Random().nextInt(reservations.size() - 1));
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }


    @GetMapping("/allByRoom/{roomId}")
    //  @ResponseBody
    public ResponseEntity<?> getAllReservationsByRoom(@PathVariable int roomId) {
        try {
            List<Reservation> res = service.getAllReservationsOfRoom(roomId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Bad request, no room found for id=" + roomId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/betweenDates/{date1}/{date2}")
    // @ResponseBody
    public ResponseEntity<?> getAllReservationsBetweenDates(@PathVariable Date date1, @PathVariable Date date2) {
        List<Reservation> res = service.getReservationBetweenTwoDate(date1, date2);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/addRes")
    //  @ResponseBody
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        Reservation res = service.addReservation(reservation);
        if (res != null) {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>("No available room in this dates", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addRoom")
    //@ResponseBody
    public Integer addRoom(@RequestBody Room room) {
        Integer roomId = service.saveRoom(room).getRoomNum();

        return roomId;
    }

    @GetMapping("/getHashMap")
    // @ResponseBody
    public String getYourName(@RequestParam Map<String, String> stringsMap) {

        return stringsMap.entrySet().toString();
    }

    @GetMapping("/getName")
    // @ResponseBody
    public String getYourName(@RequestParam(required = false) String name, Integer age) {

        return "welcome" + " " + name + " " + age;
    }

    @GetMapping("/getOptional")
    // @ResponseBody
    public String getYourName(@RequestParam Optional<String> optional) {

        return "your optional is " + optional.orElseGet(() -> "string is nor provided");

    }
}
