package com.bluescreen.citizenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText correoLoginTxt, passLoginTxt;
    private Button iniciarSesionBtn, registrarUserBtn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        correoLoginTxt = (EditText) findViewById(R.id.emailLogin_et);
        passLoginTxt = (EditText) findViewById(R.id.passLogin_et);
        iniciarSesionBtn = (Button) findViewById(R.id.login_btn);
        registrarUserBtn = (Button) findViewById(R.id.registrarse_btn);

        progressDialog = new ProgressDialog(this);

        iniciarSesionBtn.setOnClickListener(this);
        registrarUserBtn.setOnClickListener(this);

        correoLoginTxt.setText(AtributosGlobales.capturarEmail);
        passLoginTxt.setText(AtributosGlobales.capturarPass);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.login_btn :
              loginUsuario();
                break;
            case R.id.registrarse_btn :
                Intent intent = new Intent(getApplication(), RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void loginUsuario(){

        final String email = correoLoginTxt.getText().toString().trim();
        final String  pass = passLoginTxt.getText().toString().trim();
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
