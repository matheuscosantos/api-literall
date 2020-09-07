package com.fatec.backend.service.implementation;

import com.fatec.backend.model.Endereco;
import com.fatec.backend.repository.EnderecoRepository;
import com.fatec.backend.service.EnderecoService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoServiceImplementation implements EnderecoService {

    @Autowired
    public EnderecoRepository enderecoRepository;

    @Override
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    public Endereco update(Long id, Endereco endereco) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(Math.toIntExact(endereco.getId()));
        if(enderecoExistente.isPresent()){
            enderecoExistente.get().setCep(endereco.getCep());
            enderecoExistente.get().setCidade(endereco.getCidade());
            enderecoExistente.get().setEstado(endereco.getEstado());
            enderecoExistente.get().setLogradouro(endereco.getLogradouro());
            enderecoExistente.get().setNumero(endereco.getNumero());
            return  enderecoRepository.save(endereco);
        }
        return null;
    }

    @Override
    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(Math.toIntExact(id));
    }

    @Override
    public void deleteById(Long id) {
        Endereco endereco = enderecoRepository.findById(Math.toIntExact(id)).orElseThrow(()-> new ObjectNotFoundException(
                "Biblioteca not found.", Endereco.class.getName()));
        enderecoRepository.deleteById(Math.toIntExact(endereco.getId()));
    }
}
