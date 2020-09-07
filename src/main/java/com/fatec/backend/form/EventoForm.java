package com.fatec.backend.form;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class EventoForm {
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
    private Integer usuario;
}
