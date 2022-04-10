package com.claseelectiva.parcialsegundogestionportatiles.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.claseelectiva.parcialsegundogestionportatiles.conexiones.Conexion;
import com.claseelectiva.parcialsegundogestionportatiles.model.Portatil;
import com.claseelectiva.parcialsegundogestionportatiles.utilidades.UtilidadesPortatil;

import java.util.ArrayList;
import java.util.List;

public class PortatilDao {

    private Conexion conex;

    public PortatilDao(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Portatil portatil) {
        ContentValues registro = new ContentValues();
        registro.put(UtilidadesPortatil.campoCodigo, portatil.getCodigo());
        registro.put(UtilidadesPortatil.campoPulgadas, portatil.getPulgadas());
        registro.put(UtilidadesPortatil.campoPeso, portatil.getPeso());
        registro.put(UtilidadesPortatil.campoValor, portatil.getValor());
        registro.put(UtilidadesPortatil.campoNombreMarca, portatil.getNombreMarca());
        registro.put(UtilidadesPortatil.campoNombreSistemaOperativo, portatil.getNombreSistemaOperativo());
        return conex.ejecutarInsert(UtilidadesPortatil.tabla, registro);
    }

    public Portatil buscar(String codigo) {
        String consulta = "select "+UtilidadesPortatil.campoNombreMarca+", "+UtilidadesPortatil.campoNombreSistemaOperativo+", "+UtilidadesPortatil.campoCodigo+
                            ", "+UtilidadesPortatil.campoPulgadas+", "+UtilidadesPortatil.campoPeso+", "+UtilidadesPortatil.campoValor+"  from "+UtilidadesPortatil.tabla+" where "+UtilidadesPortatil.campoCodigo+"='" + codigo+"'";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            return new Portatil(temp.getString(0),temp.getString(1), codigo, Integer.parseInt(temp.getString(3)), Double.parseDouble(temp.getString(4)), Double.parseDouble(temp.getString(5)));
        }
        conex.cerrarConexion();
        return null;
    }

    public boolean eliminar(String codigo) {
        String condicion = UtilidadesPortatil.campoCodigo+"='" + codigo+"'";
        return conex.ejecutarDelete(UtilidadesPortatil.tabla, condicion);
    }

    public boolean modificar(Portatil portatil) {
        String condicion = UtilidadesPortatil.campoCodigo+"='" + portatil.getCodigo()+"'";
        ContentValues registro = new ContentValues();
        registro.put(UtilidadesPortatil.campoPulgadas, portatil.getPulgadas());
        registro.put(UtilidadesPortatil.campoPeso, portatil.getPeso());
        registro.put(UtilidadesPortatil.campoValor, portatil.getValor());
        registro.put(UtilidadesPortatil.campoNombreMarca, portatil.getNombreMarca());
        registro.put(UtilidadesPortatil.campoNombreSistemaOperativo, portatil.getNombreSistemaOperativo());
        return conex.ejecutarUpdate(UtilidadesPortatil.tabla, condicion, registro);
    }

    public List<Portatil> listar() {
        List<Portatil> lista = new ArrayList();
        String consulta = "select "+UtilidadesPortatil.campoNombreMarca+", "+UtilidadesPortatil.campoNombreSistemaOperativo+", "+UtilidadesPortatil.campoCodigo+
                ", "+UtilidadesPortatil.campoPulgadas+", "+UtilidadesPortatil.campoPeso+", "+UtilidadesPortatil.campoValor+"  from "+UtilidadesPortatil.tabla;
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Portatil portatil = new Portatil(temp.getString(0),temp.getString(1), temp.getString(2), Integer.parseInt(temp.getString(3)), Double.parseDouble(temp.getString(4)), Double.parseDouble(temp.getString(5)));
                lista.add(portatil);
            } while (temp.moveToNext());
        }
        return lista;
    }

    public double valorTotalPortatil() {
        double valor=0;
        SQLiteDatabase db = conex.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery("SELECT sum("+ UtilidadesPortatil.campoValor+") FROM "+UtilidadesPortatil.tabla, null);
            cursor.moveToFirst();
            valor = Double.parseDouble(cursor.getString(0));
            cursor.close();
        }catch (Exception e) {
            System.out.println("error en la consulta especial "+e);
        }
        return valor;
    }
}
