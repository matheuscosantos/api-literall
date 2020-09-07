package com.fatec.backend.models;

import com.fatec.backend.models.enums.Avaliacao;
import com.fatec.backend.models.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String editora;
    private String isbn;
    private String idioma;
    private String descricao;
    private String categoria;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualização;
    private String autor;
    private Status status;
    private Avaliacao avaliacao;
    @ManyToOne
    private Usuario usuario;

}
