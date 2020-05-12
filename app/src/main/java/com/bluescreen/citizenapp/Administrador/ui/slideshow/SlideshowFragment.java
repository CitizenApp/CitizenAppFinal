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
import com.bluescreen.citizenapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    EditText nombrealumno,correo,rut;
    Button agregar;
    AlumnoModel alumnoModel;

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
        rut=getView().findViewById(R.id.rutalumno_et);
        agregar=getView().findViewById(R.id.agregar_btn);
        alumnoModel=new AlumnoModel();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Personal").child("Alumnos");

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alumnoModel.setNombre(nombrealumno.getText().toString().trim());
                alumnoModel.setCorreoelectronico(correo.getText().toString().trim());
                alumnoModel.setRut(rut.getText().toString().trim());

                databaseReference.push().setValue(alumnoModel);
                Toast.makeText(getContext(),"agrado correctamente",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
