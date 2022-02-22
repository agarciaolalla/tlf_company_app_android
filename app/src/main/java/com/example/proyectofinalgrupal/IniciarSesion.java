package com.example.proyectofinalgrupal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IniciarSesion extends AppCompatActivity {

    EditText email;
    EditText contraseña;
    Button registrar ;
    Button iSesion ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion);
        email = findViewById(R.id.CampoEmail);
        contraseña = findViewById(R.id.CampoContraseña);
        registrar = findViewById(R.id.registrar);
        iSesion = findViewById(R.id.iniciarSesion);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IniciarSesion.this, Registro.class));
                finish();
            }
        });
    }
}