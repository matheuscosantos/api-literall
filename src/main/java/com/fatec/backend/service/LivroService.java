package com.fatec.backend.service;

import com.fatec.backend.model.Livro;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface LivroService {
    public Livro save(Livro livro);
    public Livro update(Long id, Livro Livro);
    public Optional<Livro> findById(Long id);
    public void deleteById(Long id);
}
