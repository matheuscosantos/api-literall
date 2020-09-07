package com.fatec.backend.controller;

import com.fatec.backend.form.SeboForm;
import com.fatec.backend.model.Sebo;
import com.fatec.backend.service.SeboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/sebo")
public class SeboController {

    @Autowired
    SeboService seboService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Sebo> saveSebo(@RequestBody SeboForm sebo){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(seboService.save(sebo));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Sebo> getSebo(@PathVariable(value = "id") Long id){
        try{
            Optional<Sebo> sebo = seboService.findById(id);
            if(sebo.isPresent()){
                return new ResponseEntity<>(sebo.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Sebo> updateSebo(@RequestBody SeboForm seboUpdated, @PathVariable(value="id") Long id){
        try{
            Optional<Sebo> sebo = seboService.findById(id);
            if(sebo.isPresent()){
                return new ResponseEntity<Sebo>(seboService.save(seboUpdated), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSebo(@PathVariable Long id) {
        try {
            this.seboService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
