package com.example.hotel.controllers;

import com.example.hotel.entities.Reservation;
import com.example.hotel.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private Service service = new Service();

    @GetMapping("all")
    @ResponseBody
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> res = service.getAllReservations();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("allByRoom")
    @ResponseBody
    public ResponseEntity<?> getAllReservationsByRoom(@PathVariable int roomId){
        try {
            List<Reservation> res = service.getAllReservationsOfRoom(roomId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>("Bad request, no room found for id="+roomId, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("betweenDates")
    @ResponseBody
    public ResponseEntity<?> getAllReservationsBetweenDates(@PathVariable java.sql.Date date1, Date date2){
            List<Reservation> res = service.getReservationBetweenTwoDate(date1, date2);
            return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("addRes")
    @ResponseBody
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation){
        Reservation res = service.addReservation(reservation);
        if(res != null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>("No available room in this dates", HttpStatus.BAD_REQUEST);
    }
}
