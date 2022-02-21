package com.example.proyectofinalgrupal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class IniciarSesion extends AppCompatActivity {

    EditText email;
    EditText contraseña;
    Button registrar = findViewById(R.id.registrar);
    Button iSesion = findViewById(R.id.iniciarSesion);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion);
        email.findViewById(R.id.CampoEmail);
        contraseña.findViewById(R.id.CampoContraseña);

    }
}