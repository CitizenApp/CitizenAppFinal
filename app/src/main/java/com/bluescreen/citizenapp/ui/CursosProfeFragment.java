package com.bluescreen.citizenapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluescreen.citizenapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursosProfeFragment extends Fragment {

    public CursosProfeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cursos_profe, container, false);
    }
}
