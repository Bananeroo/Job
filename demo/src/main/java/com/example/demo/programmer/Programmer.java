package com.example.demo.programmer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public @Data
class Programmer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "programmer_sequence")
    @SequenceGenerator(name = "programmer_sequence", sequenceName = "programmer_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;

}
