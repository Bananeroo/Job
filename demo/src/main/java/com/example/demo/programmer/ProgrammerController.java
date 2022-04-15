package com.example.demo.programmer;

import com.example.demo.raport.Raport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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


    @GetMapping("/getAll")
    public ResponseEntity<List<Programmer>> getAll(){
        List<Programmer> listOfProgrammer = programmerRepository.findAll();
        return new ResponseEntity<>(listOfProgrammer, HttpStatus.OK);
    }
    @RequestMapping("/findById")
    public ResponseEntity< Optional<Programmer>> findById(@RequestParam("id") Long id){
        Optional<Programmer> optionalProgrammer = programmerRepository.findById(id);
        if(optionalProgrammer.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalProgrammer, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Programmer> create(@RequestBody Programmer programmer){

        Programmer newProgrammer = new Programmer();
        newProgrammer.updateProgrammer(programmer);
        try{
            newProgrammer = programmerRepository.save(newProgrammer);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newProgrammer, HttpStatus.OK);

    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id){
        if(id == null || id<1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        programmerRepository.deleteById(id);
        return  new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/update")
    public ResponseEntity<Programmer> update(@RequestBody Programmer programmer){
        Long id = programmer.getId();
        Optional<Programmer> optionalProgrammer = programmerRepository.findById(id);
        if(optionalProgrammer.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Programmer programmerToUpdate = optionalProgrammer.get();
        programmerToUpdate.updateProgrammer(programmer);
        try{
            programmerToUpdate = programmerRepository.save(programmerToUpdate);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(programmerToUpdate, HttpStatus.OK);
    }

}
