package com.fatec.backend.service.implementation;

import com.fatec.backend.model.Sebo;
import com.fatec.backend.repository.SeboRepository;
import com.fatec.backend.service.SeboService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeboServiceImplementation implements SeboService {

    @Autowired
    public SeboRepository seboRepository;

    @Override
    public Sebo save(Sebo sebo) {
        return seboRepository.save(sebo);
    }

    @Override
    public Sebo update(Long id, Sebo sebo) {
        Optional<Sebo> seboExistente = seboRepository.findById(Math.toIntExact(sebo.getId()));
        if(seboExistente.isPresent()){
            seboExistente.get().setNome(sebo.getNome());
            seboExistente.get().setEndereco(sebo.getEndereco());
            seboExistente.get().setUsuario(sebo.getUsuario());
            return seboRepository.save(sebo);
        }
        return null;
    }

    @Override
    public Optional<Sebo> findById(Long id) {
        return seboRepository.findById(Math.toIntExact(id));
    }

    @Override
    public void deleteById(Long id) {
        Sebo sebo = seboRepository.findById(Math.toIntExact(id)).orElseThrow(()-> new ObjectNotFoundException(
                "Biblioteca not found.", Sebo.class.getName()));
        seboRepository.deleteById(Math.toIntExact(sebo.getId()));
    }
}
