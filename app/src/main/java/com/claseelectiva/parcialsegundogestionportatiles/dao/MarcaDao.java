package com.claseelectiva.parcialsegundogestionportatiles.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.claseelectiva.parcialsegundogestionportatiles.conexiones.Conexion;
import com.claseelectiva.parcialsegundogestionportatiles.model.Marca;
import com.claseelectiva.parcialsegundogestionportatiles.utilidades.UtilidadesMarca;

import java.util.ArrayList;
import java.util.List;

public class MarcaDao {

    Conexion conex;

    public MarcaDao(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Marca marca) {
        ContentValues registro = new ContentValues();
        registro.put(UtilidadesMarca.campoNombre, marca.getNombre());
        registro.put(UtilidadesMarca.campoDescripcion, marca.getDescripcion());
        return conex.ejecutarInsert(UtilidadesMarca.tabla, registro);
    }

    public Marca buscar(String nombre) {
        Marca marca= null;
        String consulta = "select "+UtilidadesMarca.campoNombre+", "+UtilidadesMarca.campoDescripcion+" from "+UtilidadesMarca.tabla+" where "+UtilidadesMarca.campoNombre+"='" + nombre+ "'";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            marca = new Marca(temp.getString(0),temp.getString(1));
        }
        conex.cerrarConexion();
        return marca;
    }

    public boolean eliminar(Marca marca) {
        String condicion = UtilidadesMarca.campoNombre+"='" + marca.getNombre()+"'";
        return conex.ejecutarDelete(UtilidadesMarca.tabla, condicion);
    }

    public boolean modificar(Marca marca) {
        String condicion = UtilidadesMarca.campoNombre+"='" + marca.getNombre()+"'";
        ContentValues registro = new ContentValues();
        registro.put(UtilidadesMarca.campoDescripcion, marca.getDescripcion());
        return conex.ejecutarUpdate(UtilidadesMarca.tabla, condicion, registro);
    }

    public List<Marca> listar() {
        List<Marca> listaMarca = new ArrayList();
        String consulta = "select "+UtilidadesMarca.campoNombre+", "+UtilidadesMarca.campoDescripcion+" from "+UtilidadesMarca.tabla+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Marca marca= new Marca(temp.getString(0),temp.getString(1));
                listaMarca.add(marca);
            } while (temp.moveToNext());
        }
        return listaMarca;
    }

}
