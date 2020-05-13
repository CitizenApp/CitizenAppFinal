package com.bluescreen.citizenapp.Administrador.ui.slideshow;

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

import com.bluescreen.citizenapp.Administrador.AlumnoModel;
import com.bluescreen.citizenapp.Profe.usuarios;
import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    EditText nombrealumno,correo,contraseña,rut;
    Button agregar;
    private FirebaseAuth firebaseAuth;

    usuarios usuarios;
    int rol=0;


    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_agregar_alumnos, container, false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        nombrealumno=getView().findViewById(R.id.nombrealumno);
        correo=getView().findViewById(R.id.emailRegistroalumno_et);
        contraseña=getView().findViewById(R.id.contraseña_et);
        rut=getView().findViewById(R.id.rut_et);
        agregar=getView().findViewById(R.id.agregar_btn);
        usuarios=new usuarios();
        firebaseAuth = FirebaseAuth.getInstance();
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nombre2 = nombrealumno.getText().toString();
                final String email = correo.getText().toString().trim();
                final String password = contraseña.getText().toString().trim();
                final String rutt = rut.getText().toString().trim();



                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            usuarios u=new usuarios(
                                    nombre2,email,rol,rutt
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
