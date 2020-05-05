package com.bluescreen.citizenapp.ui;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.direccioneinformaciones.FragmentBuzon;


public class InspectoriaFragment extends Fragment {
    CardView cartitaJustificacion, cartitaPases, cartitaAsistencia, cartitaSugerencias;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspectoria, container, false);

        cartitaJustificacion = (CardView) view.findViewById(R.id.card_justificaciones);
        cartitaPases         = (CardView) view.findViewById(R.id.card_pases);
        cartitaAsistencia         = (CardView) view.findViewById(R.id.card_asistencia);
        cartitaSugerencias         = (CardView) view.findViewById(R.id.card_sugerencias);
        cartitaSugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment someFragment = new FragmentBuzon();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        cartitaAsistencia.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Fragment someFragment = new asistenciaInspectoriaFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }
});
cartitaPases.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Fragment someFragment = new pasesAccesoFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }
});
cartitaJustificacion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Fragment someFragment = new JustificacionInspectoriaFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }
});
        return view;
    }
}
