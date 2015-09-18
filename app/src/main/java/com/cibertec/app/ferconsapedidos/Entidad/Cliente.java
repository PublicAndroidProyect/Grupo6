package com.cibertec.app.ferconsapedidos.Entidad;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jdiaz on 07/09/2015.
 */
public class Cliente implements Parcelable {
    public Cliente() {
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    private int IdCliente;
    private  String  NombreCliente;
    private String RUC ;
    private String Direccion;
    private String Telefono;


    @Override
    public String toString() {
        return   NombreCliente + " " +  RUC ;

    }

    protected Cliente(Parcel in) {
        IdCliente = in.readInt();
        NombreCliente = in.readString();
        RUC = in.readString();
        Direccion = in.readString();
        Telefono = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IdCliente);
        dest.writeString(NombreCliente);
        dest.writeString(RUC);
        dest.writeString(Direccion);
        dest.writeString(Telefono);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Cliente> CREATOR = new Parcelable.Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };
}