package com.bluescreen.citizenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.bluescreen.citizenapp.Apoderados.ApoderadoVideo;
import com.bluescreen.citizenapp.Apoderados.Apoderadohome;
import com.bluescreen.citizenapp.videollamada.AlumnoVideollamada;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PrincipalApoderado extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_apoderado);
        bottomNavigationView=findViewById(R.id.botonbar);
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.containerfrag,new Apoderadohome()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.iniciom:
                        fragment=new Apoderadohome();
                        break;

                    case R.id.reuniones:
                        fragment=new AlumnoVideollamada();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.containerfrag,fragment).commit();
                return true;
            }
        });

    }
}
