package com.fatec.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sebo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate data;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualizacao;

    @ManyToOne
    private Endereco endereco;

    @ManyToOne
    private Usuario usuario;
}
