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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class HotelApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(HotelApplication.class, args);

        Service service = ctx.getBean(Service.class);
    }


    public static Reservation getReservation() {

        int year = new Random().nextInt(24) + 2000;
        int month = new Random().nextInt(11) + 1;
        int day = new Random().nextInt(27) + 1;

        String startDate = year + "-" + month + "-" + day;

        Date date1 = Date.valueOf(startDate);

        year -= new Random().nextInt(9) + 1;

        if (month > 4) {
            month -= new Random().nextInt(3) + 1;
        }

        if (day > 4) {
            day -= new Random().nextInt(3) + 1;
        }

        String endDate = year + "-" + month + "-" + day;


        Date date2 = Date.valueOf(endDate);


        return Reservation.builder().roomNum(new Room()).startDate(date2).endDate(date1).build();
    }

    public static Visitor getVisitor(int i) {

        Visitor visitor = new Visitor();

        visitor.setAge(new Random().nextInt(110) + 1);
        visitor.setFirstName("name" + i);
        visitor.setLastName("lastName" + i);
        return visitor;
    }


}


