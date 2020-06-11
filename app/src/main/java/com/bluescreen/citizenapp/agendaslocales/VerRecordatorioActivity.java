package com.bluescreen.citizenapp.agendaslocales;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bluescreen.citizenapp.R;

import java.util.ArrayList;

import static com.bluescreen.citizenapp.agendaslocales.AgregarRecordatorioActivity.mBDSQLite;

public class VerRecordatorioActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private  SQLiteDatabase db;
    private ListView listView;
    private ArrayAdapter <String> arrayAdapter;
    String cadena;
    String dia ;
    ListAdapter mAdapter=null;

    ArrayList<Modelc> mlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_recordatorio);

        mlist=new ArrayList<>();
        listView = (ListView) findViewById(R.id.listRecordatorios);
        listView.setOnItemLongClickListener(this);
        Bundle bundle = getIntent().getExtras();

        dia = bundle.getString("fechaFinal");
        Toast.makeText(getApplicationContext(),dia,Toast.LENGTH_SHORT).show();
        mBDSQLite= new BDSQLite(this, "RECORDATORIOS.sqlite", null, 1);
        mBDSQLite.querydata("CREATE TABLE IF NOT EXISTS recordatorios(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, descripcion VARCHAR, hora VARCHAR, fecha VARCHAR)");
        final Cursor cursor = mBDSQLite.getData("SELECT * FROM recordatorios");

        while (cursor.moveToNext()){
            int id= cursor.getInt(0);
            String titulof=cursor.getString(1);
            String descripcionf=cursor.getString(2);
            String horaf=cursor.getString(3);
            String fechaf=cursor.getString(4);

            mlist.add(new Modelc(id,titulof,descripcionf,horaf,fechaf));
        }

        if(mlist.size()==0){
            //Toast.makeText(this,"vacio",Toast.LENGTH_SHORT).show();
        }
        mAdapter=new ListAdapter(this,R.layout.rowrecord,mlist);
        listView.setAdapter(mAdapter);







    }



    private void eliminar(String dato){

        String []datos = dato.split(", ");
        String sql =  "delete from recordatorios where concat(tituloRecordatorio, ', ' , contenidoRecordatorio, ',' , " +
            "horaRecordatorio, ', ', fechaRecordatorio) = '" + dato + "'";

        try {
                arrayAdapter.remove(dato);
                listView.setAdapter((arrayAdapter));
        }catch (Exception ex){
            Toast.makeText(getApplication(), "Error"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        CharSequence[]items = new CharSequence[2];
        items[0] = "Eliminar Recordatorio";
        items[1] = "Cancelar";
        builder.setTitle("Eliminar Recordatorio")
            .setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i==0){
                        eliminar(adapterView.getItemAtPosition(i).toString());
                        Toast.makeText(getApplication(), "Recordatorio Eliminado",Toast.LENGTH_LONG).show();
                    }
                }
            });
        AlertDialog dialog = builder.create();
        dialog.show();

        return false;
    }
}
