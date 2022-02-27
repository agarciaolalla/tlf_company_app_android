package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MenuPrincipal extends AppCompatActivity {


    WebView myWebView;

    private FirebaseAuth mAuth;

    private View informacion;
    private View Cerrarsesion;
    private Button share;
    private  View MostrarTiendas;
    private View Consumo;
    String getMail;
    String getRol;
    String getPass;
    String getName;
    private DatabaseReference db;
    //private TextView mTextViewData;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        getSupportActionBar().setTitle("O2"); //Indicamos el título de la página.

        share = findViewById(R.id.share);
        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("https://o2online.es/");
        getSupportActionBar().setTitle("O2");
        getMail = "";
        getPass = "";
        getRol = "";
        getName = "";

        //Instanciamos firebase & variables
        Cerrarsesion = (View) findViewById(R.id.Cerrarsesion);
        informacion = (View) findViewById(R.id.informacion);
        MostrarTiendas = (View) findViewById(R.id.MostrarTiendas);
        mAuth = FirebaseAuth.getInstance();
        Consumo = (View) findViewById(R.id.consumo);

        //Guardamos valores pasados de la otra pantalla
        getMail = getIntent().getStringExtra("mail");
        getPass = getIntent().getStringExtra("pass");
        getName = getIntent().getStringExtra("name");
        getRol = getIntent().getStringExtra("rol");


        //Método para compartir la web de la app mediante un botón por whatsapp
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_TEXT, "¡Únete a nuestro operador! - https://o2online.es/");

                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    ex.printStackTrace();

                    Snackbar.make(view, "El dispositivo no tiene instalado WhatsApp", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.informacion) {
            Intent i = new Intent(MenuPrincipal.this,Informacion.class);
            i.putExtra("mail", getMail); //Te mete la variable del Mail para que en la otra clase la obtenga directamente
            i.putExtra("rol", getRol);
            i.putExtra("pass", getPass);
            i.putExtra("name", getName);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.consumo) {
            Intent i = new Intent(MenuPrincipal.this, MenuConsumos.class);
            i.putExtra("mail", getMail);//Te mete la variable del Mail para que en la otra clase la obtenga directamente
            i.putExtra("rol", getRol);
            i.putExtra("pass", getPass);
            i.putExtra("name", getName);
            startActivity(i);
            finish();
            return true;
        }
        if (id == R.id.MostrarTiendas) {
            Intent i = new Intent(MenuPrincipal.this,MapaTiendas.class);
            i.putExtra("mail", getMail);//Te mete la variable del Mail para que en la otra clase la obtenga directamente
            i.putExtra("rol", getRol);
            i.putExtra("pass", getPass);
            i.putExtra("name", getName);
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

