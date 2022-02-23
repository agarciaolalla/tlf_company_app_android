package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RedireccionExterna extends AppCompatActivity {

    WebView myWebView;
    Button volver;
    String getMail;
    String getRol;
    String getPass;
    String getName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redireccion_externa);


        volver = (Button) findViewById(R.id.volvermenu);
        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("https://o2online.es/");
        getMail = getIntent().getStringExtra("mail");
        getPass = getIntent().getStringExtra("pass");
        getName = getIntent().getStringExtra("name");
        getRol = getIntent().getStringExtra("rol");

        //SI le da al boton volver se pasa a la actividad Menu principal con los atributos de usuario y rol en todo momento.
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RedireccionExterna.this, MenuPrincipal.class);
                i.putExtra("mail", getMail); //Te mete la variable del Mail para que en la otra clase la obtenga directamente
                i.putExtra("rol", getRol);
                i.putExtra("pass", getPass);
                i.putExtra("name", getName);
                startActivity(i);
            }
        });

    }
}
