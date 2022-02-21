package com.example.proyectofinalgrupal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email.findViewById(R.id.CampoEmail);
        contraseña.findViewById(R.id.CampoContraseña);

    }
}