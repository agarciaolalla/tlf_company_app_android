package com.example.proyectofinalgrupal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {

    private EditText etName;
    private EditText etMail;
    private EditText etPass;
    private Button btRegistrarBasico;
    private Button btRegistrarAvanzado;

    //Variables de datos a registrar
    private String name = "";
    private String pass = "";
    private String mail = "";
    String rol= "";

    FirebaseAuth comprobarFirebase;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);
        etPass = (EditText) findViewById(R.id.editTextTextPassword);
        btRegistrarBasico = (Button) findViewById(R.id.btRegistrarBasico);
        btRegistrarAvanzado = (Button) findViewById(R.id.btRegistrarAvanzado);


        comprobarFirebase = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        btRegistrarBasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                pass = etPass.getText().toString();
                mail = etMail.getText().toString();
                rol = "basico";


                if ( !name.isEmpty() && !mail.isEmpty() && !pass.isEmpty()){

                    if(pass.length() >= 6){
                        registrarUsuario();
                    }else{
                        Toast.makeText(Registro.this, "La contraseña debe tener 6 letras como mínimo", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Registro.this, "No puedes dejar los campos en blanco", Toast.LENGTH_SHORT).show();
                }



            }
        });

        btRegistrarAvanzado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                pass = etPass.getText().toString();
                mail = etMail.getText().toString();
                rol = "avanzado";


                if ( !name.isEmpty() && !mail.isEmpty() && !pass.isEmpty()){

                    if(pass.length() >= 6){
                        registrarUsuario();
                    }else{
                        Toast.makeText(Registro.this, "La contraseña debe tener 6 letras como mínimo", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Registro.this, "No puedes dejar los campos en blanco", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    private void registrarUsuario(){

        comprobarFirebase.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Map<String,Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("mail", mail);
                    map.put("pass", pass);
                    map.put("rol", rol);



                    String id = comprobarFirebase.getCurrentUser().getUid();

                    db.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {

                            if(task2.isSuccessful()){
                                startActivity(new Intent(Registro.this, IniciarSesion.class));
                                finish();
                            }else{
                                Toast.makeText(Registro.this, "No se pudo registrar el usuario introducido, inténtalo de nuevo. 0", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }else{
                    Toast.makeText(Registro.this, "No se pudo registrar el usuario introducido, inténtalo de nuevo. 1", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
