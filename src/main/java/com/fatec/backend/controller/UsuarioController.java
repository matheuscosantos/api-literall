package com.fatec.backend.controller;

import com.fatec.backend.model.Usuario;
import com.fatec.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/Usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "id") Long id){
        try{
            Optional<Usuario> usuario = usuarioService.findById(id);
            if(usuario.isPresent()){
                return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuarioUpdated, @PathVariable(value="id") Long id){
        try{
            Optional<Usuario> usuario = usuarioService.findById(id);
            if(usuario.isPresent()){
                usuarioUpdated.setId(usuario.get().getId());
                return new ResponseEntity<Usuario>(usuarioService.save(usuarioUpdated), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
