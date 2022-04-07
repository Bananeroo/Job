package com.example.demo.programmer;

import com.example.demo.tasks.Task;
import com.example.demo.tasks.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
public class ProgrammerConfig {
    @Bean
    CommandLineRunner commandLineRunnerProgrammer(ProgrammerRepository repository){
        return args -> {
            Programmer b1 = new Programmer(
                    1L,
                    "Mateusz",
                    "Banaszkiewicz",
                    23,
                    "Mateusz.Banaszkiewicz@onet.pl"
            );
            Programmer b2 = new Programmer(
                    2L,
                    "Kamil",
                    "Kowalski",
                    23,
                    "Mateusz.Banaszkiewicz@onet.pl"
            );


            repository.saveAll(
                    List.of(b1,b2)
            );

        };
    }
}
