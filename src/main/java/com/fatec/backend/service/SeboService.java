package com.fatec.backend.service;

import com.fatec.backend.form.SeboForm;
import com.fatec.backend.model.Sebo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface SeboService {
    public Sebo save(SeboForm seboForm);
    public Sebo update(Long id, SeboForm seboForm);
    public Optional<Sebo> findById(Long id);
    public void deleteById(Long id);

    List<Sebo> findByCidade(String cidade);
}
