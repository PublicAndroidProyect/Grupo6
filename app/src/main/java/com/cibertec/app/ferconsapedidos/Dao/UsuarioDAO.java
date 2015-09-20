package com.cibertec.app.ferconsapedidos.Dao;

import android.database.Cursor;

import com.cibertec.app.ferconsapedidos.Entidad.Usuario;

import java.util.ArrayList;

/**
 * Created by jdiaz on 07/09/2015.
 */
public class UsuarioDAO {
    public ArrayList<Usuario> ListOneUser(Usuario usuario) {
        ArrayList<Usuario> ListOneUser = new ArrayList<>();

        Cursor cursor = null;

        try {
            cursor = DataBaseHelper.myDataBase.query("usuario", new String[]{"NombreUsuario"}, "Usuario=? and Clave=?",
                    new String[]{String.valueOf(usuario.getUsuario()),String.valueOf(usuario.getClave())}, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Usuario user = new Usuario();
                    user.setNombreUsuario(cursor.isNull(cursor.getColumnIndex("NombreUsuario")) ? "" : cursor.getString(cursor.getColumnIndex("NombreUsuario")));
                    ListOneUser.add(user);
                  /*  persona.setApellido(cursor.isNull(cursor.getColumnIndex("Apellido")) ? "" : cursor.getString(cursor.getColumnIndex("Apellido")));
                    persona.setEdad(cursor.isNull(cursor.getColumnIndex("Edad")) ? 0 : cursor.getInt(cursor.getColumnIndex("Edad")));
                    persona.setDNI(cursor.isNull(cursor.getColumnIndex("DNI")) ? "" : cursor.getString(cursor.getColumnIndex("DNI")));
                    lstPersona.add(persona);*/
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return ListOneUser;
    }
}
