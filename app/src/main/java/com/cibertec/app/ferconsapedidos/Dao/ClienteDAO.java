package com.cibertec.app.ferconsapedidos.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.cibertec.app.ferconsapedidos.Entidad.Cliente;

import java.util.ArrayList;

/**
 * Created by jdiaz on 07/09/2015.
 */
public class ClienteDAO {

    public ArrayList<Cliente> listCliente() {
        ArrayList<Cliente> lstPersona = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = DataBaseHelper.myDataBase.query("Cliente", null, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Cliente cliente = new Cliente();
                    cliente.setNombreCliente(cursor.isNull(cursor.getColumnIndex("NombreCliente")) ? "" : cursor.getString(cursor.getColumnIndex("NombreCliente")));
                    cliente.setDireccion(cursor.isNull(cursor.getColumnIndex("Direccion")) ? "" : cursor.getString(cursor.getColumnIndex("Direccion")));
                    cliente.setRUC(cursor.isNull(cursor.getColumnIndex("RUC")) ? "" : cursor.getString(cursor.getColumnIndex("RUC")));
                    cliente.setIdCliente(cursor.isNull(cursor.getColumnIndex("IdCliente")) ? 0 : cursor.getInt(cursor.getColumnIndex("IdCliente")));
                    cliente.setTelefono(cursor.isNull(cursor.getColumnIndex("Telefono")) ? "" : cursor.getString(cursor.getColumnIndex("Telefono")));
                    //cliente.setDNI(cursor.isNull(cursor.getColumnIndex("DNI")) ? "" : cursor.getString(cursor.getColumnIndex("DNI")));
                    //Log.v("hh", String.valueOf(lstPersona.size()));
                    lstPersona.add(cliente);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            Log.v("hh", String.valueOf(lstPersona.size()));
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return lstPersona;
    }

    public int addCliente(Cliente cliente) {
        long idcliente =-1;
        try {
            ContentValues cv = new ContentValues();
            cv.put("NombreCliente", cliente.getNombreCliente());
            cv.put("Direccion", cliente.getDireccion());
            cv.put("RUC", cliente.getRUC());
            cv.put("Telefono", cliente.getTelefono());
            //cv.put("Telefono", cliente.getDNI());
            idcliente =    DataBaseHelper.myDataBase.insert("Cliente", null, cv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ((int) idcliente);
    }

    public void updateCliente(Cliente cliente) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("NombreCliente", cliente.getNombreCliente());
            cv.put("Direccion", cliente.getDireccion());
            cv.put("RUC", cliente.getRUC());
            cv.put("Telefono", cliente.getTelefono());
            //cv.put("Telefono", cliente.getDNI());
            DataBaseHelper.myDataBase.update("Cliente", cv, "IdCliente = ?", new String[]{String.valueOf(cliente.getIdCliente())});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteCliente(Cliente cliente) {
        try {
            DataBaseHelper.myDataBase.delete("Cliente", "IdCliente = ?", new String[]{String.valueOf(cliente.getIdCliente())});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
