package com.example.demo.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/task")
public class TaskController {
    private final TaskRepository tasksRepository;


    @Autowired
    public TaskController(TaskRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    // TODO sprawdz transactional

    @Transactional(rollbackFor = { SQLException.class })
    @RequestMapping("/assignTaskToProgrammer")
    public ResponseEntity<String> getTasksByProgrammerId(@RequestParam("id") Object object) throws JsonProcessingException, SQLException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        json=json.replace("\"","");
        String[] arrayOfTasksId = json.split(",");

        for(int i = 0 ; i < arrayOfTasksId.length;i++ ){
            int tmp = tasksRepository.assignTaskToProgrammer(2L,Long.parseLong(arrayOfTasksId[i])  );
            Assert.isTrue(tmp == 1,   "Null Null is all I do");
        }
        return new ResponseEntity<>("Taski Przypisane", HttpStatus.OK);
    }

    @RequestMapping("/getAll")
    List<Task> getAll(){
        return tasksRepository.findAll();
    }

    @RequestMapping("/getFreeTask")
    List<Task> getFreeTask(){
        return tasksRepository.findFreeTask();
    }
    @RequestMapping("/getMyTask")
    List<Task> getFreeTask(@RequestParam("id") Long id){
        return tasksRepository.findTasksByProgrammer(id);
    }


}
