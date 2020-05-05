package com.bluescreen.citizenapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bluescreen.citizenapp.R;


public class JustificacionInspectoriaFragment extends Fragment {


    public JustificacionInspectoriaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_justificacion_inspectoria, container, false);


        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_justificacion);
        String[] letra = {"Elija motivo de Justificacion","Inasistencia","Atraso","Otro"};
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, letra));

        Button  btnJustificacion = (Button) view.findViewById(R.id.btn_justificacion);


        btnJustificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment someFragment = new pasesAccesoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}
