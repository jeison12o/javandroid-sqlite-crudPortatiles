package com.claseelectiva.parcialsegundogestionportatiles.model;

public class SistemaOperativo {
    private String nombre;
    private String descripcion;

    public SistemaOperativo() {
    }

    public SistemaOperativo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
