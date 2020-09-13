package com.fatec.backend.service.implementation;

import com.fatec.backend.form.EventoForm;
import com.fatec.backend.model.Biblioteca;
import com.fatec.backend.model.Evento;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.EventoRepository;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.EventoService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImplementation implements EventoService {

    @Autowired
    public EventoRepository eventoRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Evento save(EventoForm eventoForm) {
        Optional<Usuario> usuario = usuarioRepository.findById(eventoForm.getUsuario());
        if(usuario.isPresent()) {
            Evento evento = new Evento(
                eventoForm.getNome(),
                eventoForm.getCategoria(),
                eventoForm.getData(),
                eventoForm.getLogradouro(),
                eventoForm.getNumero(),
                eventoForm.getCidade(),
                eventoForm.getEstado(),
                eventoForm.getCep(),
                usuario.get()
            );
            return eventoRepository.save(evento);
        }
        return null;
    }

    @Override
    public Evento update(Long id, EventoForm eventoForm) {
        Optional<Evento> eventoExistente = eventoRepository.findById(id);
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
        return eventoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Evento não encontrado.", Evento.class.getName()));
        eventoRepository.deleteById(evento.getId());
    }

    @Override
    public List<Evento> findByCidade(String cidade) {
        return eventoRepository.findByCidade(cidade);
    }
}
