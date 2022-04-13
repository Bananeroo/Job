package com.example.demo.programmer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProgrammerRepository extends JpaRepository<Programmer,Long> {

    Optional<Programmer> findByEmail(String email);

}
