package com.bluescreen.citizenapp.Administrador.ui.AsignarMateriaAProfe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.transition.SidePropagation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.bluescreen.citizenapp.Asignacionmaterialumnocargarmateria;
import com.bluescreen.citizenapp.R;
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
 * Use the {@link admin_AsignarMateriAProfe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_AsignarMateriAProfe extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner profesor,materia;
    Button asignar;

    String idprofe,idprofe1;
    String idmateria,idmateria1;

    DatabaseReference databaseReference;
    materiaprofeModel materiaprofeModel;

    public admin_AsignarMateriAProfe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment admin_AsignarMateriAProfe.
     */
    // TODO: Rename and change types and number of parameters
    public static admin_AsignarMateriAProfe newInstance(String param1, String param2) {
        admin_AsignarMateriAProfe fragment = new admin_AsignarMateriAProfe();
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
        return inflater.inflate(R.layout.fragment_admin__asignar_materi_a_profe, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        profesor=getView().findViewById(R.id.spprofesor);
        materia=getView().findViewById(R.id.spmateria);
        asignar=getView().findViewById(R.id.btasig);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        materiaprofeModel=new materiaprofeModel();
        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbr = FirebaseDatabase.getInstance().getReference()
                        .child("Personal").child(idprofe1).child("MateriaAsignada").child(idmateria1);
                materiaprofeModel.setIdmateria(idmateria1);
                dbr.setValue(materiaprofeModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });

        llenarprofesor();
        cargarmateria();
    }

    public void cargarmateria(){
        final List<Asignacionmaterialumnocargarmateria> materiass=new ArrayList<>();
        databaseReference.child("Materias").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        idmateria = ds.getKey();
                        String nombre = ds.child("nombre").getValue(String.class);
                        materiass.add(new Asignacionmaterialumnocargarmateria(idmateria, nombre));


                    }
                    ArrayAdapter<Asignacionmaterialumnocargarmateria> areasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, materiass);
                    materia.setAdapter(areasAdapter);
                    materia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                            idmateria1=materiass.get(i).getId();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void llenarprofesor(){
        int rol=1;
        final List<cargarprofesoresasignaracurso> profess=new ArrayList<>();
        databaseReference.child("Personal").orderByChild("rol").equalTo(rol).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds :dataSnapshot.getChildren()){
                    idprofe=ds.getKey();
                    String nombre=ds.child("nombre").getValue(String.class);
                    profess.add(new cargarprofesoresasignaracurso(idprofe,nombre));
                }
                ArrayAdapter<cargarprofesoresasignaracurso> areasAdapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, profess);
                profesor.setAdapter(areasAdapter2);
                profesor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        idprofe1=profess.get(position).getId();
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
