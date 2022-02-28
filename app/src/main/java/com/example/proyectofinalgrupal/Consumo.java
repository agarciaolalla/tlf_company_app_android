package com.example.proyectofinalgrupal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    String mail;

    //getters y setters de todas las variables a utilizar en menu consumos

    public Consumo() {
    }

    public Consumo(String mes, String mb, String llamadas, String mensajes,String mail) {
        this.mes = mes;
        this.mb = mb;
        this.llamadas = llamadas;
        this.mensajes = mensajes;
        this.mail = mail;

    }

    public String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail= mail;
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
