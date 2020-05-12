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
import com.bluescreen.citizenapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    EditText nombre,correo,rut;
    Button agregar;
    DatabaseReference databaseReference;
    ProfesorModel profesorModel;

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

        nombre=getView().findViewById(R.id.nombreprofe);
        correo=getView().findViewById(R.id.emailRegistroprofe_et);
        rut=getView().findViewById(R.id.rutprofe_et);
        agregar=getView().findViewById(R.id.agregarprofe_btn);
        profesorModel=new ProfesorModel();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Personal").child("Profesores");

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profesorModel.setNombre(nombre.getText().toString().trim());
                profesorModel.setCorreoelectronico(correo.getText().toString().trim());
                profesorModel.setRut(rut.getText().toString().trim());
                databaseReference.push().setValue(profesorModel);

                Toast.makeText(getContext(),"Profesor agregado correctamente",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
