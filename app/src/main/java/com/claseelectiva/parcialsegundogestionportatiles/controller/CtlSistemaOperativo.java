package com.claseelectiva.parcialsegundogestionportatiles.controller;

import android.app.Activity;

import com.claseelectiva.parcialsegundogestionportatiles.dao.SistemaOperativoDao;
import com.claseelectiva.parcialsegundogestionportatiles.model.SistemaOperativo;

import java.util.List;

public class CtlSistemaOperativo {

    SistemaOperativoDao dao;

    public CtlSistemaOperativo(Activity activity) {
        dao = new SistemaOperativoDao(activity);
    }

    public boolean registrar(SistemaOperativo sistema) throws Exception {
        if (sistema == null) {
            throw new Exception("debe llenar los datos correctamente para poder registrar");
        }else {
            SistemaOperativo existe = null;
            try {
                existe= dao.buscar(sistema.getNombre());
            }catch (Exception e) {
            }
            if (existe == null){
                dao.guardar(sistema);
                return true;
            }else {
                return false;
            }
        }
    }

    public List<SistemaOperativo> listar() {
        return dao.listar();
    }
}
