package com.fatec.backend.service;

import com.fatec.backend.model.Endereco;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface EnderecoService {
    public Endereco save(Endereco endereco);
    public Endereco update(Long id, Endereco endereco);
    public Optional<Endereco> findById(Long id);
    public void deleteById(Long id);
}
