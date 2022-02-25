package com.example.proyectofinalgrupal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Consumo extends AppCompatActivity{

    String mes;
    String mb;
    String llamadas;
    String mensajes;


    public Consumo() {
    }

    public Consumo(String mes, String mb, String llamadas, String mensajes, int imageItems) {
        this.mes = mes;
        this.mb = mb;
        this.llamadas = llamadas;
        this.mensajes = mensajes;

    }


    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public String getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(String llamadas) {
        this.llamadas = llamadas;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }


}
