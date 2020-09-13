package com.fatec.backend.service;

import com.fatec.backend.form.LivroForm;
import com.fatec.backend.model.Livro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    public Livro save(LivroForm livroForm);
    public Livro update(Long id, LivroForm livroForm);
    public Optional<Livro> findById(Long id);
    public void deleteById(Long id);
}
