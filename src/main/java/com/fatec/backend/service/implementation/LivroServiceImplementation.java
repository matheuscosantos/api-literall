package com.fatec.backend.service.implementation;

import com.fatec.backend.model.Livro;
import com.fatec.backend.repository.LivroRepository;
import com.fatec.backend.service.LivroService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class LivroServiceImplementation implements LivroService {

    @Autowired
    public LivroRepository livroRepository;

    @Override
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Livro update(Long id, Livro livro) {

        Optional<Livro> livroExistente = livroRepository.findById(Math.toIntExact(livro.getId()));
        if(livroExistente.isPresent()){
            livroExistente.get().setTitulo(livro.getTitulo());
            livroExistente.get().setEditora(livro.getEditora());
            livroExistente.get().setIsbn(livro.getIsbn());
            livroExistente.get().setIdioma(livro.getIdioma());
            livroExistente.get().setDescricao(livro.getDescricao());
            livroExistente.get().setCategoria(livro.getCategoria());
            livroExistente.get().setEdicao(livro.getEdicao());
            livroExistente.get().setAutor(livro.getAutor());
            livroExistente.get().setStatus(livro.getStatus());
            livroExistente.get().setAvaliacao(livro.getAvaliacao());
            livroExistente.get().setUsuario(livro.getUsuario());
            livroExistente.get().setDataDeAtualizacao(LocalDateTime.now());
            return livroRepository.save(livro);
        }
        return null;
    }

    @Override
    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(Math.toIntExact(id));
    }

    @Override
    public void deleteById(Long id) {
        Livro livro = livroRepository.findById(Math.toIntExact(id)).orElseThrow(()-> new ObjectNotFoundException(
                "Livro não encontrado.", Livro.class.getName()));
        livroRepository.deleteById(Math.toIntExact(livro.getId()));
    }
}
