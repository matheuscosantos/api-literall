package com.fatec.backend.service;

import com.fatec.backend.model.Evento;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface EventoService {
    public Evento save(Evento evento);
    public Evento update(Long id, Evento evento);
    public Optional<Evento> findById(Long id);
    public void deleteById(Long id);
}
