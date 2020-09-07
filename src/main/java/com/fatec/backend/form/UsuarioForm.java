package com.fatec.backend.form;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UsuarioForm {
    private String nome;
    private String email;
    private String telefone;
    private String senha;
}
