package com.example.studyflowdev.model;

public class Meta {
    private long id;
    private long usuarioId;
    private String titulo;
    private String descricao;
    private int horasAlvo;
    private String dataLimite;
    private String status; // Ex: PENDENTE, CONCLUIDA

    public Meta() {
    }

    public Meta(long id, long usuarioId, String titulo, String descricao, int horasAlvo, String dataLimite, String status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.horasAlvo = horasAlvo;
        this.dataLimite = dataLimite;
        this.status = status;
    }

    public Meta(long usuarioId, String titulo, String descricao, int horasAlvo, String dataLimite, String status) {
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.horasAlvo = horasAlvo;
        this.dataLimite = dataLimite;
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

    public int getHorasAlvo() {
        return horasAlvo;
    }

    public void setHorasAlvo(int horasAlvo) {
        this.horasAlvo = horasAlvo;
    }

    public String getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
