package com.example.demo.raport;

import com.example.demo.program.Program;
import com.example.demo.program.ProgramRepository;
import com.example.demo.programmer.Programmer;
import com.example.demo.programmer.ProgrammerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/raport")
public class RaportController {
    private final RaportRepository raportRepository;
    private final ProgramRepository programRepository;
    private final ProgrammerRepository programmerRepository;

    public RaportController(RaportRepository raportRepository, ProgramRepository programRepository, ProgrammerRepository programmerRepository) {
        this.raportRepository = raportRepository;
        this.programRepository = programRepository;
        this.programmerRepository = programmerRepository;
    }
    @Transactional
    @GetMapping("/getAll")
    public ResponseEntity<List<Raport>>getAll(){
        List<Raport> result = raportRepository.findAll();
        if(result.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/findById")
        public ResponseEntity<Optional<Raport>> findById(@RequestParam("id") Long id){
        if(id == null || id<1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }Optional<Raport> result = raportRepository.findById(id);
        if(result.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/create")
    public ResponseEntity<Raport> createRaport(@RequestBody RaportWrapper raportWrapper) {

        Raport raport = new Raport();

        raport.setTitle(raportWrapper.getTitle());
        raport.setDate(raportWrapper.getDate());
        raport.setDescription(raportWrapper.getDescription());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        raport.setTimestamp(timestamp);

        Long programmerId= raportWrapper.getProgrammerId();
        Long programId= raportWrapper.getProgramId();

        Optional<Programmer> programmer = programmerRepository.findById(programmerId);
        Optional<Program> program = programRepository.findById(programId);
        if(programmer.isEmpty() || program.isEmpty()) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        raport.setProgrammer(programmer.get());
        raport.setProgram(program.get());
        try{
            raportRepository.save(raport);
        }catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<>(raport,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id){
        if(id == null || id<1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        raportRepository.deleteById(id);
        return  new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/update")
    public ResponseEntity<Raport> update(@RequestBody Raport raport){
        Long id = raport.getId();
        Optional<Raport> result = raportRepository.findById(id);
        if(result.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try{
            raport = raportRepository.save(raport);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(raport, HttpStatus.OK);
    }
}
