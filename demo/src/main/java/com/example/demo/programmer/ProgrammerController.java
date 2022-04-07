package com.example.demo.programmer;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@CrossOrigin
@RequestMapping("/programmer")
public class ProgrammerController {

    private final ProgrammerRepository programmerRepository;

    @Autowired
    public ProgrammerController(ProgrammerRepository programmerRepository) {
        this.programmerRepository = programmerRepository;
    }

    @RequestMapping("/getAll")
    @ResponseBody
    List<Programmer> getAll(){
        System.out.println("XD");
        return programmerRepository.findAll();
    }
    @RequestMapping("/getProgrammerById/{id}")
    Optional<Programmer> getProgrammerById(@PathVariable("id") Long id, Model model){
        if(id<1) return null;
        return programmerRepository.findById(id);
    }

}
