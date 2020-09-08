package com.fatec.backend.controller;

import com.fatec.backend.form.UsuarioForm;
import com.fatec.backend.model.Sebo;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.UsuarioService;
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
@RequestMapping("/api/Usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioForm usuarioForm){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioForm));
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
    public ResponseEntity<Usuario> updateUsuario(@RequestBody UsuarioForm usuarioUpdated, @PathVariable(value="id") Long id){
        try{
            Optional<Usuario> usuario = usuarioService.findById(id);
            if(usuario.isPresent()){
                return new ResponseEntity<Usuario>(usuarioService.save(usuarioUpdated), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable Long id) {
        try {
            this.usuarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public Page<Usuario> listAllEventos(@PageableDefault(sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios;
    }
}
