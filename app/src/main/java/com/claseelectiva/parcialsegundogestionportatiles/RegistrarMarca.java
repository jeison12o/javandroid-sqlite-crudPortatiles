package com.claseelectiva.parcialsegundogestionportatiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.claseelectiva.parcialsegundogestionportatiles.controller.CtlMarca;
import com.claseelectiva.parcialsegundogestionportatiles.model.Marca;

public class RegistrarMarca extends AppCompatActivity {

    private EditText txtNombreMarca, txtDescripcionMarca;
    private CtlMarca controladorMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_marca);
        txtNombreMarca = findViewById(R.id.txtNombreMarca);
        txtDescripcionMarca = findViewById(R.id.txtDescripcionMarca);
        controladorMarca = new CtlMarca(this);
    }

    private void imprimirMensaje (String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
    }

    private void limpiar() {
        txtNombreMarca.setText("");
        txtDescripcionMarca.setText("");
    }

    public void GuardarMarca(View view) {
        String nombreMarca = txtNombreMarca.getText().toString();
        String descripcion = txtDescripcionMarca.getText().toString();

        if(nombreMarca.isEmpty() || descripcion.isEmpty()) {
            imprimirMensaje("debe ingresar los datos correctamente para guardar");
        }else{
            Marca marca = new Marca();
            marca.setNombre(nombreMarca);
            marca.setDescripcion(descripcion);
            try {
                if (controladorMarca.registrar(marca)) {
                    imprimirMensaje("se registro con exito la marca");
                    limpiar();
                }else{
                    imprimirMensaje("ya existe una marca con este nombre debe cambiarlo");
                    txtNombreMarca.setText("");
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
