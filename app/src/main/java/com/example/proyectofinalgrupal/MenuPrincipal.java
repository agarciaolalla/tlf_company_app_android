package com.example.proyectofinalgrupal;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MenuPrincipal extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private View informacion;
    private View Cerrarsesion;
    private Button contratarProductos;
    String getMail;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //Instanciamos firebase & variables
        Cerrarsesion = (View) findViewById(R.id.Cerrarsesion);
        informacion = (View) findViewById(R.id.informacion);
        mAuth = FirebaseAuth.getInstance();
        contratarProductos = (Button) findViewById(R.id.webView);

        //Guardamos valores pasados de la otra pantalla
        getMail = getIntent().getStringExtra("mail");


        contratarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuPrincipal.this, RedireccionExterna.class);
                i.putExtra("mail", getMail);
                startActivity(i);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.informacion) {
            Intent i = new Intent(MenuPrincipal.this,Informacion.class);
            i.putExtra("mail", getMail); //Te mete la variable del Mail y Rol para que en la otra clase la obtenga directamente
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.consumo) {
            Intent i = new Intent(MenuPrincipal.this,Consumo.class);
            i.putExtra("mail", getMail); //Te mete la variable del Mail y Rol para que en la otra clase la obtenga directamente
            startActivity(i);
            finish();


            return true;
        }
        if (id == R.id.Cerrarsesion) {
            mAuth.signOut();
            startActivity(new Intent(MenuPrincipal.this, IniciarSesion.class));
            finish();
            return true;
        }




        return super.onOptionsItemSelected(item);
    }




}
