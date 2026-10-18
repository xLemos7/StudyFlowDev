package com.example.studyflowdev.model;

public class Anotacao {
    private long id;
    private long usuarioId;
    private long conteudoId;
    private String titulo;
    private String texto;
    private String dataCriacao;

    public Anotacao() {
    }

    public Anotacao(long id, long usuarioId, long conteudoId, String titulo, String texto, String dataCriacao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.conteudoId = conteudoId;
        this.titulo = titulo;
        this.texto = texto;
        this.dataCriacao = dataCriacao;
    }

    public Anotacao(long usuarioId, long conteudoId, String titulo, String texto) {
        this.usuarioId = usuarioId;
        this.conteudoId = conteudoId;
        this.titulo = titulo;
        this.texto = texto;
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

    public long getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(long conteudoId) {
        this.conteudoId = conteudoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
