package com.fatec.backend.controller;

import com.fatec.backend.form.UsuarioForm;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioForm usuarioForm){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioForm));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody UsuarioForm usuarioForm) {
        try{
            Optional<Usuario> usuario = usuarioService.findById(id);
            if(usuario.isPresent()){
                Usuario usuarioAtualizado = usuarioService.update(id, usuarioForm);
                return new ResponseEntity<Usuario>(usuarioAtualizado, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable Long id) {
        try {
            this.usuarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
