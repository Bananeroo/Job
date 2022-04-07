package com.example.demo.tasks;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Configuration
public class TaskConfig {
    @Bean
    CommandLineRunner commandLineRunnerTask(TaskRepository repository){
        return args -> {
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("05/04/2022");
            Date date2=new SimpleDateFormat("dd/MM/yyyy").parse("06/12/2022");
            Task b1 = new Task(
                    1L,
                    1L,
                    "Pierwszy task Podłączyć do bazy danych",
                    "Postgres + Docker + Spring Boot",
                    null,
                    null
            );
            Task b2 = new Task(
                    2L,
                    2L,
                    "Drugi Task",
                    "Praesent ld pi purus. Cras a tincidunt magna. Nutis sollicitudin quam. Donec euismod, turpis ut placerat pretium, dui erat euismod nulla, vitae scelerisque orci velit quis erat. Sed pellentesque iaculis massa.",
                    null,
                    date1
            );
            Task b3 = new Task(
                    3L,
                    null,
                    "Title",
                    "",
                    null,
                    date2
            );
            Task b4 = new Task(
                    4L,
                    2L,
                    "Title",
                    "",
                    null,
                    date2
            );
            Task b5 = new Task(
                    5L,
                    2L,
                    "Title",
                    "",
                    null,
                    date2
            );
            Task b6 = new Task(
                    6L,
                    2L,
                    "Title",
                    "",
                    null,
                    date2
            );
            Task b7 = new Task(
                    7L,
                    2L,
                    "Title",
                    "",
                    null,
                    date2
            );
            Task b8 = new Task(
                    8L,
                    2L,
                    "Title",
                    "",
                    null,
                    date2
            );
            Task b9 = new Task(
                    9L,
                    null,
                    "Title",
                    "",
                    null,
                    date2
            );





            repository.saveAll(
                    List.of(b1,b2,b3,b4,b5,b6,b7,b8,b9)
            );

        };
    }
}
