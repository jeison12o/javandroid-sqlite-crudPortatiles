package com.claseelectiva.parcialsegundogestionportatiles.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.claseelectiva.parcialsegundogestionportatiles.conexiones.Conexion;
import com.claseelectiva.parcialsegundogestionportatiles.model.SistemaOperativo;
import com.claseelectiva.parcialsegundogestionportatiles.utilidades.UtilidadesSistemaOperativo;

import java.util.ArrayList;
import java.util.List;

public class SistemaOperativoDao {

    Conexion conex;

    public SistemaOperativoDao(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(SistemaOperativo sistema) {
        ContentValues registro = new ContentValues();
        registro.put(UtilidadesSistemaOperativo.campoNombre, sistema.getNombre());
        registro.put(UtilidadesSistemaOperativo.campoDescripcion, sistema.getDescripcion());
        return conex.ejecutarInsert(UtilidadesSistemaOperativo.tabla, registro);
    }

    public SistemaOperativo buscar(String nombre) {
        SistemaOperativo sistema= null;
        String consulta = "select "+UtilidadesSistemaOperativo.campoNombre+", "+UtilidadesSistemaOperativo.campoDescripcion+" from "+UtilidadesSistemaOperativo.tabla+" where "+UtilidadesSistemaOperativo.campoNombre+"='" + nombre+ "'";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            sistema = new SistemaOperativo(temp.getString(0),temp.getString(1));
        }
        conex.cerrarConexion();
        return sistema;
    }

    public boolean eliminar(SistemaOperativo sistema) {
        String condicion = UtilidadesSistemaOperativo.campoNombre+"='" + sistema.getNombre()+"'";
        return conex.ejecutarDelete(UtilidadesSistemaOperativo.tabla, condicion);
    }

    public boolean modificar(SistemaOperativo sistema) {
        String condicion = UtilidadesSistemaOperativo.campoNombre+"='" + sistema.getNombre()+"'";
        ContentValues registro = new ContentValues();
        registro.put(UtilidadesSistemaOperativo.campoDescripcion, sistema.getDescripcion());
        return conex.ejecutarUpdate(UtilidadesSistemaOperativo.tabla, condicion, registro);
    }

    public List<SistemaOperativo> listar() {
        List<SistemaOperativo> lista = new ArrayList();
        String consulta = "select "+UtilidadesSistemaOperativo.campoNombre+", "+UtilidadesSistemaOperativo.campoDescripcion+" from "+UtilidadesSistemaOperativo.tabla+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                SistemaOperativo siste= new SistemaOperativo(temp.getString(0),temp.getString(1));
                lista.add(siste);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
