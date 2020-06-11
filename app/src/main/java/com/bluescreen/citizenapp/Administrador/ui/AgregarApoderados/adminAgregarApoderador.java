package com.bluescreen.citizenapp.Administrador.ui.AgregarApoderados;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bluescreen.citizenapp.Administrador.ui.AsignarCursoAlumno.cursoaalumnoModel;
import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.cargaralumnoasignaracurso;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminAgregarApoderador#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminAgregarApoderador extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseReference databaseReference;
    com.bluescreen.citizenapp.Administrador.ui.AsignarCursoAlumno.cursoaalumnoModel cursoaalumnoModel;
    Spinner alumnos;
    Button agrega;
    EditText nombre,correo,contraseña,rut;

    String idalumno,idalumnof;
    ModelApoderados modelApoderados;
    String rol="5";

    private FirebaseAuth firebaseAuth;

    public adminAgregarApoderador() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminAgregarApoderador.
     */
    // TODO: Rename and change types and number of parameters
    public static adminAgregarApoderador newInstance(String param1, String param2) {
        adminAgregarApoderador fragment = new adminAgregarApoderador();
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
        return inflater.inflate(R.layout.fragment_admin_agregar_apoderador, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        modelApoderados=new ModelApoderados();
        nombre=getView().findViewById(R.id.nombreapoderado);
        correo=getView().findViewById(R.id.emailapoderado);
        contraseña=getView().findViewById(R.id.contraseñaapoderado);
        rut=getView().findViewById(R.id.rutapoderado);
        alumnos=getView().findViewById(R.id.spinner3);
        agrega=getView().findViewById(R.id.agregarapoderado);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        cursoaalumnoModel=new cursoaalumnoModel();
        llenaralumno();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Personal");
        agrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               firebaseAuth.createUserWithEmailAndPassword(correo.getText().toString(), contraseña.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           ModelApoderados m=new ModelApoderados(nombre.getText().toString(),correo.getText().toString(),contraseña.getText().toString(),rut.getText().toString(),idalumnof,rol);
                           FirebaseDatabase.getInstance().getReference("Personal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   Toast.makeText(getContext(), "Completado", Toast.LENGTH_SHORT).show();
                               }
                           });
                       }
                   }
               });

               // modelApoderados.setNombre(nombre.getText().toString());
                //modelApoderados.setEmail(correo.getText().toString());
                //modelApoderados.setContraseña(contraseña.getText().toString());
                //modelApoderados.setRut(rut.getText().toString());
                //modelApoderados.setIdalumno(idalumnof);
                //modelApoderados.setRol(rol);
                //databaseReference.push().setValue(modelApoderados);
                Toast.makeText(getContext(),"Agregado",Toast.LENGTH_SHORT).show();
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
