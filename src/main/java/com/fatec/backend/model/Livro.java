package com.fatec.backend.model;

import com.fatec.backend.enums.Avaliacao;
import com.fatec.backend.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String edicao;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualizacao;
    private String autor;
    private Status status;
    private Avaliacao avaliacao;

    @ManyToOne
    private Usuario usuario;
}
