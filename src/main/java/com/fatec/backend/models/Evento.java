package com.fatec.backend.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private LocalDate data;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualização;

    @ManyToOne
    private Endereco endereco;
    @ManyToOne
    private Usuario usuario;
}
