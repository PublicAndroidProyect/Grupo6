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
import android.widget.EditText;

import com.cibertec.app.ferconsapedidos.Adaptador.AdaptadorCliente;
import com.cibertec.app.ferconsapedidos.Adaptador.AdaptadorProducto;
import com.cibertec.app.ferconsapedidos.Dao.ClienteDAO;
import com.cibertec.app.ferconsapedidos.Dao.ProductoDAO;
import com.cibertec.app.ferconsapedidos.Entidad.Cliente;
import com.cibertec.app.ferconsapedidos.Entidad.PedidoDetalle;
import com.cibertec.app.ferconsapedidos.Entidad.Producto;

import java.util.ArrayList;

public class ProductoActivity extends AppCompatActivity {

    private AdaptadorProducto adaptadorProducto;
    private ArrayList<Producto> arrayProducto;
    private RecyclerView recViewProducto;
    private EditText etBuscaProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        recViewProducto = (RecyclerView) findViewById(R.id.rvProducto);
        recViewProducto.setHasFixedSize(true);
        arrayProducto=new ProductoDAO().listProducto();
        adaptadorProducto = new AdaptadorProducto(arrayProducto);
        recViewProducto.setAdapter(adaptadorProducto);

        recViewProducto.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        etBuscaProducto = (EditText)findViewById(R.id.etBuscarProducto);
        etBuscaProducto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptadorProducto.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        adaptadorProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recViewProducto.getChildAdapterPosition(v);

                Intent intent = new Intent(getBaseContext(), PedidoDetalleNuevoItemActivity.class);
                Producto producto = arrayProducto.get(itemPosition);
                intent.putExtra("ARG_PRODUCTO", producto);
                intent.putExtra("ARG_POSITION", itemPosition);

                startActivityForResult(intent, 22);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // si es nuevo
        if (resultCode == RESULT_OK){

            PedidoDetalle pedidoDetalle = data.getParcelableExtra("ARG_PRODUCTO_PEDIDODETALLE");
            //Toast.makeText(PedidoActivity.this, "Position: " + producto.getCodigoProducto(), Toast.LENGTH_SHORT).show();

            Intent i = getIntent();
            i.putExtra("ARG_PRODUCTO2", pedidoDetalle);
            setResult(RESULT_OK, i);
            finish();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clientes, menu);
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


}
