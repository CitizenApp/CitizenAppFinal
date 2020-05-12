package com.bluescreen.citizenapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

Button loginbtn,loguearsebtn;
EditText nombre,correo,contraseña;
Switch personal;
    private ProgressDialog progressDialog;
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

        progressDialog = new ProgressDialog(this);
        cursos=findViewById(R.id.spinner);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Personal");
        personal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    personal.setText("Profesor");
                    cursos.setEnabled(false);
                    rol=1;
                }else{
                    personal.setText("Alumno");
                    cursos.setEnabled(true);
                    rol=0;

                }
            }
        });




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

                if(isOnline(getApplicationContext())){
                    Registrarusuario();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Porfavor, verifica tu conexion a internet",Toast.LENGTH_LONG).show();
                }



            }

        });


    }


    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }




    //standby

    public void verficarprofe(){

    }

    public void verificaralumno(){
        final String nombrealumno =nombre.getText().toString();
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseDatabaseReference.child("Alumnos");

        mFirebaseDatabaseReference.child("Alumnos").orderByChild("correoelectronico").equalTo(nombrealumno).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null && dataSnapshot.getChildren()!=null &&
                        dataSnapshot.getChildren().iterator().hasNext()){
                    Toast.makeText(getApplicationContext(),"existe",Toast.LENGTH_SHORT);
                }else {
                    //Username does not exist
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    public void Registrarusuario(){


        final String nombre2 = nombre.getText().toString();
        final String email = correo.getText().toString().trim();
        final String password = contraseña.getText().toString().trim();


        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(nombre2)){
            Toast.makeText(this,"Se debe ingresar un nombre", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando Registro de Usuario");
        progressDialog.show();



        cursoseleccionado="";
        cursoseleccionado=cursos.getSelectedItem().toString();




        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progressDialog.dismiss();



                    usuarios mio = new usuarios(
                            nombre2,
                            email,
                            rol,
                            cursoseleccionado

                    );

                    FirebaseDatabase.getInstance().getReference("Personal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mio).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this, "Completado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(), LoginActivity.class);
                            startActivity(intent);

                        }
                    });

                }

            }
        });





    }





}
