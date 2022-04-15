package com.example.demo.program;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "program")
@Table(name = "program")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Program  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NonNull
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Program program = (Program) o;
        return id != null && Objects.equals(id, program.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void updateProgram(Program program){
        this.id = program.getId();
        this.name = program.getName();
    }

}
