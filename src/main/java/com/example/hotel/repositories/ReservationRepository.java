package com.example.hotel.repositories;

import com.example.hotel.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findByEndDateGreaterThanEqual(Date startDate);

    List<Reservation>  findByStartDateAfterAndEndDateBefore (Date date1,Date date2);

    List<Reservation>  findByEndDateGreaterThanEqualAndStartDateLessThanEqual (Date date1,Date date2);






}
