package com.fatec.backend.models;

import com.fatec.backend.models.Endereco;
import com.fatec.backend.models.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Sebo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate data;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualização;

    @ManyToOne
    private Endereco endereco;
    @ManyToOne
    private Usuario usuario;
}
