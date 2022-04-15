package com.example.demo.program;

import com.example.demo.programmer.Programmer;
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
        List<Program> listOfProgram = programRepository.findAll();
            return new ResponseEntity<>(listOfProgram, HttpStatus.OK);
    }
    @RequestMapping("/findById")
    public ResponseEntity<Optional<Program>> findById(@RequestParam("id") Long id){
        Optional<Program> optionalProgram = programRepository.findById(id);
        if(optionalProgram.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalProgram, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Program> create(@RequestBody Program program){
        Program newProgram = new Program();
        newProgram.updateProgram(program);
        try{
            newProgram = programRepository.save(program);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newProgram, HttpStatus.OK);

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
        Optional<Program> optionalProgram = programRepository.findById(id);
        if(optionalProgram.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Program programToUpdate = optionalProgram.get();
        programToUpdate.updateProgram(program);
        try{
            programToUpdate = programRepository.save(programToUpdate);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(programToUpdate, HttpStatus.OK);
    }


}
