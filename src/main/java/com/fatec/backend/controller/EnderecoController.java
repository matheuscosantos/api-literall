package com.fatec.backend.controller;

import com.fatec.backend.model.Endereco;
import com.fatec.backend.model.Endereco;
import com.fatec.backend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(endereco));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable(value = "id") Long id){
        try{
            Optional<Endereco> endereco = enderecoService.findById(id);
            if(endereco.isPresent()){
                return new ResponseEntity<>(endereco.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco enderecoUpdated, @PathVariable(value="id") Long id){
        try{
            Optional<Endereco> endereco = enderecoService.findById(id);
            if(endereco.isPresent()){
                enderecoUpdated.setId(endereco.get().getId());
                return new ResponseEntity<Endereco>(enderecoService.save(enderecoUpdated), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEndereco(@PathVariable Long id) {
        try {
            this.enderecoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
