package com.bluescreen.citizenapp.Profe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bluescreen.citizenapp.Administrador.ui.Curso.cursoModel;
import com.bluescreen.citizenapp.Administrador.ui.Materias.Materias;
import com.bluescreen.citizenapp.Objects.Adapter;
import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.cargarcursoModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubirArchivoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubirArchivoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button seleccionarfichero,agregarfichero;
    EditText titulo;
    Spinner curso,materia;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    String id1,idcurso;
    String idmateria;

    TextView kakita,kakita1;

    List<cursoModel> cursitos;
    List<Materias> materiasl;

    String pp,ff;




    public SubirArchivoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubirArchivoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubirArchivoFragment newInstance(String param1, String param2) {
        SubirArchivoFragment fragment = new SubirArchivoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        llenarcurso();
        llenarmateria();
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subir_archivo, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        seleccionarfichero=getView().findViewById(R.id.btnSelectArchivoProfe);
        titulo=getView().findViewById(R.id.editText);
        materia=getView().findViewById(R.id.spinnerarchivomateria);
        curso=getView().findViewById(R.id.spinnerarchivocurso);
        storageReference= FirebaseStorage.getInstance().getReference("Pdfs");
        databaseReference= FirebaseDatabase.getInstance().getReference();
        curso=getView().findViewById(R.id.spinnerarchivocurso);
        materia=getView().findViewById(R.id.spinnerarchivomateria);

       cursitos =new ArrayList<>();
       materiasl=new ArrayList<>();



        kakita=getView().findViewById(R.id.textView24);
        kakita1=getView().findViewById(R.id.textView26);


        seleccionarfichero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selecciondearchivo();
            }
        });


    }
    public void llenarmateria(){
        FirebaseUser fu = FirebaseAuth.getInstance().getCurrentUser() ;
        final String userIdprofe = fu.getUid();
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("Personal").child(userIdprofe).child("MateriaAsignada");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        idmateria= ds.child("idmateria").getValue(String.class);
                        databaseReference.child("Materias").child(idmateria).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                   Materias nombre= dataSnapshot.getValue(Materias.class);
                                   nombre.setId(dataSnapshot.getKey());
                                   materiasl.add(nombre);
                                }
                                ArrayAdapter<Materias> areasAdapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,materiasl);
                                materia.setAdapter(areasAdapter2);

                                materia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        ff=materiasl.get(position).getId();

                                        kakita.setText(ff);

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
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void llenarcurso(){

        FirebaseUser fu = FirebaseAuth.getInstance().getCurrentUser() ;
        final String userId = fu.getUid();
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("Personal").child(userId).child("CursoAsignado");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        idcurso = ds.child("idcurso").getValue(String.class);
                        databaseReference.child("Cursos").child(idcurso).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){

                                        cursoModel nombre= dataSnapshot.getValue(cursoModel.class);
                                        nombre.setId(dataSnapshot.getKey());
                                        cursitos.add(nombre);


                                }
                                ArrayAdapter<cursoModel> areasAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, cursitos);
                                curso.setAdapter(areasAdapter);
                                curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        pp=cursitos.get(position).getId();
                                        kakita1.setText(pp);
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void selecciondearchivo(){
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"selecciones"),1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode ==RESULT_OK && data !=null && data.getData()!=null){
            subirarchivo(data.getData());
        }
    }

    public void subirarchivo(Uri data){
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        final String iduser=currentFirebaseUser.getUid();
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Subiendo");
        progressDialog.show();
        final StorageReference reference=storageReference.child("Pdfs"+ System.currentTimeMillis() + ".pdf");
       reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {

                       Uri url=uri;
                       subirarchivoModel subirarchivoModel=new subirarchivoModel(titulo.getText().toString(),url.toString(),iduser);
                       databaseReference.child("Cursos").child(pp).child("MateriasAsignadas").child(ff).child("pdf").child(databaseReference.push().getKey()).setValue(subirarchivoModel);
                       Toast.makeText(getContext(),"subido",Toast.LENGTH_SHORT).show();
                       progressDialog.dismiss();
                   }
               });
           }
       }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

               double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
               progressDialog.setMessage("Subiendo"+(int)progress+"%");
           }
       });
    }
}
