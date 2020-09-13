package com.fatec.backend.service.implementation;

import com.fatec.backend.form.SeboForm;
import com.fatec.backend.model.Sebo;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.SeboRepository;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.SeboService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SeboServiceImplementation implements SeboService {

    @Autowired
    public SeboRepository seboRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Sebo save(SeboForm seboForm) {
        Optional<Usuario> usuario = usuarioRepository.findById(seboForm.getUsuario());
        if(usuario.isPresent()){
            Sebo sebo = new Sebo(
                seboForm.getNome(),
                seboForm.getData(),
                seboForm.getLogradouro(),
                seboForm.getNumero(),
                seboForm.getCidade(),
                seboForm.getEstado(),
                seboForm.getCep(),
                usuario.get()
            );
            return seboRepository.save(sebo);
        }
        return null;
    }

    @Override
    public Sebo update(Long id, SeboForm seboForm) {
        Optional<Sebo> seboExistente = seboRepository.findById(id);
        if(seboExistente.isPresent()){
            seboExistente.get().setNome(seboForm.getNome());
            seboExistente.get().setLogradouro(seboForm.getLogradouro());
            seboExistente.get().setNumero(seboForm.getNumero());
            seboExistente.get().setCidade(seboForm.getCidade());
            seboExistente.get().setEstado(seboForm.getEstado());
            seboExistente.get().setCep(seboForm.getCep());
            seboExistente.get().setDataDeAtualizacao(LocalDateTime.now());
            return seboRepository.save(seboExistente.get());
        }
        return null;
    }

    @Override
    public Optional<Sebo> findById(Long id) {
        return seboRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        Sebo sebo = seboRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
                "Biblioteca not found.", Sebo.class.getName()));
        seboRepository.deleteById(sebo.getId());
    }

    @Override
    public List<Sebo> findByCidade(String cidade) {
        return seboRepository.findByCidade(cidade);
    }
}
