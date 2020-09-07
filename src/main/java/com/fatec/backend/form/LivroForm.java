package com.fatec.backend.form;

import com.fatec.backend.enums.Avaliacao;
import com.fatec.backend.enums.Status;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class LivroForm {
    private String titulo;
    private String editora;
    private String isbn;
    private String idioma;
    private String descricao;
    private String categoria;
    private String edicao;
    private String autor;
    private Status status;
    private Avaliacao avaliacao;
    private Integer usuario;
}
