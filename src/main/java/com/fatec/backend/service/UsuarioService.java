package com.fatec.backend.service;

import com.fatec.backend.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsuarioService {
    public Usuario save(Usuario usuario);
    public Usuario update(Long id, Usuario usuario);
    public Optional<Usuario> findById(Long id);
    public void deleteById(Long id);
}
