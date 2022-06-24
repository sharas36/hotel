package com.example.hotel.repositories;

import com.example.hotel.entities.Reservation;
import com.example.hotel.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorRepository extends JpaRepository<Visitor, Integer> {

    List<Visitor> findByFirstName(String firstName);

    List<Visitor> findByLastName(String lastName);

    List<Visitor> findByAgeBetween(int FromAge, int untilAge);




}
