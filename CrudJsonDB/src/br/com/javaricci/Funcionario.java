package br.com.javaricci;

public class Funcionario {
    private int idFunc;
    private String nomeFunc;
    private double salarioFunc;
    private int horasBaseFunc;
    private double salHoraFunc;
    private double salDiaFunc;

    // Getters e Setters

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }

    public double getSalarioFunc() {
        return salarioFunc;
    }

    public void setSalarioFunc(double salarioFunc) {
        this.salarioFunc = salarioFunc;
    }

    public int getHorasBaseFunc() {
        return horasBaseFunc;
    }

    public void setHorasBaseFunc(int horasBaseFunc) {
        this.horasBaseFunc = horasBaseFunc;
    }

    public double getSalHoraFunc() {
        return salHoraFunc;
    }

    public void setSalHoraFunc(double salHoraFunc) {
        this.salHoraFunc = salHoraFunc;
    }

    public double getSalDiaFunc() {
        return salDiaFunc;
    }

    public void setSalDiaFunc(double salDiaFunc) {
        this.salDiaFunc = salDiaFunc;
    }
}
