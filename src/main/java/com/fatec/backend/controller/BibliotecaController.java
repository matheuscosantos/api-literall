package com.fatec.backend.controller;

import com.fatec.backend.form.BibliotecaForm;
import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.repository.BibliotecaRepository;
import com.fatec.backend.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @Autowired
    BibliotecaRepository bibliotecaRepository;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Biblioteca> saveBiblioteca(@RequestBody BibliotecaForm biblioteca){
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
    public ResponseEntity<Biblioteca> updateBiblioteca(@RequestBody BibliotecaForm bibliotecaUpdated, @PathVariable(value="id") Long id){
        try{
            Optional<Biblioteca> biblioteca = bibliotecaService.findById(id);
            if(biblioteca.isPresent()){
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

    @GetMapping
    public Page<Biblioteca> listAllBibliotecas(@PageableDefault(sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Biblioteca> bibliotecas = bibliotecaRepository.findAll(pageable);
        return bibliotecas;
    }
}
