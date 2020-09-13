package com.fatec.backend.service.implementation;

import com.fatec.backend.form.UsuarioForm;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.UsuarioService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
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
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if(usuarioExistente.isPresent()){
            usuarioExistente.get().setNome(usuarioForm.getNome());
            usuarioExistente.get().setEmail(usuarioForm.getEmail());
            usuarioExistente.get().setTelefone(usuarioForm.getTelefone());
            usuarioExistente.get().setSenha(usuarioForm.getSenha());
            usuarioExistente.get().setDataDeAtualizacao(LocalDateTime.now());
            usuarioRepository.save(usuarioExistente.get());
            return usuarioExistente.get();
        }
        return null;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
                "Event not found.", Event.class.getName()));
        usuarioRepository.deleteById(usuario.getId());
    }

}
