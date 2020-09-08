package com.fatec.backend.controller;

import com.fatec.backend.form.LivroForm;
import com.fatec.backend.model.Evento;
import com.fatec.backend.model.Livro;
import com.fatec.backend.repository.LivroRepository;
import com.fatec.backend.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    LivroService livroService;

    @Autowired
    LivroRepository livroRepository;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Livro> saveLivro(@RequestBody LivroForm livro){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livro));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Livro> getLivro(@PathVariable(value = "id") Long id){
        try{
            Optional<Livro> livro = livroService.findById(id);
            if(livro.isPresent()){
                return new ResponseEntity<>(livro.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Livro> updateLivro(@RequestBody LivroForm livroUpdated, @PathVariable(value="id") Long id){
        try{
            Optional<Livro> livro = livroService.findById(id);
            if(livro.isPresent()){
                return new ResponseEntity<Livro>(livroService.save(livroUpdated), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLivro(@PathVariable Long id) {
        try {
            this.livroService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public Page<Livro> listAllEventos(@PageableDefault(sort="id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Livro> livros = livroRepository.findAll(pageable);
        return livros;
    }
}
