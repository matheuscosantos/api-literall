package com.fatec.backend.service.implementation;

import com.fatec.backend.form.BibliotecaForm;
import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.BibliotecaRepository;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.BibliotecaService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BibliotecaServiceImplementation implements BibliotecaService {

    @Autowired
    public BibliotecaRepository bibliotecaRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Biblioteca save(BibliotecaForm bibliotecaForm) {
        Biblioteca biblioteca = new Biblioteca();
        Optional<Usuario> usuario = usuarioRepository.findById(bibliotecaForm.getUsuario());
        if(usuario.isPresent()){
            biblioteca.setNome(bibliotecaForm.getNome());
            biblioteca.setData(bibliotecaForm.getData());
            biblioteca.setLogradouro(bibliotecaForm.getLogradouro());
            biblioteca.setNumero(bibliotecaForm.getNumero());
            biblioteca.setCidade(bibliotecaForm.getCidade());
            biblioteca.setEstado(bibliotecaForm.getEstado());
            biblioteca.setCep(bibliotecaForm.getCep());
            biblioteca.setEstado(bibliotecaForm.getEstado());
            biblioteca.setUsuario(usuario.get());
            return bibliotecaRepository.save(biblioteca);
        }
        return null;
    }

    @Override
    public Biblioteca update(Long id, BibliotecaForm bibliotecaForm) {
        Optional<Biblioteca> bibliotecaExistente = bibliotecaRepository.findById(Math.toIntExact(id));
        if(bibliotecaExistente.isPresent()){
            bibliotecaExistente.get().setNome(bibliotecaForm.getNome());
            bibliotecaExistente.get().setLogradouro(bibliotecaForm.getLogradouro());
            bibliotecaExistente.get().setNumero(bibliotecaForm.getNumero());
            bibliotecaExistente.get().setCidade(bibliotecaForm.getCidade());
            bibliotecaExistente.get().setEstado(bibliotecaForm.getEstado());
            bibliotecaExistente.get().setCep(bibliotecaForm.getCep());
            bibliotecaExistente.get().setDataDeAtualizacao(LocalDateTime.now());
            return  bibliotecaRepository.save(bibliotecaExistente.get());
        }
        return null;
    }

    @Override
    public Optional<Biblioteca> findById(Long id) {
        return bibliotecaRepository.findById(Math.toIntExact(id));
    }

    @Override
    public void deleteById(Long id) {
        Biblioteca biblioteca = bibliotecaRepository.findById(Math.toIntExact(id)).orElseThrow(()-> new ObjectNotFoundException(
                "Biblioteca not found.", Biblioteca.class.getName()));
        bibliotecaRepository.deleteById(Math.toIntExact(biblioteca.getId()));
    }
}
