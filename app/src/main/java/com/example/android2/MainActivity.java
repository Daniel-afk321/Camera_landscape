package com.example.android2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //declaracao de variaveis
    private ImageView imageViewFoto;
    private Button btnGeo;
    //esse código esta exibindo a atividade
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //esta verificando a permicao do Dispositivo Móvel
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);
       //esses codigo esta pegando a localização atual do dispositivo e exibindo a na tela.
        btnGeo.setOnClickListener(new View.OnClickListener() {
            //O código esta rastreando a localização atual do dispositivo
            @Override
            public void onClick(View view) {
                 GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();
                // esse codigo obtém a latitude e longitude dessa localização e exibe essa informação em uma mensagem
                if(l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " + lat +"\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                }
            }
        });
//checagem de permicao
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 0);
        }
//esta capturando a foto
        imageViewFoto = (ImageView) findViewById(R.id.imagem_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
            //esse codigo pega o click para tirar a foto
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }

    //Esse método tem como objetivo iniciar a câmera
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //Este método é usado para recuperar a imagem capturada pela câmera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //ta transformando a foto em bitmap
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
