package com.fatec.backend.controller;

import com.fatec.backend.form.EventoForm;
import com.fatec.backend.model.Evento;
import com.fatec.backend.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Evento> saveEvento(@RequestBody EventoForm evento){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.save(evento));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Evento> getEvento(@PathVariable(value = "id") Long id){
        try{
            Optional<Evento> evento = eventoService.findById(id);
            if(evento.isPresent()){
                return new ResponseEntity<>(evento.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Evento> updateEvento(@RequestBody EventoForm eventoUpdated, @PathVariable(value="id") Long id){
        try{
            Optional<Evento> evento = eventoService.findById(id);
            if(evento.isPresent()){
                return new ResponseEntity<Evento>(eventoService.save(eventoUpdated), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvento(@PathVariable Long id) {
        try {
            this.eventoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
