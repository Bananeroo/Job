package com.example.demo.raport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RaportWrapper {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private Long programmerId;
    private Long programId;
}
