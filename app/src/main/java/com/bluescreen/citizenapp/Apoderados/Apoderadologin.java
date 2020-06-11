package com.bluescreen.citizenapp.Apoderados;

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

import com.bluescreen.citizenapp.Administrador.AdministradorPrincipal;
import com.bluescreen.citizenapp.Administrador.ui.AgregarApoderados.ModelApoderados;
import com.bluescreen.citizenapp.InicioActivity;
import com.bluescreen.citizenapp.LoginActivity;
import com.bluescreen.citizenapp.PrincipalApoderado;
import com.bluescreen.citizenapp.Profe.usuarios;
import com.bluescreen.citizenapp.ProfeInicioActivity;
import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Apoderadologin extends AppCompatActivity implements View.OnClickListener {
    Button login;
    EditText correo,pass;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        databaseReference= FirebaseDatabase.getInstance().getReference("Personal");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        setContentView(R.layout.activity_apoderadologin);
        login=findViewById(R.id.logina);
        correo=findViewById(R.id.emaila);
        pass=findViewById(R.id.passa);
       login.setOnClickListener( this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.logina :
                //loginUsuario();
               login();


        }

    }

    private void login(){

        final String email = correo.getText().toString().trim();
        final String password = pass.getText().toString().trim();


        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Se debe ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Iniciando Sesion");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Apoderadologin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Intent intent2 = new Intent(getApplication(),PrincipalApoderado.class);
                    startActivity(intent2);
                    finish();
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
                    ModelApoderados usu= dataSnapshot.getValue(ModelApoderados.class);
                    String userType = (usu.getRol());

                    //for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    // Toast.makeText(signinActivity.this, value, Toast.LENGTH_SHORT).show();

                    switch (userType) {
                        case "3":
                            Intent intent = new Intent(getApplication(), RegisterActivity.class);
                            startActivity(intent);
                            break;

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
