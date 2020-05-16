package com.bluescreen.citizenapp.Administrador.ui.Asignarmaterias;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bluescreen.citizenapp.Administrador.MateriaModel;
import com.bluescreen.citizenapp.Asignacionmaterialumnocargarmateria;
import com.bluescreen.citizenapp.AsignarmateriasalumnosModel;
import com.bluescreen.citizenapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminasignarmateriasalumnos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminasignarmateriasalumnos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String id,iddd;


    Spinner materias,alumnos;
    Button agregar;
    DatabaseReference databaseReference,databaseReference2;
    TextView idesito,idesito2;

    asignaciondemateriasModel asignaciondemateriasModel;

    String alumnoseleccionado="";

    String id2,idd2;

    public adminasignarmateriasalumnos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminasignarmateriasalumnos.
     */
    // TODO: Rename and change types and number of parameters
    public static adminasignarmateriasalumnos newInstance(String param1, String param2) {
        adminasignarmateriasalumnos fragment = new adminasignarmateriasalumnos();
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

        return inflater.inflate(R.layout.fragment_adminasignarmateriasalumnos, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        agregar=getView().findViewById(R.id.agregarasignacion);
        materias=getView().findViewById(R.id.spinnermateria);
          alumnos=getView().findViewById(R.id.spinneralumno);
        idesito=getView().findViewById(R.id.id);
        idesito2=getView().findViewById(R.id.id2);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        asignaciondemateriasModel=new asignaciondemateriasModel();
        databaseReference2= FirebaseDatabase.getInstance().getReference().child("Materias-Alumnos");



        cargaralumno();
        cargarmateria();

       agregar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String keyValue = iddd;
               String keyValue2= id;
               DatabaseReference dbr = FirebaseDatabase.getInstance().getReference()
                       .child("Personal").child(idd2).child("MateriasAsignadas").child(iddd);

               asignaciondemateriasModel.setIdmateria(iddd);
               dbr.setValue(asignaciondemateriasModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {

                   }
               });


           }
       });


    }

    //alumno
    public void cargarmateria(){
        final List<Asignacionmaterialumnocargarmateria> materiass=new ArrayList<>();
        databaseReference.child("Materias").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                         id = ds.getKey();
                        String nombre = ds.child("nombre").getValue(String.class);
                        materiass.add(new Asignacionmaterialumnocargarmateria(id, nombre));


                    }
                    ArrayAdapter<Asignacionmaterialumnocargarmateria> areasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, materiass);
                    materias.setAdapter(areasAdapter);
                    materias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                            iddd=materiass.get(i).getId();
                            idesito2.setText(iddd);
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
// curso
    public void cargaralumno(){
        int rol=0;
        final List<AsignarmateriasalumnosModel> alumnoss=new ArrayList<>();
        databaseReference.child("Personal").orderByChild("rol").equalTo(rol).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds :dataSnapshot.getChildren()){
                         id2=ds.getKey();
                        String nombre=ds.child("nombre").getValue(String.class);
                        alumnoss.add(new AsignarmateriasalumnosModel(id2,nombre));
                    }
                    ArrayAdapter<AsignarmateriasalumnosModel> areasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, alumnoss);
                    alumnos.setAdapter(areasAdapter);
                    alumnos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                             idd2=alumnoss.get(i).getNombrealumno();
                            idesito.setText(idd2);
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
}
