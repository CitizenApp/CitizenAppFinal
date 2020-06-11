package com.bluescreen.citizenapp.agendaslocales;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class BDSQLite extends SQLiteOpenHelper {

    public BDSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void querydata(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);

    }

    public void insertdata(String titulo,String descripcion, String hora, String fecha){
        //se crea una nueva instancia de escritura hacia la base de datos//
        SQLiteDatabase database=getWritableDatabase();
        //se genera la consulta que queremos hacer//
        String sql= "INSERT INTO recordatorios VALUES (NULL,?,?,?,?)";
        //se ejecuta la consulta//
        SQLiteStatement statement= database.compileStatement(sql);
        statement.clearBindings();

        //luego se define los indices de cada variable, es decir en que lugar de la tabla queremos que se guarde//
        statement.bindString(1,titulo);
        statement.bindString(2,descripcion);
        statement.bindString(3, hora);
        statement.bindString(4,fecha);


        statement.executeInsert();



    }
    public Cursor getData(String sql){
        SQLiteDatabase database= getReadableDatabase();
        return database.rawQuery(sql,null);
    }
}
