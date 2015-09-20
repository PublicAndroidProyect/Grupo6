package com.cibertec.app.ferconsapedidos;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cibertec.app.ferconsapedidos.Dao.DataBaseHelper;
import com.cibertec.app.ferconsapedidos.Dao.UsuarioDAO;
import com.cibertec.app.ferconsapedidos.Entidad.Usuario;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    private DataBaseHelper dataBaseHelper;
    private Button btAcceder;
    private SharedPreferences sp;
    private final String ARG_USER = "arg_user";
    private final String ARG_PASS = "arg_pass";
    private UsuarioDAO MUsuarioDAO;
    private TextInputLayout tilUser, tilPassword;
    private EditText etUser, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            dataBaseHelper = new DataBaseHelper(LoginActivity.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        tilUser = (TextInputLayout) findViewById(R.id.tilUser);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);

        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);




        sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        MUsuarioDAO = new UsuarioDAO();
        if (sp.contains(ARG_USER) && sp.contains(ARG_PASS) && !sp.getString(ARG_USER, "").isEmpty() && !sp.getString(ARG_PASS, "").isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
            intent.putExtra(MenuPrincipalActivity.ARG_USER, sp.getString(ARG_USER, ""));
            startActivity(intent);
            finish();
        }


        btAcceder = (Button)findViewById(R.id.btAcceder);
        btAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent newIncome = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
//                startActivity(newIncome);
                Usuario user = new Usuario();
                user.setUsuario(etUser.getText().toString().trim());
                user.setClave(etPassword.getText().toString().trim());

                //  Log.v("activity_ui__login", "Usuario logeado");
                ArrayList<Usuario> UserList = MUsuarioDAO.ListOneUser(user);
                if (UserList.size() == 0) {

                    //   Log.v("activity_ui__login", "Usuario finalmente logeado");
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    return;
                }


                SharedPreferences.Editor spe = sp.edit();
                String UserV, PassV;

                UserV = etUser.getText().toString().trim();
                PassV = etPassword.getText().toString().trim();

//                UserV = Base64.encodeToString(UserV.getBytes(), Base64.DEFAULT);
//                PassV = Base64.encodeToString(PassV.getBytes(), Base64.DEFAULT);

                //UserV = new String( Base64.decode( UserV, Base64.DEFAULT ) );
                //PassV = new String( Base64.decode( PassV, Base64.DEFAULT ) );

                spe.putString(ARG_USER, UserV);
                spe.putString(ARG_PASS, PassV);

                spe.commit();

                Intent intent = new Intent(LoginActivity.this, MenuPrincipalActivity.class);
                intent.putExtra(MenuPrincipalActivity.ARG_USER, UserV);

                startActivity(intent);

                finish();

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
