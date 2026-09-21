package com.example.studyflowdev.model;

public class Conteudo {
    private long id;
    private long tecnologiaId;
    private String titulo;
    private String descricao;
    private int progresso; // 0 a 100

    public Conteudo() {
    }

    public Conteudo(long id, long tecnologiaId, String titulo, String descricao, int progresso) {
        this.id = id;
        this.tecnologiaId = tecnologiaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.progresso = progresso;
    }

    public Conteudo(long tecnologiaId, String titulo, String descricao, int progresso) {
        this.tecnologiaId = tecnologiaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.progresso = progresso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getProgresso() {
        return progresso;
    }

    public void setProgresso(int progresso) {
        this.progresso = progresso;
    }
}
