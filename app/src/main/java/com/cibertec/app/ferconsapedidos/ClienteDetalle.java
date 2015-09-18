package com.cibertec.app.ferconsapedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ClienteDetalle extends AppCompatActivity implements OnMapReadyCallback {
    private SupportMapFragment mSupportMapFragment;
    private GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detalle);
        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(ClienteDetalle.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-11.994317, -77.078928)).title("Marker").draggable(true));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-11.994317, -77.078928)));
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

        //GoogleMapOptions options = new GoogleMapOptions().liteMode(true);
    }
}
