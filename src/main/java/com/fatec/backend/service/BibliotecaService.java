package com.fatec.backend.service;

import com.fatec.backend.model.Biblioteca;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BibliotecaService {
    public Biblioteca save(Biblioteca biblioteca);
    public Biblioteca update(Long id, Biblioteca biblioteca);
    public Optional<Biblioteca> findById(Long id);
    public void deleteById(Long id);
}
