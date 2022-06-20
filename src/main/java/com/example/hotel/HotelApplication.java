package com.example.hotel;

import com.example.hotel.entities.Reservation;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.Visitor;
import com.example.hotel.services.Service;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HotelApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(HotelApplication.class, args);
        Service service = ctx.getBean(Service.class);
        List<Visitor> visitorList = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            visitorList.add(Visitor.builder().firstName("name" + i).lastName("last" + i).build());
        }
        Room room = new Room();
        Reservation reservation = Reservation.builder().start(LocalDate.now()).finish(LocalDate.now().plusDays(10))
                .visitors(visitorList).roomNum(room).build();

        service.saveRoom(room);
        service.addRes(reservation);


        System.out.println(service.getReservation(1).toString());
        service.getReservation(1).getVisitors().forEach(System.out::println);
    }

}
