package com.fatec.backend.service.implementation;

import com.fatec.backend.form.EventoForm;
import com.fatec.backend.model.Evento;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.EventoRepository;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.EventoService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EventoServiceImplementation implements EventoService {

    @Autowired
    public EventoRepository eventoRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Evento save(EventoForm eventoForm) {
        Evento evento = new Evento();
        Optional<Usuario> usuario = usuarioRepository.findById(eventoForm.getUsuario());
        if(usuario.isPresent()) {
            evento.setNome(eventoForm.getNome());
            evento.setCategoria(eventoForm.getCategoria());
            evento.setData(eventoForm.getData());
            evento.setDataDeAtualizacao(LocalDateTime.now());
            evento.setLogradouro(eventoForm.getLogradouro());
            evento.setNumero(eventoForm.getNumero());
            evento.setCidade(eventoForm.getCidade());
            evento.setEstado(eventoForm.getEstado());
            evento.setCep(eventoForm.getCep());
            evento.setUsuario(usuario.get());
            return eventoRepository.save(evento);
        }
        return null;
    }

    @Override
    public Evento update(Long id, EventoForm eventoForm) {

        Optional<Evento> eventoExistente = eventoRepository.findById(Math.toIntExact(id));

        if(eventoExistente.isPresent()){
            eventoExistente.get().setNome(eventoForm.getNome());
            eventoExistente.get().setCategoria(eventoForm.getCategoria());
            eventoExistente.get().setLogradouro(eventoForm.getLogradouro());
            eventoExistente.get().setNumero(eventoForm.getNumero());
            eventoExistente.get().setCidade(eventoForm.getCidade());
            eventoExistente.get().setEstado(eventoForm.getEstado());
            eventoExistente.get().setCep(eventoForm.getCep());
            eventoExistente.get().setData(eventoForm.getData());
            eventoExistente.get().setDataDeAtualizacao(LocalDateTime.now());
            return eventoRepository.save(eventoExistente.get());
        }
        return null;
    }

    @Override
    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(Math.toIntExact(id));
    }

    @Override
    public void deleteById(Long id) {
        Evento evento = eventoRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new ObjectNotFoundException(
                "Evento não encontrado.", Evento.class.getName()));
        eventoRepository.deleteById(Math.toIntExact(evento.getId()));
    }
}
