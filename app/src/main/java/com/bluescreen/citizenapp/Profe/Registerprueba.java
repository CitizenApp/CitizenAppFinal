package com.bluescreen.citizenapp.Profe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bluescreen.citizenapp.MainActivity;
import com.bluescreen.citizenapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registerprueba extends AppCompatActivity {

    RadioButton alumno,profesor;
    EditText correo,passs,nombre;
    Button registro;
    private FirebaseAuth firebaseAuth;
    int rol=3;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerprueba);


        alumno = findViewById(R.id.alumnos);
        profesor = findViewById(R.id.profesor);
        correo = (EditText) findViewById(R.id.correo);
        passs = (EditText) findViewById(R.id.pass);
        nombre = (EditText) findViewById(R.id.nombre);
        registro = findViewById(R.id.registro);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("prueba");

    }



}
