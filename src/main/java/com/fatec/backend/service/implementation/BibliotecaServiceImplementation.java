package com.fatec.backend.service.implementation;

import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.repository.BibliotecaRepository;
import com.fatec.backend.service.BibliotecaService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class BibliotecaServiceImplementation implements BibliotecaService {

    @Autowired
    public BibliotecaRepository bibliotecaRepository;

    @Override
    public Biblioteca save(Biblioteca biblioteca) {
        return bibliotecaRepository.save(biblioteca);
    }

    @Override
    public Biblioteca update(Long id, Biblioteca biblioteca) {
        Optional<Biblioteca> bibliotecaExistente = bibliotecaRepository.findById(Math.toIntExact(biblioteca.getId()));
        if(bibliotecaExistente.isPresent()){
            bibliotecaExistente.get().setNome(biblioteca.getNome());
            bibliotecaExistente.get().setEndereco(biblioteca.getEndereco());
            bibliotecaExistente.get().setUsuario(biblioteca.getUsuario());
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
