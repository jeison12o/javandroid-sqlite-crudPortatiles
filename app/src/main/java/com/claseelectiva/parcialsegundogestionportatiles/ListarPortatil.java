package com.claseelectiva.parcialsegundogestionportatiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.claseelectiva.parcialsegundogestionportatiles.controller.CtlPortatil;
import com.claseelectiva.parcialsegundogestionportatiles.model.Portatil;

public class ListarPortatil extends AppCompatActivity {

    private ListView list;
    private CtlPortatil controladorPortatil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_portatil);
        iniciar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        iniciar();
    }

    private void iniciar(){
        controladorPortatil = new CtlPortatil(this);
        list = findViewById(R.id.listPortatiles);
        configurarLista();
    }

    public void configurarLista() {
        ArrayAdapter<Portatil> adapter = new ArrayAdapter<Portatil>(this, android.R.layout.simple_list_item_1, controladorPortatil.listar());
        list.setAdapter(adapter);
    }
}
