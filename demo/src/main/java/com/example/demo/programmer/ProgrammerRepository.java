package com.example.demo.programmer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgrammerRepository extends JpaRepository<Programmer,Long> {

    Optional<Programmer> findByEmail(String email);

}
