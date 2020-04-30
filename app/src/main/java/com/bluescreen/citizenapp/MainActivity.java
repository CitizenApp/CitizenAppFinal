package com.bluescreen.citizenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart(){

        super.onStart();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        if (firebaseUser != null ) {
            AtributosGlobales.capturarEmail = firebaseUser.getEmail();
            AtributosGlobales.capturarUsername = firebaseUser.getDisplayName();

            Toast.makeText(MainActivity.this, "Hola" + AtributosGlobales.capturarUsername, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

            // accederInicio();
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
}}
