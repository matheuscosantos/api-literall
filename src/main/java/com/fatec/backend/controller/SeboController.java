package com.fatec.backend.controller;

import com.fatec.backend.form.SeboForm;
import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.model.Livro;
import com.fatec.backend.model.Sebo;
import com.fatec.backend.repository.SeboRepository;
import com.fatec.backend.service.SeboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sebo")
@CrossOrigin(origins = "*")
public class SeboController {

    @Autowired
    SeboService seboService;

    @Autowired
    SeboRepository seboRepository;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Sebo> saveSebo(@RequestBody SeboForm sebo){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(seboService.save(sebo));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<Sebo> update(@RequestBody SeboForm seboForm, @PathVariable(value="id") Long id){
        try{
            Optional<Sebo> sebo = seboService.findById(id);
            if(sebo.isPresent()){
                Sebo seboAtualizado = seboService.update(id, seboForm);
                return new ResponseEntity<Sebo>(seboAtualizado, HttpStatus.OK);
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

    @GetMapping("/cidade/{cidade}")
    public List<Sebo> getSeboByCidade(@PathVariable(value = "cidade") String cidade){
        return seboService.findByCidade(cidade);
    }
}
