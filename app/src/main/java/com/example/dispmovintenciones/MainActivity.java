package com.example.dispmovintenciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private Button boton_pagina;
    private Button boton_llamada;
    private Button boton_mapa;
    private Button boton_foto;
    private Button boton_correo;
    private Button boton_whatsapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton_pagina = (Button) findViewById(R.id.btn_pagina);
        boton_llamada = (Button) findViewById(R.id.btn_llamada);
        boton_mapa = (Button) findViewById(R.id.btn_maps);
        boton_foto = findViewById(R.id.btn_foto);
        boton_correo = findViewById(R.id.btn_correo);
        boton_whatsapp = findViewById(R.id.btn_whatsapp);

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

        boton_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMensajeWhatsapp(null);
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

    private void enviarMensajeWhatsapp(View view){
        Intent intent = new Intent("Android.intent.action.MAIN");
        intent.setAction(Intent.ACTION_SEND);
        intent.setPackage("com.whatsapp");
        intent.setType("text/plain");
        intent.putExtra("jid", "57##########" + "@s.whatsapp.net");// 57 es el código del pais
        intent.putExtra(Intent.EXTRA_TEXT, "Ejemplo de menseja enviado por defecto:\n"+
                "\t\tDirección: Calle ##, ##, ## \n"+"\t\tTelefono: ######...\n"+
                "\t\tNombre: Julián Perez Rodriuez\n"+
                "======= Pedido =======\n"+
                "id=100 - Insectoide: " + String.valueOf(10) + " productos\n"+
                "id=103 - Tierrafertil: " + String.valueOf(5) + " productos\n");

        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setMessage("Whatsapp no se encuentra instalado");
            AlertDialog titulo = alerta.create();
            titulo.setTitle("Cuidado");
            titulo.show();
        }
    }
}