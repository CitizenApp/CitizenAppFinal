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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameText , emailText, passwordText;
    private Button registrarBtn, irloginBtn;
    private FirebaseAuth firebaseAuth;
    private ImageView imageRegistro;
    DatabaseReference databaseReference;
    Uri pickimage;
    private int precode = 1;
    private int requestCode = 1;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        usernameText = (EditText) findViewById(R.id.registroNombre_et);
        emailText = (EditText) findViewById(R.id.emailRegistro_et);
        passwordText = (EditText) findViewById(R.id.passRegistro_et);
        registrarBtn = (Button) findViewById(R.id.login_btn);
        irloginBtn = (Button) findViewById(R.id.loguearse_btn);
        progressDialog = new ProgressDialog(this);
        imageRegistro = (ImageView) findViewById(R.id.imagenUsuarioMenu_img);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        imageRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22) {
                    checkandRequestPermission();
                } else {
                    opengallery();
                }

            }
        });

        registrarBtn.setOnClickListener(this);
        irloginBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                registroUsuario();
                break;
            case R.id.loguearse_btn :
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
        }
    }

    private void registroUsuario(){

        final String email = emailText.getText().toString().trim();
        final String  pass = passwordText.getText().toString().trim();
        final String username = usernameText.getText().toString().trim();

        if (pickimage==null){
            Toast.makeText(this, "Debe elegir imagen de Perfil", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Se debe ingresar una contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        if (pass.length()<6){
            Toast.makeText(this,"Debe ingresar una contraseña con un mínimo de 6 caracteres",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this,"Se debe ingresar un nombre de usuario",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Realizando Registro de Usuario");
        progressDialog.show();
        //Creando Usuario con firebase
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    AtributosGlobales.capturarEmail = email;
                    AtributosGlobales.capturarPass = pass;
                    AtributosGlobales.capturarUsername = username;

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", username);
                    map.put("email", email);
                    map.put("password", pass);

                    String id = firebaseAuth.getCurrentUser().getUid();


                    databaseReference.child("users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                actualizarinfo(username, pickimage, firebaseAuth.getCurrentUser());

                            }
                        }
                    });
                 /*   Toast.makeText(RegisterActivity.this,"Se ha registrado el mail: "+ AtributosGlobales.capturarEmail + " Bienvenido : " + AtributosGlobales.capturarUsername + " !!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplication(), LoginActivity.class);
                    startActivity(intent);*/
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión

                        Toast.makeText(RegisterActivity.this, "Ese correo ya existe ", Toast.LENGTH_LONG).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                    } } }}); }

    private void checkandRequestPermission() {
        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(RegisterActivity.this, "Debe aceptar los permisos", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(RegisterActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        precode);
            }
        } else
            opengallery();
    }
    private void opengallery() {
        Intent galeria = new Intent((Intent.ACTION_GET_CONTENT));
        galeria.setType("image/*");
        startActivityForResult(galeria, requestCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == requestCode && data != null) {
            pickimage = data.getData();
            imageRegistro.setImageURI(pickimage);
        }
    }

    private void actualizarinfo(final String username, Uri pickimage, final FirebaseUser currentUser) {
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("fotos_usuarios");
        final StorageReference imagepath = mStorage.child(pickimage.getLastPathSegment());
        imagepath.putFile(pickimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        UserProfileChangeRequest profileupdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .setPhotoUri(uri)
                                .build();
                        currentUser.updateProfile(profileupdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this, "Registro Completo", Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(getApplication(), LoginActivity.class);
                                            startActivity(intent);
                                           // finish();
                                        }
                                    }
                                });
                    }
                });
            }
        });
    }

}
