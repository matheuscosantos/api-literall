package com.fatec.backend.model;

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

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public LocalDateTime getDataDeAtualização() {
        return dataDeAtualização;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
