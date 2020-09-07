package com.fatec.backend.service.implementation;

import com.fatec.backend.model.Evento;
import com.fatec.backend.repository.EventoRepository;
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

    @Override
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public Evento update(Long id, Evento evento) {
        Optional<Evento> eventoExistente = eventoRepository.findById(Math.toIntExact(evento.getId()));
        if(eventoExistente.isPresent()){
            eventoExistente.get().setNome(evento.getNome());
            eventoExistente.get().setCategoria(evento.getCategoria());
            eventoExistente.get().setLogradouro(evento.getLogradouro());
            eventoExistente.get().setNumero(evento.getNumero());
            eventoExistente.get().setCidade(evento.getCidade());
            eventoExistente.get().setEstado(evento.getEstado());
            eventoExistente.get().setCep(evento.getCep());
            eventoExistente.get().setData(evento.getData());
            eventoExistente.get().setUsuario(evento.getUsuario());
            eventoExistente.get().setDataDeAtualizacao(LocalDateTime.now());
            return eventoRepository.save(evento);
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
