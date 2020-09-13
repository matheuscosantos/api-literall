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
import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImplementation implements LivroService {

    @Autowired
    public LivroRepository livroRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Livro save(LivroForm livroForm) {
        Optional<Usuario> usuario = usuarioRepository.findById(livroForm.getUsuario());
        if(usuario.isPresent()) {
            Livro livro = new Livro(
                livroForm.getTitulo(),
                livroForm.getEditora(),
                livroForm.getIsbn(),
                livroForm.getIdioma(),
                livroForm.getDescricao(),
                livroForm.getCategoria(),
                livroForm.getEdicao(),
                livroForm.getAutor(),
                livroForm.getStatus(),
                livroForm.getAvaliacao(),
                usuario.get()
            );
            return livroRepository.save(livro);
        }
        return null;
    }

    @Override
    public Livro update(Long id, LivroForm livroForm) {
        Optional<Livro> livroExistente = livroRepository.findById(id);
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
        return livroRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
                "Livro não encontrado.", Livro.class.getName()));
        livroRepository.deleteById(livro.getId());
    }

}
