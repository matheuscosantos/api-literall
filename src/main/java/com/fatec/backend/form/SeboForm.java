package com.fatec.backend.form;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SeboForm {
    private String nome;
    private LocalDate data;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private Long usuario;
}
