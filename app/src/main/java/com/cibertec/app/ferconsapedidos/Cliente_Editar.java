package com.cibertec.app.ferconsapedidos;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cibertec.app.ferconsapedidos.Entidad.Cliente;

public class Cliente_Editar extends AppCompatActivity {

    private TextInputLayout tilcliente, tilRUC, tilDireccion, tilTelf;
    private EditText etcliente, etRUC, etDireccion, etTelf;
    private Button btacciones, btguardar;

    private int position = -1;
    private int idcliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente__editar);


        tilcliente = (TextInputLayout) findViewById(R.id.tilcliente);
        tilRUC = (TextInputLayout) findViewById(R.id.tilRUC);
        tilDireccion = (TextInputLayout) findViewById(R.id.tilDireccion);
        tilTelf = (TextInputLayout) findViewById(R.id.tilTelf);

        etcliente = (EditText) findViewById(R.id.etcliente);
        etRUC = (EditText) findViewById(R.id.etRUC);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etTelf = (EditText) findViewById(R.id.etTelf);

        btacciones = (Button) findViewById(R.id.btacciones);
        btguardar = (Button) findViewById(R.id.btguardar);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ClientesActivity.ARG_CLIENTE)) {
            Cliente cliente = getIntent().getParcelableExtra(ClientesActivity.ARG_CLIENTE);
            etcliente.setText(cliente.getNombreCliente());
            etRUC.setText(cliente.getRUC());
            etDireccion.setText(String.valueOf(cliente.getDireccion()));
            etTelf.setText(String.valueOf(cliente.getTelefono()));
            idcliente = Integer.valueOf(cliente.getIdCliente());
            position = getIntent().getIntExtra(ClientesActivity.ARG_POSITION, -1);
            btacciones.setText("Eliminar");
            btguardar.setText("Modificar");

        } else {

            btacciones.setVisibility(View.GONE);
        }


         btacciones.setOnClickListener(btaccionesOnClickListener);
        btguardar.setOnClickListener(btguardarOnClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cliente__editar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener btaccionesOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent();

            Cliente cliente = new Cliente();
            cliente.setIdCliente(idcliente);
            intent.putExtra(ClientesActivity.ARG_CLIENTE, cliente);
            intent.putExtra(ClientesActivity.ARG_POSITION, position);

            setResult(44, intent);
            finish();

        }
    };

    View.OnClickListener btguardarOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            boolean isComplete = true;

            tilcliente.setErrorEnabled(false);
            tilRUC.setErrorEnabled(false);
            tilDireccion.setErrorEnabled(false);
            tilTelf.setErrorEnabled(false);

            if (etcliente.getText().toString().trim().length() <= 0) {
                tilcliente.setErrorEnabled(true);
                tilcliente.setError("Ingrese su nombre");
                isComplete = false;
            }

            if (etRUC.getText().toString().trim().length() != 8) {
                tilRUC.setErrorEnabled(true);
                tilRUC.setError("Ingrese un ruc valido");
                isComplete = false;
            }

            if (etDireccion.getText().toString().trim().length() <= 0) {
                tilDireccion.setErrorEnabled(true);
                tilDireccion.setError("Ingrese una dirección");
                isComplete = false;
            }

            if (etTelf.getText().toString().trim().length() <= 0) {
                tilTelf.setErrorEnabled(true);
                tilTelf.setError("Ingrese un telefono");
                isComplete = false;
            }

            if (isComplete) {

                Cliente cliente = new Cliente();
                cliente.setIdCliente(idcliente);
                cliente.setNombreCliente(etcliente.getText().toString().trim());
                cliente.setRUC(etRUC.getText().toString().trim());
                cliente.setDireccion(etDireccion.getText().toString().trim());
                cliente.setTelefono(etTelf.getText().toString().trim());

                Intent intent = new Intent();
                intent.putExtra(ClientesActivity.ARG_CLIENTE, cliente);
                intent.putExtra(ClientesActivity.ARG_POSITION, position);
                setResult(RESULT_OK, intent);
                finish();

            }
        }
    };


}
