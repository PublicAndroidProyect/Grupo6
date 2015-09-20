package com.cibertec.app.ferconsapedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cibertec.app.ferconsapedidos.Adaptador.AdaptadorCliente;
import com.cibertec.app.ferconsapedidos.Dao.ClienteDAO;
import com.cibertec.app.ferconsapedidos.Entidad.Cliente;

import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity {

    private AdaptadorCliente adaptadorCliente;
    private ArrayList<Cliente> arrayCliente;
    private RecyclerView recViewCliente;
    private EditText etBuscaCliente;
    public final static String ARG_CLIENTE = "ARG_CLIENTE", ARG_POSITION = "ARG_POSITION";
    public final static String ARG_IDCLIENTE = "ARG_IDCLIENTE";
    private final static int REQUEST_CODE_CLICK = 3;
    private final static int REQUEST_CODE = 1;
    private ClienteDAO mClienteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        recViewCliente = (RecyclerView) findViewById(R.id.rvClientes);
        recViewCliente.setHasFixedSize(true);
        arrayCliente = new ClienteDAO().listCliente();
        adaptadorCliente = new AdaptadorCliente(arrayCliente);
        recViewCliente.setAdapter(adaptadorCliente);
        mClienteDAO = new ClienteDAO();
        recViewCliente.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        etBuscaCliente = (EditText) findViewById(R.id.etBuscarCliente);
        etBuscaCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptadorCliente.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        adaptadorCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recViewCliente.getChildAdapterPosition(v);
                if (MenuPrincipalActivity.ARG_OPCION==MenuPrincipalActivity.ARG_OPCION_NUEVOPEDIDO){
                    Cliente cliente = arrayCliente.get(itemPosition);
                    Intent intent = new Intent(getBaseContext(), PedidoActivity.class);
                    intent.putExtra(ARG_CLIENTE, cliente);
                    intent.putExtra(ARG_POSITION, itemPosition);
                    startActivity(intent);
                }
                if (MenuPrincipalActivity.ARG_OPCION==MenuPrincipalActivity.ARG_OPCION_CLIENTES){
                    Cliente cliente = arrayCliente.get(itemPosition);
                    Intent intent = new Intent(getBaseContext(), Cliente_Editar.class);
                    intent.putExtra(ARG_CLIENTE, cliente);
                    intent.putExtra(ARG_POSITION, itemPosition);
                    intent.putExtra(ARG_IDCLIENTE, String.valueOf(cliente.getIdCliente()));
                    startActivityForResult(intent, REQUEST_CODE_CLICK);
                }
            }
        });

        adaptadorCliente.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                //int itemPosition = recViewCliente.getChildAdapterPosition(v);
                //Cliente cliente = arrayCliente.get(itemPosition);
                //Intent intent = new Intent(getBaseContext(), Cliente_Editar.class);
                //intent.putExtra(ARG_CLIENTE, cliente);
                //intent.putExtra(ARG_POSITION, itemPosition);
                //intent.putExtra(ARG_IDCLIENTE, String.valueOf(cliente.getIdCliente()));
                //startActivityForResult(intent, REQUEST_CODE_CLICK);

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clientes, menu);
        if (MenuPrincipalActivity.ARG_OPCION==MenuPrincipalActivity.ARG_OPCION_NUEVOPEDIDO){
            menu.findItem(R.id.action_add).setVisible(false);
            menu.findItem(R.id.action_settings).setVisible(false);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getBaseContext(), ClienteDetalle.class);

            startActivity(intent);


        }

        if (id == R.id.action_add) {
            Intent intent = new Intent(getBaseContext(), Cliente_Editar.class);

            startActivityForResult(intent, REQUEST_CODE);


        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            Cliente cliente = data.getParcelableExtra(ARG_CLIENTE);

            int idcli = mClienteDAO.addCliente(cliente);
            cliente.setIdCliente(idcli);

            adaptadorCliente.add(cliente);
            adaptadorCliente.notifyDataSetChanged();

        }
        if (requestCode == REQUEST_CODE_CLICK && resultCode == 44) {

            int position = data.getIntExtra(ARG_POSITION, -1);

            Cliente cliente = data.getParcelableExtra(ARG_CLIENTE);
            if (position != -1) {
                adaptadorCliente.remove(position);
                mClienteDAO.deleteCliente(cliente);
            }

             adaptadorCliente.notifyDataSetChanged();

        } else if (requestCode == REQUEST_CODE_CLICK && resultCode == RESULT_OK) {
            int position = data.getIntExtra(ARG_POSITION, -1);
            Cliente cliente = data.getParcelableExtra(ARG_CLIENTE);

            if (position != -1) {
                adaptadorCliente.update(cliente, position);
                mClienteDAO.updateCliente(cliente);
            }
        }


    }


}
