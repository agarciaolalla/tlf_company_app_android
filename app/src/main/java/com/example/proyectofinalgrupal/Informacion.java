package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        mTextViewData = (TextView) findViewById(R.id.textViewData);
        db = FirebaseDatabase.getInstance().getReference();

        volver = (Button) findViewById(R.id.button);
        getMail = getIntent().getStringExtra("mail");
        getPass = getIntent().getStringExtra("pass");
        getName = getIntent().getStringExtra("name");
        getRol= getIntent().getStringExtra("rol");

        db.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    getName = snapshot.child("name").getValue().toString();
                    getMail = snapshot.child("mail").getValue().toString();
                    getPass= snapshot.child("pass").getValue().toString();
                    getRol = snapshot.child("rol").getValue().toString();

                    mTextViewData.setText("El mail es: " + getMail + "El nombre es: " + getName + "El password es: " + getPass + "El rol es: " + getRol);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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

