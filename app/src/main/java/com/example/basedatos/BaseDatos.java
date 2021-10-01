package com.example.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {


    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.NOMBRE_BD, null, ConstantesBaseDatos.VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + ConstantesBaseDatos.FORMULARIO + "("
                + ConstantesBaseDatos.ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.POSITION  + " INTEGER, "
                + ConstantesBaseDatos.NOMBRE    + " TEXT, "
                + ConstantesBaseDatos.OCUPACION + " TEXT, "
                + ConstantesBaseDatos.EMAIL     + " TEXT, "
                + ConstantesBaseDatos.CELULAR   + " TEXT, "
                + ConstantesBaseDatos.DIRECCION + " TEXT"
                + ")";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.FORMULARIO);
        onCreate(db);

    }

    public void insertarDato(ContentValues contentValues) {

        SQLiteDatabase database = getWritableDatabase();

        database.insert(ConstantesBaseDatos.FORMULARIO,null,contentValues);
        database.close();
    }


    public String obtenerDato(int position) {

        String dato = null;

        SQLiteDatabase database = getWritableDatabase();

        String query = "SELECT * FROM " + ConstantesBaseDatos.FORMULARIO +
                " WHERE " + ConstantesBaseDatos.POSITION + "=" + position;

        Cursor cursor = database.rawQuery(query,null);

        while (cursor.moveToNext()){
            switch(position) {
                case 0: dato = cursor.getString(2);break;
                case 1: dato = cursor.getString(3);break;
                case 2: dato = cursor.getString(4);break;
                case 3: dato = cursor.getString(5);break;
                case 4: dato = cursor.getString(6);break;
            }
        }
        cursor.close();
        database.close();
        return dato;
    }

    public void actualizarDato(ContentValues contentValues,int position) {

        SQLiteDatabase database = getWritableDatabase();
        database.update(ConstantesBaseDatos.FORMULARIO,contentValues,ConstantesBaseDatos.POSITION+"="+position,null);
    }
}
