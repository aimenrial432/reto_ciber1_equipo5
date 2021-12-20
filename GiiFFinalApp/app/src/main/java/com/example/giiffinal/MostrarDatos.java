package com.example.giiffinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.giiffinal.adaptadores.RecyclerViewAdaptador;
import com.example.giiffinal.db.DBHelper;

public class MostrarDatos extends AppCompatActivity {


    private RecyclerView recyclerViewdatosSI;
    private RecyclerViewAdaptador adaptadordatosSI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        recyclerViewdatosSI=(RecyclerView)findViewById(R.id.Recycler_SI);
        recyclerViewdatosSI.setLayoutManager(new LinearLayoutManager(this));
        DBHelper DBHelper = new DBHelper(getApplicationContext());
        adaptadordatosSI =new RecyclerViewAdaptador(DBHelper.DatosSI());
        recyclerViewdatosSI.setAdapter(adaptadordatosSI);
    }
}