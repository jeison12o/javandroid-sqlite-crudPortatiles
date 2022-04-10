package com.claseelectiva.parcialsegundogestionportatiles.model;

public class Portatil {
    private String nombreMarca;
    private String nombreSistemaOperativo;
    private String codigo;
    private int pulgadas;
    private double peso;
    private double valor;

    public Portatil() {
    }

    public Portatil(String nombreMarca, String nombreSistemaOperativo, String codigo, int pulgadas, double peso, double valor) {
        this.nombreMarca = nombreMarca;
        this.nombreSistemaOperativo = nombreSistemaOperativo;
        this.codigo = codigo;
        this.pulgadas = pulgadas;
        this.peso = peso;
        this.valor = valor;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getNombreSistemaOperativo() {
        return nombreSistemaOperativo;
    }

    public void setNombreSistemaOperativo(String nombreSistemaOperativo) {
        this.nombreSistemaOperativo = nombreSistemaOperativo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return  "nombreMarca=" + nombreMarca +", nombreSistemaOperativo=" + nombreSistemaOperativo+", codigo=" + codigo +", pulgadas=" + pulgadas +
                ", peso=" + peso +", valor=" + valor;
    }
}
