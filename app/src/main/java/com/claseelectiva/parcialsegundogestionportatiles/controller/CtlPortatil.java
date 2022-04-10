package com.claseelectiva.parcialsegundogestionportatiles.controller;

import android.app.Activity;

import com.claseelectiva.parcialsegundogestionportatiles.dao.PortatilDao;
import com.claseelectiva.parcialsegundogestionportatiles.model.Portatil;

import java.util.List;

public class CtlPortatil {

    PortatilDao dao;

    public CtlPortatil(Activity activity) {
        dao = new PortatilDao(activity);
    }

    public List<Portatil> listar() {
        return dao.listar();
    }

    public double valorTotal(){
        return dao.valorTotalPortatil();
    }

    public boolean registrar(Portatil portatil) throws Exception {
        if (portatil == null) {
            throw new Exception("debe llenar los datos correctamente para poder registrar");
        }else {
            Portatil existe = null;
            try {
                existe = dao.buscar(portatil.getCodigo());
            }catch (Exception e) {
            }
            if (existe == null){
                dao.guardar(portatil);
                return true;
            }else {
                return false;
            }
        }
    }

    public void eliminar(String codigo) throws Exception {
        if (codigo.isEmpty()) {
            throw new Exception("debe ingresar el codigo del portatil para poder eliminar");
        }else{
            if (dao.eliminar(codigo)) {
                throw new Exception("se elimino con exito el portatil");
            }else{
                throw new Exception("el portatil que usted desea eliminar no existe");
            }
        }
    }

    public void actualizar(Portatil portatil) throws Exception {
        if (portatil == null) {
            throw new Exception("debe llenar los datos correctamente para poder actualizar");
        }else {
            if (dao.modificar(portatil)) {
                throw new Exception("se actualizo con exito el portatil");
            }
        }
    }

    public Portatil buscar(String codigo) throws Exception {
        if (codigo.isEmpty()) {
            throw new Exception("debe ingresar el codigo del portatil para buscar");
        }else{
            Portatil portatil= null;
            try {
                portatil = dao.buscar(codigo);
            }catch (Exception e) {
            }
            if (portatil== null){
                throw new Exception("el portatil que busca no existe");
            }else {
                return portatil;
            }
        }
    }
}
