package com.fatec.backend.service.implementation;

import com.fatec.backend.form.SeboForm;
import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.model.Sebo;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.SeboRepository;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.SeboService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeboServiceImplementation implements SeboService {

    @Autowired
    public SeboRepository seboRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Sebo save(SeboForm seboForm) {
        Sebo sebo = new Sebo();
        Optional<Usuario> usuario = usuarioRepository.findById(seboForm.getUsuario());
        if(usuario.isPresent()){
            sebo.setNome(seboForm.getNome());
            sebo.setData(seboForm.getData());
            sebo.setLogradouro(seboForm.getLogradouro());
            sebo.setNumero(seboForm.getNumero());
            sebo.setCidade(seboForm.getCidade());
            sebo.setEstado(seboForm.getEstado());
            sebo.setCep(seboForm.getCep());
            sebo.setEstado(seboForm.getEstado());
            sebo.setUsuario(usuario.get());
            return seboRepository.save(sebo);
        }
        return null;
    }

    @Override
    public Sebo update(Long id, SeboForm seboForm) {
        Optional<Sebo> seboExistente = seboRepository.findById(Math.toIntExact(id));
        if(seboExistente.isPresent()){
            seboExistente.get().setNome(seboForm.getNome());
            seboExistente.get().setLogradouro(seboForm.getLogradouro());
            seboExistente.get().setNumero(seboForm.getNumero());
            seboExistente.get().setCidade(seboForm.getCidade());
            seboExistente.get().setEstado(seboForm.getEstado());
            seboExistente.get().setCep(seboForm.getCep());
            return seboRepository.save(seboExistente.get());
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
