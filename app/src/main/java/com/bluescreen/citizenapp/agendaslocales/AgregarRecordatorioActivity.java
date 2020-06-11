package com.bluescreen.citizenapp.agendaslocales;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bluescreen.citizenapp.R;

public class AgregarRecordatorioActivity extends AppCompatActivity implements View.OnClickListener {
    public static BDSQLite mBDSQLite;
    private EditText nombreRecordatorio, descripcionRecordatorio, horaRecordatorio, fechaRecordatorio;
    int dia , mes, anio;
    private Button btnGuardar, btnCancelar;
    String fechaFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recordatorio);

        mBDSQLite = new BDSQLite(this, "RECORDATORIOS.sqlite", null, 1);
        mBDSQLite.querydata("CREATE TABLE IF NOT EXISTS recordatorios(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, descripcion VARCHAR, hora VARCHAR, fecha VARCHAR)");

        nombreRecordatorio = (EditText) findViewById(R.id.nombreRecordatorioAdd);
        descripcionRecordatorio = (EditText) findViewById(R.id.descripcionRecordatorioAdd);
        horaRecordatorio = (EditText) findViewById(R.id.horaRecordatorioAdd);
        fechaRecordatorio = (EditText) findViewById(R.id.fechaRecordatorioAdd);

        Bundle bundle = getIntent().getExtras();
        dia = bundle.getInt("dia");
        mes = bundle.getInt("mes");
        anio = bundle.getInt("anio");
       // bundle.getInt("dia");
      //  bundle.getInt("mes");
        //bundle.getInt("anio");


        fechaFinal = anio + "/" + mes + "/" + dia;
        fechaRecordatorio.setText(fechaFinal);
        Toast.makeText(getApplication(), fechaFinal, Toast.LENGTH_SHORT).show();

        btnGuardar = (Button) findViewById(R.id.btnRecordatorioAdd);
        btnCancelar = (Button) findViewById(R.id.btnCancelarRecordatorioAdd);

        btnGuardar.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == btnGuardar.getId()) {
            try {
                //se insertan los datos llamando a msqlitehelper.insertdata(que esta en el model)

                mBDSQLite.insertdata(
                    nombreRecordatorio.getText().toString().trim(),
                    descripcionRecordatorio.getText().toString().trim(),
                    horaRecordatorio.getText().toString().trim(),
                    fechaRecordatorio.getText().toString().trim()
                );

                Toast.makeText(this,"Tarea Agregada",Toast.LENGTH_SHORT).show();
                //se guarda el pdf con los datos capturados//





            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
