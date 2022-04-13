package com.example.demo.raport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface RaportRepository extends JpaRepository<Raport,Long> {

}
