package com.example.demo.raport;


import com.example.demo.program.Program;
import com.example.demo.program.ProgramRepository;
import com.example.demo.programmer.Programmer;
import com.example.demo.programmer.ProgrammerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
@Configuration
public class RaportConfig {
    @Bean
    CommandLineRunner commandLineRunnerRaport(RaportRepository raportRepository, ProgramRepository programRepository,ProgrammerRepository programmerRepository){
        return args -> {
            Program program = new Program("Pierwszy program");
            Program program2 = new Program("Drugi program");

            programRepository.save(program);
            programRepository.save(program2);


            Programmer programmer = new Programmer();
            programmer.setName("Mateusz");
            programmer.setSurname("Banaszkiewicz");
            programmer.setAge(24);
            programmer.setEmail(programmer.getName() + "." +programmer.getSurname()+"@onet.pl");

            programmerRepository.save(programmer);


            LocalDate date1 = LocalDate.parse("2019-11-18");
            LocalDate date2 = LocalDate.parse("2020-01-08");

            Raport raport = new Raport();
            raport.setTitle("Pierwszy raport");
            raport.setDescription("Pierwszy opis");
            raport.setDate(date1);
            raport.setProgram(program);

            Raport raport2 = new Raport();
            raport2.setTitle("Drugi raport");
            raport2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla ornare egestas lorem non tincidunt. Duis vitae augue at ante elementum ultrices et semper nisi. Integer ultrices ornare nibh, tincidunt dignissim mi iaculis ut. Proin at mattis nisi, at hendrerit neque. In hac habitasse platea dictumst. Vivamus eros augue, ultrices ut luctus id, pharetra nec nulla. Praesent vulputate sem odio, quis finibus nisi finibus non");
            raport2.setDate(date2);
            raport2.setProgram(program);


            raport.setProgrammer(programmer);
            raport2.setProgrammer(programmer);

            raportRepository.save(raport);
            raportRepository.save(raport2);

        };
    }
}
