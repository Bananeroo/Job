package com.example.demo.programmer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammerRepository extends JpaRepository<Programmer,Long> {
}