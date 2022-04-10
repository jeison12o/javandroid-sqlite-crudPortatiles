package com.claseelectiva.parcialsegundogestionportatiles.utilidades;

public class UtilidadesSistemaOperativo {
    public static final String tabla = "sistema_operativo";
    public static final String campoNombre = "nombre";
    public static final String campoDescripcion = "descripcion";
    public static final String crearTabla = "create table "+tabla+"( "+campoNombre+"  text primary key,"+campoDescripcion+" text );";
}
