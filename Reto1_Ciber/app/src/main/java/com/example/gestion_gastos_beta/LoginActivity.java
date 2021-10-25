package com.example.gestion_gastos_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_login);
    }

    protected void getRegistro(View view){

        Intent registro=new Intent(this, RegisterActivity.class);
        startActivity(registro);

    }

    public void comprubaLogin() {

        ItemsDBOpenHelper wggbt = new ItemsDBOpenHelper(this);

        SQLiteDatabase db = wggbt.getWritableDatabase();

        if (wggbt != null) {

            System.out.println("La base de datos existe pero no tiene tablas");
            wggbt.close();

        }

    }

}