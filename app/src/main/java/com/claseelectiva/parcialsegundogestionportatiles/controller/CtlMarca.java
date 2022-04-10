package com.claseelectiva.parcialsegundogestionportatiles.controller;

import android.app.Activity;

import com.claseelectiva.parcialsegundogestionportatiles.dao.MarcaDao;
import com.claseelectiva.parcialsegundogestionportatiles.model.Marca;

import java.util.List;

public class CtlMarca {

    MarcaDao dao;

    public CtlMarca(Activity activity) {
        dao = new MarcaDao(activity);
    }

    public boolean registrar(Marca marca) throws Exception {
        if (marca == null) {
            throw new Exception("debe llenar los datos correctamente para poder registrar");
        }else {
            Marca marcaExiste = null;
            try {
                marcaExiste = dao.buscar(marca.getNombre());
            }catch (Exception e) {
            }
            if (marcaExiste == null){
                dao.guardar(marca);
                return true;
            }else {
                return false;
            }
        }
    }

    public void eliminar(String nombre) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("debe ingresar el nombre de la marca para poder eliminar");
        }else{
            Marca marca = new Marca();
            marca.setNombre(nombre);
            if (dao.eliminar(marca)) {
                throw new Exception("se elimino con exito la marca");
            }else{
                throw new Exception("la marca que usted desea eliminar no existe");
            }
        }
    }

    public void actualizar(Marca marca) throws Exception {
        if (marca == null) {
            throw new Exception("debe llenar los datos correctamente para poder actualizar");
        }else {
            if (dao.modificar(marca)) {
                throw new Exception("se actualizo con exito la marca");
            }
        }
    }

    public Marca buscar(String nombre) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("debe ingresar el nombre de la marca para buscar");
        }else{
            Marca marca = null;
            try {
                marca= dao.buscar(nombre);
            }catch (Exception e) {
            }
            if (marca== null){
                throw new Exception("la marca que busca no existe");
            }else {
                return marca;
            }
        }
    }

    public List<Marca> listar() {
        return dao.listar();
    }

}
