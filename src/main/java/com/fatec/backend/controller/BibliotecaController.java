package com.fatec.backend.controller;

import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

    @Autowired
    BibliotecaService bibliotecaService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Biblioteca> saveBiblioteca(@RequestBody Biblioteca biblioteca){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(bibliotecaService.save(biblioteca));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Biblioteca> getBiblioteca(@PathVariable(value = "id") Long id){
        try{
            Optional<Biblioteca> biblioteca = bibliotecaService.findById(id);
            if(biblioteca.isPresent()){
                return new ResponseEntity<>(biblioteca.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Biblioteca> updateBiblioteca(@RequestBody Biblioteca bibliotecaUpdated, @PathVariable(value="id") Long id){
        try{
            Optional<Biblioteca> biblioteca = bibliotecaService.findById(id);
            if(biblioteca.isPresent()){
                bibliotecaUpdated.setId(biblioteca.get().getId());
                return new ResponseEntity<Biblioteca>(bibliotecaService.save(bibliotecaUpdated), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBiblioteca(@PathVariable Long id) {
        try {
            this.bibliotecaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
