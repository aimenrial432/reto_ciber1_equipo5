package com.example.giiffinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giiffinal.db.DBUsuarios;
import com.example.giiffinal.entidades.Usuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Editar extends AppCompatActivity {

    EditText textNombre, textApellido, textUsername;
    Button btnGuarda, Bvolver;
    FloatingActionButton fabEliminar;

    boolean correcto = false;
    Usuarios usuarios;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textUsername = findViewById(R.id.textUsername);
        btnGuarda = findViewById(R.id.btnGuarda);
        Bvolver = findViewById(R.id.Volver_g);
        fabEliminar = findViewById(R.id.fabEliminar);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DBUsuarios dbUsuarios = new DBUsuarios(Editar.this);
        usuarios = dbUsuarios.verUsuarios(id);

        if (usuarios !=null){
            textUsername.setText(usuarios.getUsername());
            textNombre.setText(usuarios.getNombre());
            textApellido.setText(usuarios.getApellido());
        }

        Bvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i22 = new Intent(getApplicationContext(), Gestion_usuarios.class);
                startActivity(i22);
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textUsername.getText().toString().equals("")&&!textNombre.getText().toString().equals("")&&!textApellido.getText().toString().equals("")){
                    dbUsuarios.editarUsuarios(id, textUsername.getText().toString(),textNombre.getText().toString(),textApellido.getText().toString());

                    if (correcto){
                        Toast.makeText(Editar.this,"ERROR AL MODIFICAR LOS REGISTROS", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(Editar.this,"REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Editar.this,"TODOS LOS CAMPOS SON OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Editar.this);
                builder.setMessage("¿Desea Elminar Este Usuario?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dbUsuarios.eliminarUsuario(id)){
                                    verRegistro();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }


    private void verRegistro(){
        Intent intent = new Intent(this,Gestion_usuarios.class);
        startActivity(intent);
    }
}