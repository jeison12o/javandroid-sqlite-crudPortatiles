package com.claseelectiva.parcialsegundogestionportatiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.claseelectiva.parcialsegundogestionportatiles.controller.CtlSistemaOperativo;
import com.claseelectiva.parcialsegundogestionportatiles.model.SistemaOperativo;

public class RegistrarSistemaOperativo extends AppCompatActivity {

    private EditText txtNombreSistemaOperativo, txtDescripcionSistemaOprativo;
    private CtlSistemaOperativo controladorSistemaOperativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_sistema_operativo);
        txtNombreSistemaOperativo = findViewById(R.id.txtNombreSistemaOperativo);
        txtDescripcionSistemaOprativo = findViewById(R.id.txtDescripcionSistemaOperativo);
        controladorSistemaOperativo = new CtlSistemaOperativo(this);
    }

    private void imprimirMensaje (String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
    }

    private void limpiar() {
        txtNombreSistemaOperativo.setText("");
        txtDescripcionSistemaOprativo.setText("");
    }

    public void GuardarSistemaOperativo(View view) {
        String nombre = txtNombreSistemaOperativo.getText().toString();
        String descripcion = txtDescripcionSistemaOprativo.getText().toString();

        if(nombre.isEmpty() || descripcion.isEmpty()) {
            imprimirMensaje("debe ingresar los datos correctamente para guardar");
        }else{
            SistemaOperativo sistemaOperativo = new SistemaOperativo();
            sistemaOperativo.setNombre(nombre);
            sistemaOperativo.setDescripcion(descripcion);
            try {
                if (controladorSistemaOperativo.registrar(sistemaOperativo)) {
                    imprimirMensaje("se registro con exito el sistema operativo");
                    limpiar();
                }else{
                    imprimirMensaje("ya existe un sistema operativo con este nombre debe cambiarlo");
                    txtNombreSistemaOperativo.setText("");
                }
            } catch (Exception e) {
                imprimirMensaje(e.getMessage());
                limpiar();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        limpiar();
    }
}
