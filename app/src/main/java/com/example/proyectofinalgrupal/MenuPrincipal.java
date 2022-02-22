package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MenuPrincipal extends AppCompatActivity {

    private Button logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

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



    }

}
