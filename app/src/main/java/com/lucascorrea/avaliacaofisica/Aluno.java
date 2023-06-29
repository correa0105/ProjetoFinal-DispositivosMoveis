package com.lucascorrea.avaliacaofisica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluno implements Serializable {
    private int id;
    private String  nome;
    private double altura;
    private double peso;

    public Aluno(String nome, double altura, double peso) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
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

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
