package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MenuPrincipal extends AppCompatActivity {

    private Button logout;
    private FirebaseAuth mAuth;
    private Button avisos;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        avisos = (Button) findViewById(R.id.webView);
        //MÉTODO PARA CERRAR SESIÓN -ALEJANDRO GARCÍA-
        //Instancio mAuth para hacer uso de la base de datos y el botón de cerrar sesión
        mAuth = FirebaseAuth.getInstance();
        logout = (Button) findViewById(R.id.cerrarSesion);
        //Hago un listener del botón y le indico que se cierre sesión en caso de hacer click y vuelva a la página anterior.
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mAuth.signOut();
                startActivity(new Intent(MenuPrincipal.this, IniciarSesion.class));
                finish();
            }
         });
        //FIN DEL MÉTODO PARA CERRAR SESIÓN -ALEJANDRO GARCÍA-

        //Mediante estas variables tenemos el MAIL del USUARIO que ha iniciado sesión y el ROL que tiene el mismo.
        String getMail = getIntent().getStringExtra("mail");
        String getRol = getIntent().getStringExtra("rol");

        avisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this, RedireccionExterna.class);
                i.putExtra("mail", getMail);
                i.putExtra("rol",getRol);
                startActivity(i);
            }
        });

        //Si es basico haces las acciones en este IF
        if(getRol.equals("basico")){

        }
        //Si es avanzado haces las acciones en este IF
        if(getRol.equals("avanzado")){

        }









    }

}
