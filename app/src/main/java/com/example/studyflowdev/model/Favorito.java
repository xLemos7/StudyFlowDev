package com.example.studyflowdev.model;

public class Favorito {
    private long id;
    private long usuarioId;
    private long conteudoId;

    public Favorito() {
    }

    public Favorito(long id, long usuarioId, long conteudoId) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.conteudoId = conteudoId;
    }

    public Favorito(long usuarioId, long conteudoId) {
        this.usuarioId = usuarioId;
        this.conteudoId = conteudoId;
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
}
