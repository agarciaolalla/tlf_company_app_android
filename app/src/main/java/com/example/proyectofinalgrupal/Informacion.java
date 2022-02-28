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
    private TextView mTextViewData2;
    private TextView mTextViewData3;
    private TextView mTextViewData4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion);
        //le ponemos titulo a la actionbar
        getSupportActionBar().setTitle("Informacion");

        //Inicializo todas las variables para que no haya NullPointerException
        getMail = "";
        getPass = "";
        getRol = "";
        getName = "";
        //Inicializo campos edittext, botones y la db
        mTextViewData = (TextView) findViewById(R.id.textViewData);
        mTextViewData2 = (TextView) findViewById(R.id.textViewData2);
        mTextViewData3 = (TextView) findViewById(R.id.textViewData3);
        mTextViewData4 = (TextView) findViewById(R.id.textViewData4);
        db = FirebaseDatabase.getInstance().getReference();

        volver = (Button) findViewById(R.id.volvermenu);
       getMail = getIntent().getStringExtra("mail");
       getName = getIntent().getStringExtra("name");
       getPass = getIntent().getStringExtra("pass");
        getRol= getIntent().getStringExtra("rol");

        mTextViewData.setText(" El mail es: " + getMail);
        mTextViewData2.setText(" El nombre es: " + getName);
        mTextViewData3.setText(" El password es: " + getPass);
        mTextViewData4.setText(" El Rol es: " + getRol);

        //boton volver a la pestaña anterior
        volver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Mediante putExtra pasamos a la otra clase los datos del mail que hemos introducido al iniciar sesión para no
                    //perderlos al darle para atras
                    Intent i = new Intent(Informacion.this, MenuPrincipal.class);
                    i.putExtra("mail", getMail);
                    i.putExtra("rol", getRol);
                    startActivity(i);
                }
            });

        }



}

