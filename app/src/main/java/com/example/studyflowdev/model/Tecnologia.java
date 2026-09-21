package com.example.studyflowdev.model;

public class Tecnologia {
    private long id;
    private String nome;

    public Tecnologia() {
    }

    public Tecnologia(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Tecnologia(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
