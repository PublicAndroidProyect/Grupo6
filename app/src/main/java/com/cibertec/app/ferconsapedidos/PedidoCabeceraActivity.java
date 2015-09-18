package com.cibertec.app.ferconsapedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.app.ferconsapedidos.Adaptador.AdaptadorCabeceraPedido;
import com.cibertec.app.ferconsapedidos.Dao.PedidoCabeceraDAO;
import com.cibertec.app.ferconsapedidos.Entidad.PedidoCabecera;
import com.cibertec.app.ferconsapedidos.Entidad.PedidoDetalle;

import java.util.ArrayList;

public class PedidoCabeceraActivity extends AppCompatActivity {

    ListView ListPedidoCabecera;
    AdaptadorCabeceraPedido adpatadorPedidoCabecera;
    ArrayList<PedidoCabecera>  arrayPedidoCabecera;

    EditText etBuscaPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_cabecera);

        ListPedidoCabecera = (ListView) findViewById(R.id.lvPedidoCabecera);
        arrayPedidoCabecera=new PedidoCabeceraDAO().listPedidoCabecera();

        adpatadorPedidoCabecera = new  AdaptadorCabeceraPedido(this, R.layout.activity_pedido_cabecera_item, arrayPedidoCabecera );
        ListPedidoCabecera.setAdapter(adpatadorPedidoCabecera);
        ListPedidoCabecera.setTextFilterEnabled(true);
        ListPedidoCabecera.setOnItemClickListener(lvPedidoCabeceraOnItemClickListener);
        etBuscaPedido = (EditText)findViewById(R.id.etBuscaPedido);
        etBuscaPedido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adpatadorPedidoCabecera.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    AdapterView.OnItemClickListener lvPedidoCabeceraOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            PedidoCabecera pedidoCabecera =  adpatadorPedidoCabecera.getItem(position);
            Intent intent = new Intent(getBaseContext(),PedidoActivity.class);
            intent.putExtra("ARG_PEDIDOCABECERA",  pedidoCabecera);
            intent.putExtra("ARG_POSITION_PEDIDOCABECERA", position);
            intent.putExtra("ARG_EDICION", "EDITAR");
            //startActivity(intent);
            startActivityForResult(intent,12);
        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK){
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
        PedidoCabecera pedidoCabecera = data.getParcelableExtra("ARG_PEDIDOCABECERA");
        int position = data.getIntExtra("ARG_POSITION_PEDIDOCABECERA", -1);
        if (resultCode != RESULT_OK){
            return;
        }

        if (position != -1) {
                PedidoCabecera old = adpatadorPedidoCabecera.getItem(position);
                old.setCondicionPago(pedidoCabecera.getCondicionPago());
                old.setFechaPedido(pedidoCabecera.getFechaPedido());
                adpatadorPedidoCabecera.notifyDataSetChanged();
        }
    }


}
