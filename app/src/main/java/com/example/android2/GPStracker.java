package com.example.android2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {
   Context context;
    //metodo para usar o context
    public GPStracker(Context c){
        context = c;
    }
    //esta pegando a localizacao
    public Location getLocation(){
//esta verificando a permicao
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
//esta verificando se foi permitido, caso nao foi ira mostra uma mensagem nao foi permitido
            Toast.makeText(context, "Não foi permitir", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //tempo de chamada do gps
        if(isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }
        else
        {
//esta pedindo pro usuario habilitar o GPS
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();
        }
//esta retornando nulo
        return null;
    }
    //
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }
}