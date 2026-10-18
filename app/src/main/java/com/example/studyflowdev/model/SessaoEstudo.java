package com.example.studyflowdev.model;

public class SessaoEstudo {
    private long id;
    private long usuarioId;
    private long conteudoId;
    private int duracaoMinutos;
    private String dataSessao;
    private String observacoes;

    public SessaoEstudo() {
    }

    public SessaoEstudo(long id, long usuarioId, long conteudoId, int duracaoMinutos, String dataSessao, String observacoes) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.conteudoId = conteudoId;
        this.duracaoMinutos = duracaoMinutos;
        this.dataSessao = dataSessao;
        this.observacoes = observacoes;
    }

    public SessaoEstudo(long usuarioId, long conteudoId, int duracaoMinutos, String observacoes) {
        this.usuarioId = usuarioId;
        this.conteudoId = conteudoId;
        this.duracaoMinutos = duracaoMinutos;
        this.observacoes = observacoes;
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

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(String dataSessao) {
        this.dataSessao = dataSessao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
