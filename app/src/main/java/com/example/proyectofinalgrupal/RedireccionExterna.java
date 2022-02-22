package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RedireccionExterna extends AppCompatActivity {

    WebView myWebView;
    Button volver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redireccion_externa);


        volver = (Button) findViewById(R.id.button);
        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("https://o2online.es/");
        String getMail = getIntent().getStringExtra("mail");
        String getRol = getIntent().getStringExtra("rol");
        Log.d("MAIL", getMail);
        Log.d("ROL", getRol);

        //SI le da al boton volver se pasa a la actividad Menu principal con los atributos de usuario y rol en todo momento.
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RedireccionExterna.this, MenuPrincipal.class);
                i.putExtra("mail", getMail);
                i.putExtra("rol", getRol);
                startActivity(i);
            }
        });

    }
}
