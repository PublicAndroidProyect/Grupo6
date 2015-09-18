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
import android.widget.TextView;

import com.cibertec.app.ferconsapedidos.Entidad.PedidoDetalle;
import com.cibertec.app.ferconsapedidos.Entidad.Producto;

public class PedidoDetalleNuevoItemActivity extends AppCompatActivity {

    private TextView tvCodigoProducto ;
    private TextView tvDescripcionProducto;
    private TextView tvUnidadProducto;
    private TextView tvPrecioProducto;
    private Integer IdProductoProducto;
    private EditText etCantidadProducto;
    private Button btAdicionarProducto;
    private Button btcancelar;
    private int position = -1;
    private TextInputLayout tilCantidadProductoNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_detalle_nuevo_item);

        final Producto producto = getIntent().getParcelableExtra("ARG_PRODUCTO");
        tvCodigoProducto = (TextView)findViewById(R.id.tvCodigoProductoEditar);
        tvDescripcionProducto =(TextView)findViewById(R.id.tvDescripcionProductoEditar);
        tvPrecioProducto=(TextView)findViewById(R.id.tvPrecioEditar);
        tvUnidadProducto=(TextView)findViewById(R.id.tvUnidadEditar);
        etCantidadProducto=(EditText)findViewById(R.id.etCantidadProducto);
        btAdicionarProducto = (Button)findViewById(R.id.btAdicionarProducto);
        btcancelar=(Button)findViewById(R.id.btCancelar);
        tvCodigoProducto.setText(producto.getCodigoProducto());
        tvDescripcionProducto.setText(producto.getDescripcionProducto());
        tvUnidadProducto.setText(producto.getUnidad());
        tvPrecioProducto.setText(producto.getPrecio().toString());


        btAdicionarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //etCantidadProducto = (EditText)findViewById(R.id.etCantidadProducto);
                boolean isCorrect = true;
                tilCantidadProductoNuevo = (TextInputLayout)findViewById(R.id.tilCantidadProductoNuevo);
                tilCantidadProductoNuevo .setErrorEnabled(false);
                if (tilCantidadProductoNuevo.getEditText().getText().toString().trim().length() <= 0) {
                    tilCantidadProductoNuevo.setError("Ingrese una cantidad");
                    tilCantidadProductoNuevo.setErrorEnabled(true);
                    isCorrect = false;
                }

                if (!isCorrect) {
                    return;
                }

                PedidoDetalle pedidoDetalle = new PedidoDetalle();
                pedidoDetalle.setCantidad(Double.valueOf(etCantidadProducto.getText().toString()));
                pedidoDetalle.setCodigoProducto(tvCodigoProducto.getText().toString());
                pedidoDetalle.setDescripcionProducto(tvDescripcionProducto.getText().toString());
                pedidoDetalle.setUnidad(tvUnidadProducto.getText().toString());
                pedidoDetalle.setPrecio(Double.valueOf(tvPrecioProducto.getText().toString()));
                pedidoDetalle.setIdProducto(Integer.valueOf(producto.getIdProducto()));

                Intent i = getIntent();
                i.putExtra("ARG_PRODUCTO_PEDIDODETALLE", pedidoDetalle);
                setResult(RESULT_OK, i);
                finish();

            }
        });

        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                setResult(RESULT_CANCELED, i);
                finish();
            }
        });

    }
}

