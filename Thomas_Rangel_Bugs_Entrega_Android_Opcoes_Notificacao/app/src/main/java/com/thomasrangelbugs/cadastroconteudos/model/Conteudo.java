package com.thomasrangelbugs.cadastroconteudos.model;

public class Conteudo {
    private final int id;
    private final String titulo;
    private final String descricao;

    public Conteudo(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
