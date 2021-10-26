package com.example.proyecto1giif;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import Wggt_conexion.ItemsDBOpenHelper;

public class Login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void prueba_con() {
    }


    public void check_con() {

        ItemsDBOpenHelper dbHelper = new ItemsDBOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db != null) {

            System.out.println("La base de datos existe: Conexion Establecida");
        }

    }

}
