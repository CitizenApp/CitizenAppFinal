package com.bluescreen.citizenapp.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bluescreen.citizenapp.Objects.FirebaseReferences;
import com.bluescreen.citizenapp.Objects.Noticias_home;
import com.bluescreen.citizenapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class crearNoticiaHomeFragment extends Fragment {
    Button btnPublicar;
    EditText tituloEdit, fechaEdit, descripcionEdit, contenidoEdit ;




    public static crearNoticiaHomeFragment newInstance() {
        return new crearNoticiaHomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crear_noticia_home_fragment, container, false);
        btnPublicar = view.findViewById(R.id.publcarNoticia_btn);
        tituloEdit = view.findViewById(R.id.titulo_noticiaHome);
        fechaEdit = view.findViewById(R.id.fecha_noticiaHome);
        descripcionEdit = view.findViewById(R.id.descripcion_noticiasHome);
        contenidoEdit = view.findViewById(R.id.contenido_noticiasHome2);

       // final DatabaseReference noticiaRef = database.getReference(FirebaseReferences.CITIZENAPP_NEWSHOME);
        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicarNoticia();
            }
        });
        return view;
    }

    public void publicarNoticia(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference noticiaRef = database.getReference(FirebaseReferences.CITIZENAPP_NEWSHOME);
        final String titulo = tituloEdit.getText().toString().trim();
        final String fecha = fechaEdit.getText().toString().trim();
        final String descripcion = descripcionEdit.getText().toString().trim();
        final String contenido = contenidoEdit.getText().toString().trim();

        Noticias_home noticias = new Noticias_home(titulo, fecha, descripcion, contenido);

        noticiaRef.push().setValue(noticias);





    }

}
