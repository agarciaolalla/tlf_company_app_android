package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Informacion extends AppCompatActivity {

    Button volver;
    private String getMail;
   private String getRol;
    private String getPass;
   private String getName;
    private DatabaseReference db;
    private TextView mTextViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion);
        getSupportActionBar().setTitle("Informacion");

        getMail = "";
        getPass = "";
        getRol = "";
        getName = "";

        mTextViewData = (TextView) findViewById(R.id.textViewData);
        db = FirebaseDatabase.getInstance().getReference();

        volver = (Button) findViewById(R.id.volvermenu);
       getMail = getIntent().getStringExtra("mail");
       getName = getIntent().getStringExtra("name");
       getPass = getIntent().getStringExtra("pass");
        getRol= getIntent().getStringExtra("rol");

        mTextViewData.setText("El mail es: " +getMail + "El nombre es: " + getName + getPass + getRol);

        volver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Informacion.this, MenuPrincipal.class);
                    i.putExtra("mail", getMail);
                    i.putExtra("rol", getRol);
                    startActivity(i);
                }
            });

        }



}

