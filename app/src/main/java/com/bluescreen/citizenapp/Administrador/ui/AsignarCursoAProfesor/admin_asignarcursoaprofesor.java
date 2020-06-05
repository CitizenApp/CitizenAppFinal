package com.bluescreen.citizenapp.Administrador.ui.AsignarCursoAProfesor;

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
import com.bluescreen.citizenapp.cargarcursoModel;
import com.bluescreen.citizenapp.cargarprofesoresasignaracurso;
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
 * Use the {@link admin_asignarcursoaprofesor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_asignarcursoaprofesor extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner curso,profesor;
    DatabaseReference databaseReference;
    String id1,id2;
    String idd1,idd2;
    Button agregar;
    String nombre,nombref;

    cursoaprofesorModel cursoaprofesorModel;

    public admin_asignarcursoaprofesor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment admin_asignarcursoaprofesor.
     */
    // TODO: Rename and change types and number of parameters
    public static admin_asignarcursoaprofesor newInstance(String param1, String param2) {
        admin_asignarcursoaprofesor fragment = new admin_asignarcursoaprofesor();
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
        return inflater.inflate(R.layout.fragment_admin_asignarcursoaprofesor, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        profesor=getView().findViewById(R.id.spinnerprofesorcito);
        curso=getView().findViewById(R.id.spinnercursitox);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        agregar=getView().findViewById(R.id.btnf);
        cursoaprofesorModel=new cursoaprofesorModel();
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbr = FirebaseDatabase.getInstance().getReference()
                        .child("Personal").child(idd2).child("CursoAsignado").child(idd1);
                cursoaprofesorModel.setIdcurso(idd1);
                cursoaprofesorModel.setNombre(nombref);

                dbr.setValue(cursoaprofesorModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });

        llenarcurso();
        llenarprofesor();
    }

    public void llenarprofesor(){
        int rol=1;
        final List<cargarprofesoresasignaracurso> profess=new ArrayList<>();
        databaseReference.child("Personal").orderByChild("rol").equalTo(rol).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds :dataSnapshot.getChildren()){
                    id2=ds.getKey();
                    String nombre=ds.child("nombre").getValue(String.class);
                    profess.add(new cargarprofesoresasignaracurso(id2,nombre));
                }
                ArrayAdapter<cargarprofesoresasignaracurso> areasAdapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, profess);
                profesor.setAdapter(areasAdapter2);
                profesor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        idd2=profess.get(position).getId();
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

    public void llenarcurso(){
            final List<cargarcursoModel> Cursos=new ArrayList<>();
            databaseReference.child("Cursos").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        id1 = ds.getKey();
                        nombre = ds.child("nombre").getValue(String.class);
                        Cursos.add(new cargarcursoModel(id1, nombre));
                    }
                    ArrayAdapter<cargarcursoModel> areasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Cursos);
                    curso.setAdapter(areasAdapter);
                    curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            idd1=Cursos.get(position).getNombrecurso();
                            nombref=Cursos.get(position).getId();
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
