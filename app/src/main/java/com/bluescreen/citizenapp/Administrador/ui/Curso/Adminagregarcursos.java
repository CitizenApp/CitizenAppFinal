package com.bluescreen.citizenapp.Administrador.ui.Curso;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bluescreen.citizenapp.Administrador.CursoModel;
import com.bluescreen.citizenapp.Administrador.ProfesorModel;
import com.bluescreen.citizenapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Adminagregarcursos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Adminagregarcursos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText curso;
    Button agregarcurso;
    CursoModel cursoModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseReference databaseReference;

    public Adminagregarcursos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * holiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
     *
     *               sds
     * @param param2 Parameter 2.
     * @return A new instance of fragment Adminagregarcursos.
     */
    // TODO: Rename and change types and number of parameters
    public static Adminagregarcursos newInstance(String param1, String param2) {
        Adminagregarcursos fragment = new Adminagregarcursos();
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
        return inflater.inflate(R.layout.fragment_adminagregarcursos, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        curso=getView().findViewById(R.id.cursoet);
        agregarcurso=getView().findViewById(R.id.agregarcurso);
        cursoModel=new CursoModel();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Cursos");

        agregarcurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursoModel.setNombre(curso.getText().toString().trim());
                databaseReference.push().setValue(cursoModel);
            }
        });
    }
}
