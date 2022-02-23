package com.example.proyectofinalgrupal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    private Button btVolver;

    //Variables de datos a registrar
    private String name;
    private String pass;
    private String mail;
    private String rol;


    //Variables necesarias para interactuar con Firebase
    FirebaseAuth mAuth;
    DatabaseReference db;

    //Método OnCeate que se inicia nada más iniciar el programa.
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        //Inicializamos las variables con el xml
        etName = (EditText) findViewById(R.id.etName);
        etMail = (EditText) findViewById(R.id.etMail);
        etPass = (EditText) findViewById(R.id.editTextTextPassword);
        btRegistrarBasico = (Button) findViewById(R.id.btRegistrarBasico);
        btRegistrarAvanzado = (Button) findViewById(R.id.btRegistrarAvanzado);
        btVolver = (Button) findViewById(R.id.volver);

        //Inicializamos las variables de Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        //Creo Dos botones OnClick, uno para crear usuarios con Permisos "AVANZADOS" y otro con permisos "BÁSICOS"
        //Este es el básico
        btRegistrarBasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                pass = etPass.getText().toString();
                mail = etMail.getText().toString();
                rol = "basico";

            //Indicamos que en caso de que cualquiera de los 3 campos esté vacio te muestre el mensaje "No puedes dejar los campos en blanco"
                if ( !name.isEmpty() && !mail.isEmpty() && !pass.isEmpty()){

                    //Indicamos que la contraseña no puede tener menos de 6 carácteres meduiante texto.

                    if(pass.length() >= 6){
                        //En caso de pasar los anteriores if, entra en el método "registrarUsuario"
                        registrarUsuario();
                    }else{
                        Toast.makeText(Registro.this, "La contraseña debe tener 6 letras como mínimo", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Registro.this, "No puedes dejar los campos en blanco", Toast.LENGTH_SHORT).show();
                }



            }
        });
        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registro.this, IniciarSesion.class);
                startActivity(i);
                finish();


            }
        });

        //Botón de registro avanzado, igual que el anterior pero en el ROL indica que es "avanzado"
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

    //Método para registrar usuarios en FIREBASE
    private void registrarUsuario(){
        //Mediante el mAuth comrpobamos que el mail y la contraseña son correctos, en caso de que por ejemplo pongas un mail sin dominio (sin @gmail.com / @hotmail.com)
        //Te lanzará el error No se ha podido registrar, inténtalo de nuevo.
        mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                //Entramos en el on Complete y hace la comprobación citada anteriormente mediante task.isSuccesful()
                if(task.isSuccessful()){
                    //En caso de ser correctos los campos introducideos, mediante este HashMap lo introduce en la base de datos FIREBASE
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("mail", mail);
                    map.put("pass", pass);
                    map.put("rol", rol);

                    String id = mAuth.getCurrentUser().getUid();

                    //Los crea e inserta mediante esta linea db.
                    db.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            //Una vez se han introducido , en caso de haberse completado correctamente te mandará mediante un Intent al Menú principal.
                            //Si no se ha podido completar, te lanzará el mensaje No se pudieron crear los datos correctamente
                            if(task2.isSuccessful()){
                                Intent i = new Intent(Registro.this, MenuPrincipal.class);
                                i.putExtra("mail", mail); //Te mete la variable del Mail y Rol para que en la otra clase la obtenga directamente
                                i.putExtra("rol","avanzado");
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(Registro.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(Registro.this, "No se ha podido registrar, inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
