package com.bluescreen.citizenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bluescreen.citizenapp.Administrador.AdministradorPrincipal;
import com.bluescreen.citizenapp.Profe.Profeactivity;
import com.bluescreen.citizenapp.Profe.Registerprueba;
import com.bluescreen.citizenapp.Profe.usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText correoLoginTxt, passLoginTxt;
    private Button iniciarSesionBtn, registrarUserBtn;
    private FirebaseAuth firebaseAuth;
    Button registro;
    private ProgressDialog progressDialog;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        correoLoginTxt = (EditText) findViewById(R.id.emailLogin_et);
        passLoginTxt = (EditText) findViewById(R.id.passLogin_et);
        iniciarSesionBtn = (Button) findViewById(R.id.login_btn);
        registro=(Button)findViewById(R.id.regitrobtn);

        databaseReference=FirebaseDatabase.getInstance().getReference("Personal");

        progressDialog = new ProgressDialog(this);


        iniciarSesionBtn.setOnClickListener(this);
        registro.setOnClickListener(this);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        correoLoginTxt.setText(AtributosGlobales.capturarEmail);
        passLoginTxt.setText(AtributosGlobales.capturarPass);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.login_btn :
              //loginUsuario();
                if(isOnline(getApplicationContext())){
                    login();
                }
                else{
                    Toast.makeText(LoginActivity.this,"Porfavor, verifica tu conexion a internet",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.regitrobtn:
                Intent intent2 = new Intent(getApplication(), RegisterActivity.class);
                startActivity(intent2);
                break;

        }

    }


    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    private void login(){

        String email = correoLoginTxt.getText().toString().trim();
        String password = passLoginTxt.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Iniciando Sesion");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            onAuthSuccess(task.getResult().getUser());
                            //Toast.makeText(signinActivity.this, "Successfully Signed In", Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }
                    }
                });

    }

    private void onAuthSuccess(FirebaseUser user) {

        //String username = usernameFromEmail(user.getEmail())
        if (user != null) {
            //Toast.makeText(signinActivity.this, user.getUid(), Toast.LENGTH_SHORT).show();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("Personal").child(firebaseAuth.getUid());
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                     usuarios usu= dataSnapshot.getValue(usuarios.class);
                     int userType = (usu.getRol());
                    //for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    // Toast.makeText(signinActivity.this, value, Toast.LENGTH_SHORT).show();

                    switch (userType) {
                        case 0:
                            Intent intent = new Intent(getApplication(), InicioActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            Intent intent2 = new Intent(getApplication(), ProfeInicioActivity.class);
                            startActivity(intent2);
                            break;
                        case 2:
                            Intent intent3 = new Intent(getApplication(), AdministradorPrincipal.class);
                            startActivity(intent3);
                            break;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void loginUsuario(){

        final String email = correoLoginTxt.getText().toString().trim();
        final String  pass = passLoginTxt.getText().toString().trim();
        final String correoprofe="profeprueba@gmail.cl";
        final String contraseñaprofe="123456";
        AtributosGlobales.capturarEmail = email;


        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Iniciando Sesion");
        progressDialog.show();
        //Login firebase
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressDialog.dismiss();

                    Toast.makeText(LoginActivity.this, " Bienvenido : " + AtributosGlobales.capturarEmail + " !!!",Toast.LENGTH_LONG).show();
                  Intent intent = new Intent(getApplication(), InicioActivity.class);
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "No se pudo Iniciar sesion (¿Datos Incorrectos?) ", Toast.LENGTH_LONG).show();
                }
            }

        });



    }




}
