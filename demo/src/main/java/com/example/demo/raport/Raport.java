package com.example.demo.raport;

import com.example.demo.program.Program;
import com.example.demo.programmer.Programmer;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "raport")
@Table(name = "raport")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "raport_sequence")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description",length = 2048)
    private String description;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "programmer_id")
    private Programmer programmer;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Raport raport = (Raport) o;
        return id != null && Objects.equals(id, raport.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void udpateRaport(Raport raport){
        this.id = raport.getId();
        this.title=raport.getTitle();
        this.description=raport.getDescription();
        this.date=raport.getDate();
        this.timestamp=raport.getTimestamp();
        this.programmer=raport.getProgrammer();
        this.program=raport.getProgram();
    }

}
