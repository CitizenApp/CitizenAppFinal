package com.bluescreen.citizenapp.Administrador.ui.AgregarNoticias;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bluescreen.citizenapp.Objects.Noticias_home;
import com.bluescreen.citizenapp.Profe.subirarchivoModel;
import com.bluescreen.citizenapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminAgregarNoticias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminAgregarNoticias extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button agregar;
    EditText titulo,descripcion;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    String fecha;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminAgregarNoticias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminAgregarNoticias.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminAgregarNoticias newInstance(String param1, String param2) {
        AdminAgregarNoticias fragment = new AdminAgregarNoticias();
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
        return inflater.inflate(R.layout.fragment_admin_agregar_noticias, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        storageReference= FirebaseStorage.getInstance().getReference("Noticias");
        databaseReference= FirebaseDatabase.getInstance().getReference();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        fecha = currentDate.format(todayDate);

        titulo=getView().findViewById(R.id.titulonoticia);
        descripcion=getView().findViewById(R.id.descripcionoticia);
        agregar=getView().findViewById(R.id.btnimagen);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecciondearchivo();
            }
        });

    }

    public void selecciondearchivo(){
        Intent intent=new Intent();
        intent.setType("image/*");
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
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Subiendo");
        progressDialog.show();
        final StorageReference reference=storageReference.child("Noticias"+ System.currentTimeMillis() + ".jpg");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri url=uri;
                        Noticias_home subirarchivoModel=new Noticias_home(titulo.getText().toString(),url.toString(),fecha,descripcion.getText().toString());
                        databaseReference.child("noticias_home").child(databaseReference.push().getKey()).setValue(subirarchivoModel);
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
