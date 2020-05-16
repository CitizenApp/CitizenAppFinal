package com.bluescreen.citizenapp.Administrador.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bluescreen.citizenapp.Administrador.ProfesorModel;
import com.bluescreen.citizenapp.Profe.usuarios;
import com.bluescreen.citizenapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    EditText nombre,correo,rut,contraseña;
    Button agregar;
    DatabaseReference databaseReference;
    ProfesorModel profesorModel;
    private FirebaseAuth firebaseAuth;
    int rol=1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_agregar_profesores, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        nombre = getView().findViewById(R.id.nombreprofe);
        correo = getView().findViewById(R.id.emailRegistroprofe_et);
        rut = getView().findViewById(R.id.rutprofe_et);
        contraseña = getView().findViewById(R.id.contraseñaprofe_et);
        agregar = getView().findViewById(R.id.agregarprofe_btn);
        firebaseAuth = FirebaseAuth.getInstance();
        profesorModel = new ProfesorModel();

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombre2 = nombre.getText().toString();
                final String email = correo.getText().toString().trim();
                final String password = contraseña.getText().toString().trim();
                final String rutt = rut.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            usuarios u = new usuarios(
                                    nombre2, email, rol, rutt
                            );
                            FirebaseDatabase.getInstance().getReference("Personal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getContext(), "Completado", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}
