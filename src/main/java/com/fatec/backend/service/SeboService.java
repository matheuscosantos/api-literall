package com.fatec.backend.service;

import com.fatec.backend.model.Sebo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SeboService {
    public Sebo save(Sebo sebo);
    public Sebo update(Long id, Sebo sebo);
    public Optional<Sebo> findById(Long id);
    public void deleteById(Long id);
}
