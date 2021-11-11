package com.example.dispmovintenciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button boton_pagina;
    private Button boton_llamada;
    private Button boton_mapa;
    private Button boton_foto;
    private Button boton_correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton_pagina = (Button) findViewById(R.id.btn_pagina);
        boton_llamada = (Button) findViewById(R.id.btn_llamada);
        boton_mapa = (Button) findViewById(R.id.btn_maps);
        boton_foto = findViewById(R.id.btn_foto);
        boton_correo = findViewById(R.id.btn_correo);

        boton_pagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paginaWeb(null);

            }
        });

        boton_llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamadaTelefono(null);
            }
        });

        boton_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMaps(null);
            }
        });

        boton_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFoto(null);
            }
        });

        boton_correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarCorreo(null);
            }
        });
    }

    public void paginaWeb(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.android.com/intl/es_es/"));
        startActivity(intent);
    }

    public void llamadaTelefono(View view){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:3054603377"));
        // Verifica si el permiso esta activado
        if (ActivityCompat.checkSelfPermission( MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    public void googleMaps(View view){
        Uri punto_geografico = Uri.parse("geo:7.136757, -73.116973");
        Intent intent = new Intent(Intent.ACTION_VIEW,punto_geografico);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void tomarFoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    private void enviarCorreo(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"asunto");
        intent.putExtra(Intent.EXTRA_TEXT,"texto  del correo");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"xyz_123@gmail.com"});
        startActivity(intent);
    }
}