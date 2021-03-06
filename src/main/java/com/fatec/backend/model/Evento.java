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
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private LocalDate data;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeAtualizacao;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

    @ManyToOne
    private Usuario usuario;

    public Evento(String nome,
                  String categoria,
                  LocalDate data,
                  String logradouro,
                  String numero,
                  String cidade,
                  String estado,
                  String cep,
                  Usuario usuario) {
        this.nome = nome;
        this.categoria = categoria;
        this.data = data;
        this.dataDeCriacao = LocalDateTime.now();
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.usuario = usuario;
    }
}
