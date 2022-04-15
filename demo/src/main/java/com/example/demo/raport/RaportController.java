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

    @GetMapping("/getAll")
    public ResponseEntity<List<Raport>>getAll(){
        List<Raport> listOfRaport = raportRepository.findAll();
        return  new ResponseEntity<>(listOfRaport, HttpStatus.OK);
    }

    @RequestMapping("/findById")
        public ResponseEntity<Optional<Raport>> findById(@RequestParam("id") Long id){
        Optional<Raport> optionalRaport = raportRepository.findById(id);
        if(optionalRaport.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalRaport, HttpStatus.OK);
    }

    @RequestMapping("/create")
    public ResponseEntity<Raport> createRaport(@RequestBody RaportWrapper raportWrapper) {

        Raport newRaport = new Raport();

        newRaport.setTitle(raportWrapper.getTitle());
        newRaport.setDescription(raportWrapper.getDescription());
        newRaport.setDate(raportWrapper.getDate());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        newRaport.setTimestamp(timestamp);

        Long programmerId= raportWrapper.getProgrammerId();
        Long programId= raportWrapper.getProgramId();

        Optional<Programmer> optionalProgrammer = programmerRepository.findById(programmerId);
        Optional<Program> optionalProgram = programRepository.findById(programId);
        if(optionalProgrammer.isEmpty() || optionalProgram.isEmpty()) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        newRaport.setProgrammer(optionalProgrammer.get());
        newRaport.setProgram(optionalProgram.get());
        try{
            raportRepository.save(newRaport);
        }catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<>(newRaport,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id){
        if(id == null || id<1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        raportRepository.deleteById(id);
        return  new ResponseEntity<>("Deleted with success", HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/update")
    public ResponseEntity<Raport> update(@RequestBody Raport raport){
        Long raportId = raport.getId();
        Optional<Raport> optionalRaport = raportRepository.findById(raportId);
        if(optionalRaport.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Raport raportToUpdate = optionalRaport.get();
        raportToUpdate.udpateRaport(raport);
        try{
            raportToUpdate = raportRepository.save(raportToUpdate);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(raportToUpdate, HttpStatus.OK);
    }
}
