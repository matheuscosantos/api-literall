package com.fatec.backend.controller;

import com.fatec.backend.form.EventoForm;
import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.model.Evento;
import com.fatec.backend.repository.EventoRepository;
import com.fatec.backend.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evento")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @Autowired
    EventoRepository eventoRepository;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Evento> saveEvento(@RequestBody EventoForm evento){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.save(evento));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody EventoForm eventoForm){
        try{
            Optional<Evento> evento = eventoService.findById(id);
            if(evento.isPresent()){
                Evento eventoAtualizado = eventoService.update(id, eventoForm);
                return new ResponseEntity<Evento>(eventoAtualizado, HttpStatus.OK);
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

    @GetMapping("/cidade/{cidade}")
    public List<Evento> getEventoByCidade(@PathVariable(value = "cidade") String cidade){
        return eventoService.findByCidade(cidade);
    }
}
