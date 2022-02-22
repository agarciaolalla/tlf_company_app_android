package com.example.proyectofinalgrupal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class IniciarSesion extends AppCompatActivity {

    EditText etmail;
    EditText etpass;
    Button bregistrar ;
    Button blogin ;

    private String mail = "";
    private String pass = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion);

        //Inicializo los campos editext y botones
        etmail = findViewById(R.id.CampoEmail);
        etpass = findViewById(R.id.CampoContraseña);
        bregistrar = findViewById(R.id.registrar);
        blogin = findViewById(R.id.iniciarSesion);
        //Inicializo mAuth
        mAuth = FirebaseAuth.getInstance();

        //Creo un onClick para el botón registrar que te lleva a la clase registro
        bregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IniciarSesion.this, Registro.class));
                finish();
            }
        });

        //Creo on Click para el botón iniciar sesión
        blogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                mail = etmail.getText().toString();
                pass = etpass.getText().toString();

                //En caso de que los campos NO ESTEN VACIOS te lleva al método logSesion
                if (!mail.isEmpty() && !pass.isEmpty()){
                    logSesion();
                }else{
                    //Si están vacíos te muestra este mensaje
                    Toast.makeText(IniciarSesion.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    //Método para iniciar sesión, primero mediante signInWithEmailAndPassword la base de datos introduce los datos para ver si son correctos.
    private void logSesion(){
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //En caso de que los datos sean correctos mediante el task.isSuccesful() te llevaría a la clase del menú principal.
                if(task.isSuccessful()){
                    startActivity(new Intent(IniciarSesion.this, MenuPrincipal.class));
                    finish();
                }else{
                    //Si no son correctos te muestra el siguiente mensaje.
                    Toast.makeText(IniciarSesion.this,"Datos erróneos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}