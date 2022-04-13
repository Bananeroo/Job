package com.example.demo.program;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/program")
@CrossOrigin
public class ProgramController {

    private final ProgramRepository programRepository;
    @Autowired
    public ProgramController(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Program>> getAll(){
        List<Program> result = programRepository.findAll();
        if(result.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping("/findById")
    public ResponseEntity<Optional<Program>> findById(@RequestParam("id") Long id){
        if(id == null || id<1)  {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Optional<Program> result = programRepository.findById(id);
        if(result.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Program> create(@RequestBody Program program){
        Program result;
        try{
            result = programRepository.save(program);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id){
        if(id == null || id<1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        programRepository.deleteById(id);
        return  new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/update")
    public ResponseEntity<Program> update(@RequestBody Program program){
        Long id = program.getId();
        Optional<Program> result = programRepository.findById(id);
        if(result.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try{
            program = programRepository.save(program);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(program, HttpStatus.OK);
    }


}
