package com.cibertec.app.ferconsapedidos;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MenuPrincipalActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btClientes;
    private Button btPedidos;
    private Button  btMaps;
    private Button btSynchronize;
    private DrawerLayout dlmenu;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    public  static Integer ARG_OPCION = 0 ;
    public final static Integer ARG_OPCION_CLIENTES = 1;
    public final static Integer  ARG_OPCION_PEDIDOS = 2;
    //private final static int REQUEST_CODE = 1;
    //private final static int REQUEST_CODE_CLICK = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        //toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        //toolbar.setSubtitle("Menu Principal");
        //setSupportActionBar(toolbar);

        btClientes = (Button)findViewById(R.id.btCliente);
        btClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIncome = new Intent(MenuPrincipalActivity.this,ClientesActivity.class);
                ARG_OPCION = ARG_OPCION_CLIENTES;
                //newIncome.putExtra(ARG_OPCION,ARG_CLIENTES);
                MenuPrincipalActivity.this.startActivity(newIncome);
            }
        });
        btPedidos = (Button)findViewById(R.id.btPedidos);
        btPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPedido = new Intent( MenuPrincipalActivity.this, PedidoCabeceraActivity.class);
                ARG_OPCION = ARG_OPCION_PEDIDOS;

                MenuPrincipalActivity.this.startActivity(intentPedido);
            }
        });

        btMaps = (Button)findViewById(R.id.btMaps);
        btMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIncome1 = new Intent( MenuPrincipalActivity.this, ClienteDetalle.class);
                MenuPrincipalActivity.this.startActivity(newIncome1);
            }
        });

        btSynchronize = (Button)findViewById(R.id.btSynchronize);
        btSynchronize .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSincroniza = new Intent(MenuPrincipalActivity.this, SincronizarActivity.class);
                MenuPrincipalActivity.this.startActivity(intentSincroniza);
            }
        });

        dlmenu = (DrawerLayout) findViewById(R.id.dlMenu);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MenuPrincipalActivity.this, dlmenu, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        dlmenu.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }
    View.OnClickListener tvOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Toast.makeText(MenuPrincipalActivity.this, ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            dlmenu.closeDrawer(Gravity.START);
        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

