package com.fatec.backend.service;

import com.fatec.backend.form.BibliotecaForm;
import com.fatec.backend.model.Biblioteca;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BibliotecaService {
    public Biblioteca save(BibliotecaForm bibliotecaForm);
    public Biblioteca update(Long id, BibliotecaForm bibliotecaForm);
    public Optional<Biblioteca> findById(Long id);
    public void deleteById(Long id);

    List<Biblioteca> findByCidade(String cidade);
}
