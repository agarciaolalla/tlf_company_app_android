package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MenuConsumos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager LM;
    private AdaptadorConsumos adaptador;
    private RecyclerView.LayoutManager layoutManager;

    String getMail;
    String getRol;
    String getName;
    String getPass;
    Button volver;


    TextView txvMes;
    TextView txvmensajes;
    TextView txvllamadas;
    TextView txvMb;
    ImageView imageItems;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportActionBar().setTitle("O2");

        getMail = "";
        getPass = "";
        getRol = "";
        getName = "";

        volver = (Button) findViewById(R.id.volvermenu);

        txvMes = (TextView) findViewById(R.id.txvMes);
        txvMb = (TextView) findViewById(R.id.txvMb);
        txvllamadas = (TextView) findViewById(R.id.txvllamadas);
        txvmensajes = (TextView) findViewById(R.id.txvmensajes);
        imageItems=(ImageView) findViewById(R.id.imageItems);


        getMail = getIntent().getStringExtra("mail");
        getName = getIntent().getStringExtra("name");
        getPass = getIntent().getStringExtra("pass");
        getRol= getIntent().getStringExtra("rol");

        //inicializar recyclerview
        recyclerView=(RecyclerView) findViewById(R.id.recyclerlista);

        //declarar visualizacion de los elementos
        LM = new LinearLayoutManager(this);
        LM.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(LM);

        ArrayList<Consumo> consumos = obtenerConsumos();
        ArrayList<Consumo> datosUsuarioLogeado = new ArrayList<>();

        for (int i = 0; i < consumos.size(); i++){
            Consumo datoActual = consumos.get(i);
            if(datoActual.getMail().equals(getMail)){
                datosUsuarioLogeado.add(datoActual);
            }
        }

        //creamos el adaptador
        adaptador = new AdaptadorConsumos(datosUsuarioLogeado);
        //asignar el adaptador al recyclerview
        recyclerView.setAdapter(adaptador);



        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuConsumos.this, MenuPrincipal.class);
                i.putExtra("mail", getMail);
                i.putExtra("rol", getRol);
                startActivity(i);
            }
        });



    }

    public ArrayList<Consumo> obtenerConsumos(){
        ArrayList<Consumo> consumos = new ArrayList<>();

        consumos.add(new Consumo("Enero", "65461", "15", "65", "bilal@gmail.com"));
        consumos.add(new Consumo("Febrero", "7544", "20", "21","bilal@gmail.com"));
        consumos.add(new Consumo("Marzo", "12346", "1", "2", "bilal@gmail.com"));
        consumos.add(new Consumo("Abril", "5461", "63", "8","bilal@gmail.com"));
        consumos.add(new Consumo("Mayo", "99761", "5", "78", "bilal@gmail.com"));
        consumos.add(new Consumo("Junio", "665651", "4", "7", "bilal@gmail.com"));
        consumos.add(new Consumo("Julio", "256486", "475", "0","bilal@gmail.com"));
        consumos.add(new Consumo("Agosto", "165461", "7", "6","bilal@gmail.com"));
        consumos.add(new Consumo("Septiembre", "134697", "0", "7","bilal@gmail.com"));
        consumos.add(new Consumo("Octubre", "49713", "44", "9","bilal@gmail.com"));
        consumos.add(new Consumo("Noviembre", "971646", "45", "2", "bilal@gmail.com"));
        consumos.add(new Consumo("Diciembre", "584976", "16", "1", "bilal@gmail.com"));
        consumos.add(new Consumo("Enero", "70000", "36", "65", "alex@gmail.com"));
        consumos.add(new Consumo("Febrero", "459996", "20", "21","alex@gmail.com"));
        consumos.add(new Consumo("Marzo", "12346", "1", "2", "alex@gmail.com"));
        consumos.add(new Consumo("Abril", "5461", "63", "8","alex@gmail.com"));
        consumos.add(new Consumo("Mayo", "99761", "5", "78", "alex@gmail.com"));
        consumos.add(new Consumo("Junio", "665651", "4", "7", "alex@gmail.com"));
        consumos.add(new Consumo("Julio", "256486", "475", "0","alex@gmail.com"));
        consumos.add(new Consumo("Agosto", "165461", "7", "6","alex@gmail.com"));
        consumos.add(new Consumo("Septiembre", "134697", "0", "7","alex@gmail.com"));
        consumos.add(new Consumo("Octubre", "49713", "44", "9","alex@gmail.com"));
        consumos.add(new Consumo("Noviembre", "971646", "45", "2", "alex@gmail.com"));
        consumos.add(new Consumo("Diciembre", "584976", "16", "1", "alex@gmail.com"));
        consumos.add(new Consumo("Enero", "896511", "48", "65", "ivan@gmail.com"));
        consumos.add(new Consumo("Febrero", "255879", "20", "21","ivan@gmail.com"));
        consumos.add(new Consumo("Marzo", "12346", "1", "2", "ivan@gmail.com"));
        consumos.add(new Consumo("Abril", "5461", "63", "8","ivan@gmail.com"));
        consumos.add(new Consumo("Mayo", "99761", "5", "78", "ivan@gmail.com"));
        consumos.add(new Consumo("Junio", "665651", "4", "7", "ivan@gmail.com"));
        consumos.add(new Consumo("Julio", "256486", "475", "0","ivan@gmail.com"));
        consumos.add(new Consumo("Agosto", "165461", "7", "6","ivan@gmail.com"));
        consumos.add(new Consumo("Septiembre", "134697", "0", "7","ivan@gmail.com"));
        consumos.add(new Consumo("Octubre", "49713", "44", "9","ivan@gmail.com"));
        consumos.add(new Consumo("Noviembre", "971646", "45", "2", "ivan@gmail.com"));
        consumos.add(new Consumo("Diciembre", "584976", "16", "1", "ivan@gmail.com"));
        consumos.add(new Consumo("Enero", "4789632", "78", "65", "julian@gmail.com"));
        consumos.add(new Consumo("Febrero", "4458916", "20", "21","julian@gmail.com"));
        consumos.add(new Consumo("Marzo", "12346", "1", "2", "julian@gmail.com"));
        consumos.add(new Consumo("Abril", "5461", "63", "8","julian@gmail.com"));
        consumos.add(new Consumo("Mayo", "99761", "5", "78", "julian@gmail.com"));
        consumos.add(new Consumo("Junio", "665651", "4", "7", "julian@gmail.com"));
        consumos.add(new Consumo("Julio", "256486", "475", "0","julian@gmail.com"));
        consumos.add(new Consumo("Agosto", "165461", "7", "6","julian@gmail.com"));
        consumos.add(new Consumo("Septiembre", "134697", "0", "7","julian@gmail.com"));
        consumos.add(new Consumo("Octubre", "49713", "44", "9","julian@gmail.com"));
        consumos.add(new Consumo("Noviembre", "971646", "45", "2", "julian@gmail.com"));
        consumos.add(new Consumo("Diciembre", "584976", "16", "1", "julian@gmail.com"));

        return consumos;
    }
}