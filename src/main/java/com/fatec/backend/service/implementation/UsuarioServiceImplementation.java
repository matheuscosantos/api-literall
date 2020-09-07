package com.fatec.backend.service.implementation;

import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.UsuarioService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;

@Service
public class UsuarioServiceImplementation implements UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(Math.toIntExact(usuario.getId()));
        if(usuarioExistente.isPresent()){
            usuarioExistente.get().setNome(usuario.getNome());
            usuarioExistente.get().setEmail(usuario.getEmail());
            usuarioExistente.get().setTelefone(usuario.getTelefone());
            usuarioExistente.get().setSenha(usuario.getSenha());
            return usuarioRepository.save(usuarioExistente.get());
        }
        return null;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(Math.toIntExact(id));
    }

    @Override
    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findById(Math.toIntExact(id)).orElseThrow(()-> new ObjectNotFoundException(
                "Event not found.", Event.class.getName()));
        usuarioRepository.deleteById(Math.toIntExact(usuario.getId()));
    }
}
