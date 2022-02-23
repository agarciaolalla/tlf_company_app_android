package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Consumo extends AppCompatActivity {

    Button volver;
    String getMail;
    String getRol;
    String getName;
    String getPass;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo);
        getSupportActionBar().setTitle("Consumo");

        volver = (Button) findViewById(R.id.volvermenu);
        getMail = getIntent().getStringExtra("mail");
        getName = getIntent().getStringExtra("name");
        getPass = getIntent().getStringExtra("pass");
        getRol= getIntent().getStringExtra("rol");

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Consumo.this, MenuPrincipal.class);
                i.putExtra("mail", getMail);
                i.putExtra("rol", getRol);
                startActivity(i);
            }
        });

    }
}
