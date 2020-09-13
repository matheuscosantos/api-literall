package com.fatec.backend.controller;

import com.fatec.backend.form.BibliotecaForm;
import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.repository.BibliotecaRepository;
import com.fatec.backend.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/biblioteca")
@CrossOrigin(origins = "*")
public class BibliotecaController {

    @Autowired
    BibliotecaService bibliotecaService;

    @Autowired
    BibliotecaRepository bibliotecaRepository;

    @PostMapping(produces = "application/json")
    @Transactional
    public ResponseEntity<Biblioteca> saveBiblioteca(@RequestBody BibliotecaForm biblioteca){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(bibliotecaService.save(biblioteca));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Biblioteca> update(@PathVariable Long id, @RequestBody BibliotecaForm bibliotecaForm){
        try{
            Optional<Biblioteca> biblioteca = bibliotecaService.findById(id);
            if(biblioteca.isPresent()){
                Biblioteca bibliotecaAtualizada = bibliotecaService.update(id, bibliotecaForm);
                return new ResponseEntity<Biblioteca>(bibliotecaAtualizada, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
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

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteBiblioteca(@PathVariable Long id) {
        try {
            this.bibliotecaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cidade/{cidade}")
    public List<Biblioteca> getBibliotecaByCidade(@PathVariable(value = "cidade") String cidade){
        return bibliotecaService.findByCidade(cidade);
    }

}
