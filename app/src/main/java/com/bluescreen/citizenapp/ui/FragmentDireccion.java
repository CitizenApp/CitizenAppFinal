package com.bluescreen.citizenapp.ui;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bluescreen.citizenapp.Fragmentaula;
import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.direccioneinformaciones.FragmentAgendaCiudadana;
import com.bluescreen.citizenapp.direccioneinformaciones.FragmentBuzon;
import com.bluescreen.citizenapp.direccioneinformaciones.FragmentComunicados;
import com.bluescreen.citizenapp.direccioneinformaciones.FragmentOrganigrama;
import com.bluescreen.citizenapp.direccioneinformaciones.FragmentTurnos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDireccion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDireccion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    CardView buzon,organigrama,turnos,comunicados,agenda;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentDireccion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDireccion.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDireccion newInstance(String param1, String param2) {
        FragmentDireccion fragment = new FragmentDireccion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_direccion, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        buzon=(CardView)getView().findViewById(R.id.buzon);
        agenda=(CardView)getView().findViewById(R.id.agenda);
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new FragmentAgendaCiudadana();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        organigrama=(CardView)getView().findViewById(R.id.organigrama);
        turnos=(CardView)getView().findViewById(R.id.turno);
        comunicados=(CardView)getView().findViewById(R.id.comunicados);
        comunicados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new FragmentComunicados();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        turnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new FragmentTurnos();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        organigrama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new FragmentOrganigrama();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        buzon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new FragmentBuzon();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
}
