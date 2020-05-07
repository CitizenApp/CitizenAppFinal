package com.bluescreen.citizenapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.bluescreen.citizenapp.Profe.Profeactivity;
import com.bluescreen.citizenapp.Profe.Registerprueba;
import com.bluescreen.citizenapp.Profe.usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

Button loginbtn,loguearsebtn;
EditText nombre,correo,contraseña;
Switch personal;
Spinner cursos;
    String cursoseleccionado="";
    int rol;







private FirebaseAuth firebaseAuth;

DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        loginbtn=findViewById(R.id.login_btn);
        loguearsebtn=findViewById(R.id.loguearse_btn);
        nombre=findViewById(R.id.registroNombre_et);
        correo=findViewById(R.id.emailRegistro_et);
        contraseña=findViewById(R.id.passRegistro_et);
        personal=findViewById(R.id.switch1);
        cursos=findViewById(R.id.spinner);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Personal");




        loguearsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent2);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Registrarusuario();

            }
        });


    }

    public void Registrarusuario(){

        final String nombre2 = nombre.getText().toString();
        final String email = correo.getText().toString().trim();
        final String password = contraseña.getText().toString().trim();
        cursoseleccionado=cursos.getSelectedItem().toString();
        cursoseleccionado="";
        rol=0;

        personal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             if(isChecked){
                 personal.setText("Profesor");
                 rol=1;
             }else{
                 personal.setText("Alumno");
                 rol=0;
             }
            }
        });

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    usuarios mio = new usuarios(
                            nombre2,
                            email,
                            rol,
                            cursoseleccionado

                    );

                    FirebaseDatabase.getInstance().getReference("Personal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mio).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this, "completado", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });





    }





}
