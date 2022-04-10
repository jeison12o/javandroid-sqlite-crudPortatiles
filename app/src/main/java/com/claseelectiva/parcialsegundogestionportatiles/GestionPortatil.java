package com.claseelectiva.parcialsegundogestionportatiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.claseelectiva.parcialsegundogestionportatiles.controller.CtlMarca;
import com.claseelectiva.parcialsegundogestionportatiles.controller.CtlPortatil;
import com.claseelectiva.parcialsegundogestionportatiles.controller.CtlSistemaOperativo;
import com.claseelectiva.parcialsegundogestionportatiles.model.Marca;
import com.claseelectiva.parcialsegundogestionportatiles.model.Portatil;
import com.claseelectiva.parcialsegundogestionportatiles.model.SistemaOperativo;

import java.util.ArrayList;
import java.util.List;

public class GestionPortatil extends AppCompatActivity {

    private Spinner spnMarca, spnSistemaOperativo;
    private EditText txtCodigo, txtPulgadas, txtPeso, txtvalor;
    private CtlSistemaOperativo controladorSistemaOperativo;
    private CtlMarca controladorMarca;
    private CtlPortatil controladorPortatil;
    private List<String> listMarca= new ArrayList<>();
    private List<String> listSistemaOperativo= new ArrayList<>();
    private boolean estado= false;
    private Button btnGuardar, btnModificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_portatil);
        spnMarca = findViewById(R.id.spnMarca);
        spnSistemaOperativo = findViewById(R.id.spnSistemaOperativo);
        txtCodigo = findViewById(R.id.txtCodigoPortatil);
        txtPulgadas = findViewById(R.id.txtPulgadasPortatil);
        txtPeso = findViewById(R.id.txtPesoPortatil);
        txtvalor = findViewById(R.id.txtValorPortatil);
        btnGuardar = findViewById(R.id.btnGuardarPortatil);
        btnModificar = findViewById(R.id.btnModifcarPortatil);
        controladorSistemaOperativo = new CtlSistemaOperativo(this);
        controladorMarca = new CtlMarca(this);
        controladorPortatil = new CtlPortatil(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargarOpcionesMarca();
        cargarOpcionesSistemaOperativo();;
        txtCodigo.setEnabled(true);
    }

    public void vistaListarPortatiles(View view) {
        startActivity(new Intent(this, ListarPortatil.class));
    }

    private void cargarOpcionesMarca() {
        listMarca= new ArrayList<>();
        listMarca.add("Seleccionar Marca");
        for (Marca marca: controladorMarca.listar()) {
            listMarca.add(marca.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listMarca);
        spnMarca.setAdapter(adapter);
    }

    private void cargarOpcionesSistemaOperativo() {
        listSistemaOperativo= new ArrayList<>();
        listSistemaOperativo.add("Seleccionar sistema operativo");
        for (SistemaOperativo sis: controladorSistemaOperativo.listar()) {
            listSistemaOperativo.add(sis.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSistemaOperativo);
        spnSistemaOperativo.setAdapter(adapter);
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtvalor.setText("");
        txtPeso.setText("");
        txtPulgadas.setText("");
        spnMarca.setSelection(0);
        spnSistemaOperativo.setSelection(0);
    }

    private void imprimirMensaje (String mensaje){
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
    }

    public void guardar(View view) {
        if (estado == true){
            imprimirMensaje("usted se encuentra modificando");
        }else {
            String  codigo = txtCodigo.getText().toString();
            String pulgadas = txtPulgadas.getText().toString();
            String peso = txtPeso.getText().toString();
            String valor = txtvalor.getText().toString();
            String marca = spnMarca.getSelectedItem().toString();
            String operativo = spnSistemaOperativo.getSelectedItem().toString();

            if (codigo.isEmpty() || pulgadas.isEmpty() ||  peso.isEmpty() || valor.isEmpty() || marca.equals("Seleccionar Marca") || operativo.equals("Seleccionar sistema operativo")) {
                imprimirMensaje("debe ingresar todos los datos para poder guardar");
            }else {
                Portatil portatil = new Portatil();
                portatil.setCodigo(codigo);
                portatil.setPulgadas(Integer.parseInt(pulgadas));
                portatil.setPeso(Double.parseDouble(peso));
                portatil.setValor(Double.parseDouble(valor));
                portatil.setNombreMarca(marca);
                portatil.setNombreSistemaOperativo(operativo);

                try{
                    if (controladorPortatil.registrar(portatil)){
                        imprimirMensaje("se guardo con exito el portatil");
                        limpiar();
                    }else {
                        imprimirMensaje("ya existe un portatil con ese codigo, debe cambiarlo");
                        txtCodigo.setText("");
                    }

                }catch (Exception e) {
                    imprimirMensaje(e.getMessage());
                }
            }
        }
    }

    public void buscar(View view) {
        String  codigo = txtCodigo.getText().toString();
        Portatil portatil;
        try {
            portatil = controladorPortatil.buscar(codigo);
            txtCodigo.setText(portatil.getCodigo());
            txtvalor.setText(portatil.getValor()+"");
            txtPeso.setText(portatil.getPeso()+"");
            txtPulgadas.setText(portatil.getPulgadas()+"");
            spnSistemaOperativo.setSelection(0);
            for (int i = 0; i< listMarca.size(); i++) {
                if (listMarca.get(i).equals(portatil.getNombreMarca())) {
                    spnMarca.setSelection(i);
                }
            }
            for (int i = 0; i< listSistemaOperativo.size(); i++) {
                if (listSistemaOperativo.get(i).equals(portatil.getNombreSistemaOperativo())) {
                    spnSistemaOperativo.setSelection(i);
                }
            }
            estado = true;
            txtCodigo.setEnabled(false);
        }catch (Exception e) {
            imprimirMensaje(e.getMessage());
            limpiar();
        }
    }

    public void eliminar(View view){
        String  codigo = txtCodigo.getText().toString();
        try {
            controladorPortatil.eliminar(codigo);
        } catch (Exception e) {
            imprimirMensaje(e.getMessage());
            limpiar();
            txtCodigo.setEnabled(true);
        }
    }

    public void modificar(View view) {
        if (estado == false) {
            imprimirMensaje("debe buscar primero el portatil para poder modificar");
        }else {
            String  codigo = txtCodigo.getText().toString();
            String pulgadas = txtPulgadas.getText().toString();
            String peso = txtPeso.getText().toString();
            String valor = txtvalor.getText().toString();
            String marca = spnMarca.getSelectedItem().toString();
            String operativo = spnSistemaOperativo.getSelectedItem().toString();

            if (codigo.isEmpty() || pulgadas.isEmpty() ||  peso.isEmpty() || valor.isEmpty() || marca.equals("Seleccionar Marca") || operativo.equals("Seleccionar sistema operativo")) {
                imprimirMensaje("debe ingresar todos los datos para poder guardar");
            }else {
                Portatil portatil = new Portatil();
                portatil.setCodigo(codigo);
                portatil.setPulgadas(Integer.parseInt(pulgadas));
                portatil.setPeso(Double.parseDouble(peso));
                portatil.setValor(Double.parseDouble(valor));
                portatil.setNombreMarca(marca);
                portatil.setNombreSistemaOperativo(operativo);

                try{
                    controladorPortatil.actualizar(portatil);
                }catch (Exception e) {
                    imprimirMensaje(e.getMessage());
                    limpiar();
                    estado = false;
                    txtCodigo.setEnabled(true);
                }
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        limpiar();
        estado = false;
    }
}
