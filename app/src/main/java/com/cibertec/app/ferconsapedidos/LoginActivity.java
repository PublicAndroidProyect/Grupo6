package com.cibertec.app.ferconsapedidos;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cibertec.app.ferconsapedidos.Dao.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DataBaseHelper dataBaseHelper;
    private Button btAcceder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        //toolbar.setSubtitle("Login");

        //setSupportActionBar(toolbar);
        try {
            dataBaseHelper = new DataBaseHelper(LoginActivity.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        btAcceder = (Button)findViewById(R.id.btAcceder);
        btAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIncome = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                startActivity(newIncome);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
