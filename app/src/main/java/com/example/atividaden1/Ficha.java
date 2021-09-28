package com.example.atividaden1;

public class Ficha {

    public int id;
    public String nome, level, classes;

    public Ficha() {

    }

    public Ficha(String nome, String level, String classes) {
        this.nome = nome;
        this.level = level;
        this.classes = classes;
    }

    public Ficha(int id, String nome, String level, String classes) {
        this.id = id;
        this.nome = nome;
        this.level = level;
        this.classes = classes;
    }

    public Ficha(String lista_vazia_, String s) {
    }


    @Override
    public String toString() {
        return  nome + "  |  "  + level + "  |  " + classes ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}