package com.example.demo.programmer;

import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.Objects;

@Entity(name = "programmer")
@Table(name = "programmer")
@Getter
@Setter
@ToString
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "programmer_sequence")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private Integer age;
    @Column(name = "password")
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Programmer that = (Programmer) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void updateProgrammer(Programmer programmer){
        this.id = programmer.getId();
        this.name = programmer.getName();
        this.surname = programmer.getSurname();
        this.email = programmer.getEmail();
        this.age = programmer.getAge();
        this.password = programmer.getPassword();
    }

}
