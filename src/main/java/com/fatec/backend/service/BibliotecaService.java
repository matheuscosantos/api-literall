package com.fatec.backend.service;

import com.fatec.backend.form.BibliotecaForm;
import com.fatec.backend.model.Biblioteca;

import java.util.Optional;

public interface BibliotecaService {
    public Biblioteca save(BibliotecaForm bibliotecaForm);
    public Biblioteca update(Long id, BibliotecaForm bibliotecaForm);
    public Optional<Biblioteca> findById(Long id);
    public void deleteById(Long id);
}
