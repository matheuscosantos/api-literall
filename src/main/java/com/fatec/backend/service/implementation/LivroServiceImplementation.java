package com.fatec.backend.service.implementation;

import com.fatec.backend.form.LivroForm;
import com.fatec.backend.model.Livro;
import com.fatec.backend.model.Usuario;
import com.fatec.backend.repository.LivroRepository;
import com.fatec.backend.repository.UsuarioRepository;
import com.fatec.backend.service.LivroService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LivroServiceImplementation implements LivroService {

    @Autowired
    public LivroRepository livroRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Livro save(LivroForm livroForm) {
        Livro livro = new Livro();
        Optional<Usuario> usuario = usuarioRepository.findById(livroForm.getUsuario());
        if(usuario.isPresent()) {
            livro.setTitulo(livroForm.getTitulo());
            livro.setEditora(livroForm.getEditora());
            livro.setIsbn(livroForm.getIsbn());
            livro.setIdioma(livroForm.getIdioma());
            livro.setDescricao(livroForm.getDescricao());
            livro.setCategoria(livroForm.getCategoria());
            livro.setEdicao(livroForm.getEdicao());
            livro.setDataDeAtualizacao(LocalDateTime.now());
            livro.setAutor(livroForm.getAutor());
            livro.setStatus(livroForm.getStatus());
            livro.setAvaliacao(livroForm.getAvaliacao());
            livro.setUsuario(usuario.get());
            return livroRepository.save(livro);
        }
        return null;
    }

    @Override
    public Livro update(Long id, LivroForm livroForm) {
        Optional<Livro> livroExistente = livroRepository.findById(Math.toIntExact(id));
        if(livroExistente.isPresent()){
            livroExistente.get().setTitulo(livroForm.getTitulo());
            livroExistente.get().setEditora(livroForm.getEditora());
            livroExistente.get().setIsbn(livroForm.getIsbn());
            livroExistente.get().setIdioma(livroForm.getIdioma());
            livroExistente.get().setDescricao(livroForm.getDescricao());
            livroExistente.get().setCategoria(livroForm.getCategoria());
            livroExistente.get().setEdicao(livroForm.getEdicao());
            livroExistente.get().setAutor(livroForm.getAutor());
            livroExistente.get().setStatus(livroForm.getStatus());
            livroExistente.get().setAvaliacao(livroForm.getAvaliacao());
            livroExistente.get().setDataDeAtualizacao(LocalDateTime.now());
            return livroRepository.save(livroExistente.get());
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
