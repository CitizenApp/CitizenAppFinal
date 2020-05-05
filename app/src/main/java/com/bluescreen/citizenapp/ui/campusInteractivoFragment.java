package com.bluescreen.citizenapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bluescreen.citizenapp.AgendaFragment;
import com.bluescreen.citizenapp.AvisosFragment;
import com.bluescreen.citizenapp.DocumentosFragment;
import com.bluescreen.citizenapp.Fragmentaula;
import com.bluescreen.citizenapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link campusInteractivoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class campusInteractivoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RelativeLayout artes;

    public campusInteractivoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment campusInteractivoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static campusInteractivoFragment newInstance(String param1, String param2) {
        campusInteractivoFragment fragment = new campusInteractivoFragment();
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
        return inflater.inflate(R.layout.fragment_campus_interactivo, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        RelativeLayout artes=getView().findViewById(R.id.artes);
        artes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment someFragment = new Fragmentaula();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
