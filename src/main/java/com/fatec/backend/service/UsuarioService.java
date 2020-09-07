package com.fatec.backend.service;

import com.fatec.backend.form.UsuarioForm;
import com.fatec.backend.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UsuarioService {
    public Usuario save(UsuarioForm usuarioForm);
    public Usuario update(Long id, UsuarioForm usuarioForm);
    public Optional<Usuario> findById(Long id);
    public void deleteById(Long id);
}
