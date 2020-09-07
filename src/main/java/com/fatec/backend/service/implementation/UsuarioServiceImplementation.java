package com.fatec.backend.service.implementation;

import com.fatec.backend.form.UsuarioForm;
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
    public Usuario save(UsuarioForm usuarioForm) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioForm.getNome());
        usuario.setEmail(usuarioForm.getEmail());
        usuario.setTelefone(usuarioForm.getTelefone());
        usuario.setSenha(usuarioForm.getSenha());
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, UsuarioForm usuarioForm) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(Math.toIntExact(id));
        if(usuarioExistente.isPresent()){
            usuarioExistente.get().setNome(usuarioForm.getNome());
            usuarioExistente.get().setEmail(usuarioForm.getEmail());
            usuarioExistente.get().setTelefone(usuarioForm.getTelefone());
            usuarioExistente.get().setSenha(usuarioForm.getSenha());
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
