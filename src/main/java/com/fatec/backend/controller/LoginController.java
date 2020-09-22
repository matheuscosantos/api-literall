package com.fatec.backend.controller;

import com.fatec.backend.form.UsuarioLoginForm;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public boolean verifyLogin(@RequestBody UsuarioLoginForm usuarioLoginForm){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLoginForm.getEmail());
        if(usuario.isPresent()){
            if(usuario.get().getSenha().equals(usuarioLoginForm.getSenha())){
                return true;
            }
            return false;
        }
        return false;
    }
}
