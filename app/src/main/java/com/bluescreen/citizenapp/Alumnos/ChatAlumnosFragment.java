package com.bluescreen.citizenapp.Alumnos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bluescreen.citizenapp.Objects.AdapterMensajesChatAlumnos;
import com.bluescreen.citizenapp.Objects.MensajeChatAlumnos;
import com.bluescreen.citizenapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatAlumnosFragment extends Fragment {
private CircleImageView fotoperfilIv;
private TextView nombreUsuarioTv;
private RecyclerView recyclerViewMensajesRv;
private EditText mensajeChatEt;
private Button enviarMensajeBtn;


private FirebaseDatabase database;
private DatabaseReference databaseReference;
private AdapterMensajesChatAlumnos adapter;



    public ChatAlumnosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_alumnos, container, false);

        nombreUsuarioTv = (TextView) view.findViewById(R.id.nombreUsuarioChatAlumnosTv);
        recyclerViewMensajesRv = (RecyclerView) view.findViewById(R.id.mensajesChatAlumnosRv);
        mensajeChatEt = (EditText) view.findViewById(R.id.mensajesEnviadosChatAlumnosEt);
        enviarMensajeBtn = (Button) view.findViewById(R.id.enviarMensajesChatAlumnosBtn);

        adapter = new AdapterMensajesChatAlumnos(getContext());
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        recyclerViewMensajesRv.setLayoutManager(l);
        recyclerViewMensajesRv.setAdapter(adapter);

        enviarMensajeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.push().setValue(new MensajeChatAlumnos(mensajeChatEt.getText().toString(),nombreUsuarioTv.getText().toString(),"1", "00:00"));
              mensajeChatEt.setText("");
            }
        });
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    setScrollBar();
                }
            });

            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference("chat");

            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                   MensajeChatAlumnos m = dataSnapshot.getValue(MensajeChatAlumnos.class) ;
                   adapter.addMensajes(m);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        return view;
    }
    private void setScrollBar(){
       recyclerViewMensajesRv.scrollToPosition(adapter.getItemCount()-1);
    }
}
