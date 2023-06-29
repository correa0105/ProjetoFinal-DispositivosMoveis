package com.lucascorrea.avaliacaofisica;

public class IndiceDeGordura {
    private int id;
    private Aluno aluno;
    private String data;
    private double indiceDeGorduraCorporal;
    private double dobraTriceps;
    private double dobraSubescapular;
    private double dobraSuprailiaca;
    private double dobraAbdominal;

    public IndiceDeGordura(Aluno aluno, String data, double indiceDeGorduraCorporal) {
        this.aluno = aluno;
        this.data = data;
        this.indiceDeGorduraCorporal = indiceDeGorduraCorporal;
    }

    public IndiceDeGordura(Aluno aluno, String data, double dobraTriceps, double dobraSubescapular, double dobraSuprailiaca, double dobraAbdominal) {
        this.aluno = aluno;
        this.data = data;
        this.dobraTriceps = dobraTriceps;
        this.dobraSubescapular = dobraSubescapular;
        this.dobraSuprailiaca = dobraSuprailiaca;
        this.dobraAbdominal = dobraAbdominal;
        this.indiceDeGorduraCorporal = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getIndiceDeGorduraCorporal() {
        return indiceDeGorduraCorporal;
    }

    public void setIndiceDeGorduraCorporal(double indiceDeGorduraCorporal) {
        this.indiceDeGorduraCorporal = indiceDeGorduraCorporal;
    }

    public double getDobratriceps() {
        return dobraTriceps;
    }

    public void setDobratriceps(double dobraTriceps) {
        this.dobraTriceps = dobraTriceps;
    }

    public double getDobraSubescapular() {
        return dobraSubescapular;
    }

    public void setDobraSubescapular(double dobraSubescapular) {
        this.dobraSubescapular = dobraSubescapular;
    }

    public double getDobraSuprailiaca() {
        return dobraSuprailiaca;
    }

    public void setDobraSuprailiaca(double dobraSuprailiaca) {
        this.dobraSuprailiaca = dobraSuprailiaca;
    }

    public double getDobraAbdominal() {
        return dobraAbdominal;
    }

    public void setDobraAbdominal(double dobraAbdominal) {
        this.dobraAbdominal = dobraAbdominal;
    }

    public double calcularIndiceGorduraCorporal() {
        double somaDobras = 0.0;
        somaDobras = getDobratriceps() + getDobraSubescapular() + getDobraSuprailiaca() + getDobraAbdominal();

        double indiceDeGordura = (somaDobras * 0.153) + 10;
        setIndiceDeGorduraCorporal(indiceDeGordura);
        return indiceDeGordura;
    }
}
