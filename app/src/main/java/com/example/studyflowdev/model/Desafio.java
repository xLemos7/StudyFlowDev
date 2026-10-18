package com.example.studyflowdev.model;

public class Desafio {
    private long id;
    private long usuarioId;
    private long tecnologiaId;
    private String titulo;
    private String descricao;
    private String nivelDificuldade; // Ex: FACIL, MEDIO, DIFICIL
    private String status; // Ex: PENDENTE, CONCLUIDO

    public Desafio() {
    }

    public Desafio(long id, long usuarioId, long tecnologiaId, String titulo, String descricao, String nivelDificuldade, String status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tecnologiaId = tecnologiaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nivelDificuldade = nivelDificuldade;
        this.status = status;
    }

    public Desafio(long usuarioId, long tecnologiaId, String titulo, String descricao, String nivelDificuldade, String status) {
        this.usuarioId = usuarioId;
        this.tecnologiaId = tecnologiaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nivelDificuldade = nivelDificuldade;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getTecnologiaId() {
        return tecnologiaId;
    }

    public void setTecnologiaId(long tecnologiaId) {
        this.tecnologiaId = tecnologiaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(String nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
