package com.claseelectiva.parcialsegundogestionportatiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.claseelectiva.parcialsegundogestionportatiles.controller.CtlPortatil;

public class MainActivity extends AppCompatActivity {

    private TextView lblValorProducto;
    private CtlPortatil controladorPortatil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        iniciar();
    }

    private void iniciar(){
        lblValorProducto = findViewById(R.id.lblTotalProductos);
        controladorPortatil = new CtlPortatil(this);
        lblValorProducto.setText("Total valor portatiles: $ "+controladorPortatil.valorTotal());
    }

    public void vistaRegistrarMarca(View view) {
        startActivity(new Intent(this, RegistrarMarca.class));
    }

    public void vistaRegistrarSistemaOperativo(View view) {
        startActivity(new Intent(this, RegistrarSistemaOperativo.class));
    }

    public void vistaGestionPortatil(View view) {
        startActivity(new Intent(this, GestionPortatil.class));
    }
}
