package com.fatec.backend.service;

import com.fatec.backend.form.EventoForm;
import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.model.Evento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EventoService {
    public Evento save(EventoForm eventoForm);
    public Evento update(Long id, EventoForm eventoForm);
    public Optional<Evento> findById(Long id);
    public void deleteById(Long id);

    List<Evento> findByCidade(String cidade);
}
