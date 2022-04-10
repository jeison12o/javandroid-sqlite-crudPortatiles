package com.claseelectiva.parcialsegundogestionportatiles.utilidades;

public class UtilidadesPortatil {
    public static final String tabla = "portatil";
    public static final String campoNombreMarca = "nombre_marca";
    public static final String campoNombreSistemaOperativo = "nombre_sistema_operativo";
    public static final String campoCodigo = "codigo";
    public static final String campoPulgadas = "pulgadas";
    public static final String campoPeso = "peso";
    public static final String campoValor = "valor";
    public static final String crearTabla = "create table "+tabla+"("+campoCodigo+" text primary key,"+campoPulgadas+" integer, " +
                                            campoPeso+" NUMERIC , "+campoValor+" NUMERIC , "+campoNombreMarca+" text, "+campoNombreSistemaOperativo+" text," +
                                            " FOREIGN KEY("+campoNombreMarca+") REFERENCES "+UtilidadesMarca.tabla+"("+UtilidadesMarca.campoNombre+") ON DELETE CASCADE," +
                                            "FOREIGN KEY("+campoNombreSistemaOperativo+") REFERENCES "+UtilidadesSistemaOperativo.tabla+"("+UtilidadesSistemaOperativo.campoNombre+") ON DELETE CASCADE);";
}
