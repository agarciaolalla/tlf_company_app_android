package com.example.proyectofinalgrupal;

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


    TextView txvMes;
    TextView txvmensajes;
    TextView txvllamadas;
    TextView txvMb;
    ImageView imageItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportActionBar().setTitle("Consumos");

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
            if(consumos.get(i).equals(getMail)){
                datosUsuarioLogeado.add(datoActual);
            }
        }

        //creamos el adaptador
        adaptador = new AdaptadorConsumos(datosUsuarioLogeado);
        //asignar el adaptador al recyclerview
        recyclerView.setAdapter(adaptador);


    }

    public ArrayList<Consumo> obtenerConsumos(){
        ArrayList<Consumo> consumos = new ArrayList<>();

        consumos.add(new Consumo("Enero", "65461", "15", "65", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Febrero", "7544", "20", "21", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Marzo", "12346", "1", "2", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Abril", "5461", "63", "8", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Mayo", "99761", "5", "78", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Junio", "665651", "4", "7", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Julio", "256486", "475", "0", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Agosto", "165461", "7", "6", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Septiembre", "134697", "0", "7", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Octubre", "49713", "44", "9", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Noviembre", "971646", "45", "2", R.drawable.ic_contact_mail_black_18dp));
        consumos.add(new Consumo("Diciembre", "584976", "16", "1", R.drawable.ic_contact_mail_black_18dp));

        return consumos;
    }
}