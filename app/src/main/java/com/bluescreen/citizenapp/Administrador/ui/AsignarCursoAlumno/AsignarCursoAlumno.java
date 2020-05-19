package com.bluescreen.citizenapp.Administrador.ui.AsignarCursoAlumno;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.cargaralumnoasignaracurso;
import com.bluescreen.citizenapp.cargarcursoModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AsignarCursoAlumno#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AsignarCursoAlumno extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner alumnos,curso;
    Button agrega;
    DatabaseReference databaseReference;
    cursoaalumnoModel cursoaalumnoModel;

    String idcurso,idalumno;
    String idcursof,idalumnof;

    public AsignarCursoAlumno() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AsignarCursoAlumno.
     */
    // TODO: Rename and change types and number of parameters
    public static AsignarCursoAlumno newInstance(String param1, String param2) {
        AsignarCursoAlumno fragment = new AsignarCursoAlumno();
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
        return inflater.inflate(R.layout.fragment_asignar_curso_alumno, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        alumnos=getView().findViewById(R.id.spinneralumnocurso);
        curso=getView().findViewById(R.id.spinnercursoalumno);
        agrega=getView().findViewById(R.id.agregarbb);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        cursoaalumnoModel=new cursoaalumnoModel();

        llenarcurso();

        llenaralumno();

        agrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbr = FirebaseDatabase.getInstance().getReference()
                        .child("Personal").child(idalumnof).child("CursoAsignado").child(idcursof);
                cursoaalumnoModel.setIdcurso(idcursof);
                dbr.setValue(cursoaalumnoModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });
    }

    public void llenarcurso(){
        final List<cargarcursoModel> Cursos=new ArrayList<>();
        databaseReference.child("Cursos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    idcurso = ds.getKey();
                    String nombre = ds.child("nombre").getValue(String.class);
                    Cursos.add(new cargarcursoModel(idcurso, nombre));
                }
                ArrayAdapter<cargarcursoModel> areasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Cursos);
                curso.setAdapter(areasAdapter);
                curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        idcursof=Cursos.get(position).getNombrecurso();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void llenaralumno(){
        int rol=0;
        final List<cargaralumnoasignaracurso> alumnoss=new ArrayList<>();
        databaseReference.child("Personal").orderByChild("rol").equalTo(rol).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds :dataSnapshot.getChildren()){
                    idalumno=ds.getKey();
                    String nombre=ds.child("nombre").getValue(String.class);
                    alumnoss.add(new cargaralumnoasignaracurso(idalumno,nombre));
                }

                ArrayAdapter<cargaralumnoasignaracurso> areasAdapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, alumnoss);
                alumnos.setAdapter(areasAdapter2);
                alumnos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        idalumnof=alumnoss.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
