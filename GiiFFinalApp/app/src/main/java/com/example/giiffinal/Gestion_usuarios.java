package com.example.giiffinal;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.giiffinal.adaptadores.ListaUsuariosAdapter;
import com.example.giiffinal.db.DBUsuarios;
import com.example.giiffinal.entidades.Usuarios;

import java.util.ArrayList;

public class Gestion_usuarios extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaUsuarios;
    ArrayList<Usuarios> listaArrayUsuarios;
    ListaUsuariosAdapter adapter;
    Button b17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);

        txtBuscar = findViewById(R.id.txtBuscar);
        listaUsuarios = findViewById(R.id.listaUsuarios);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));
        b17 = (Button) findViewById(R.id.volver_4a);

        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i17 = new Intent(getApplicationContext(), Menu_principal.class);
                startActivity(i17);
                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });


        DBUsuarios dbUsuarios = new DBUsuarios(Gestion_usuarios.this);

        listaArrayUsuarios = new ArrayList<>();

        adapter = new ListaUsuariosAdapter(dbUsuarios.mostrarUsuarios());
        listaUsuarios.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener((SearchView.OnQueryTextListener) this);

    }



    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }

    private void lista() {
        Intent intent = new Intent(this, Gestion_usuarios.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}