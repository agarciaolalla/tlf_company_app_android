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



        getMail = getIntent().getStringExtra("mail");
        getName = getIntent().getStringExtra("name");
        getPass = getIntent().getStringExtra("pass");
        getRol= getIntent().getStringExtra("rol");


    }
}
