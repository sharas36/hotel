package com.example.hotel;

import com.example.hotel.entities.Reservation;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.Visitor;
import com.example.hotel.services.Service;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class HotelApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(HotelApplication.class, args);


        Service service = ctx.getBean(Service.class);

        int year = new Random().nextInt(24) + 2000;
        int month = new Random().nextInt(11) + 1;
        int day = new Random().nextInt(27) + 1;

        String date = year + "-" + month + "-" + day;

        Date startDate = Date.valueOf(date);
        Date endDate = null;

        System.out.println(startDate);

        year-= new Random().nextInt(9) + 1;

        if (month > 4) {

            month-= new Random().nextInt(3) + 1;
        }

        if (day > 4) {
            day-= new Random().nextInt(3) + 1;
        }


        String endDateString = year + "-" + month + "-" + day;
        endDate = Date.valueOf(endDateString);

        System.out.println(startDate);
        System.out.println(endDate);


        System.out.println();

//
//        for (int i = 0; i <= 100; i++) {
//            Room room = new Room();
//            room.setRoomId(new Random().nextInt(25) + 1);
//            Reservation reservation = getReservation();
//            reservation.setRoomNum(room);
//            service.saveReservation(reservation);
//        }


//        Date date1 = Date.valueOf("2022-06-22");
//
//        Date date2 = Date.valueOf("2022-07-28");
//
//        service.stamToCheck(date1, date2);


//        service.getReservationBetweenTwoDate(date1, date2).stream().sorted().forEach(System.out::println);

//        service.getVisitorByAge(11, 99).stream().sorted(Comparator.comparing(Visitor::getAge)
//                .thenComparing(Visitor::getFirstName)).collect(Collectors.toList()).forEach(System.out::println);


    }

    public static Reservation getReservation() {

        int year = new Random().nextInt(24) + 2000;
        int month = new Random().nextInt(11) + 1;
        int day = new Random().nextInt(27) + 1;

        String date = year + "-" + month + "-" + day;

        Date datePattern = Date.valueOf(date);

        year = year - new Random().nextInt(9) + 1;

        if (month > 4) {
            month = month - new Random().nextInt(3) + 1;
        }

        if (day > 4) {
            day = day - new Random().nextInt(3) + 1;
        }

        date = year + "-" + month + "-" + day;


        Date date1 = Date.valueOf("" + year + month + day);

        Date date2 = new Date(System.currentTimeMillis());


        return Reservation.builder().roomNum(new Room()).startDate(date1).endDate(date2).build();
    }

    public static Visitor getVisitor(int i) {

        Visitor visitor = new Visitor();

        visitor.setAge(new Random().nextInt(110) + 1);
        visitor.setFirstName("name" + i);
        visitor.setLastName("lastName" + i);
        return visitor;
    }


}


