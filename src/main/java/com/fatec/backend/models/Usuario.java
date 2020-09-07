package com.fatec.backend.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualizacao;

    @ManyToOne
    private Endereco endereco;
}
