package com.example.giiffinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giiffinal.db.DBHelper;
import com.example.giiffinal.db.DBUsuarios;
import com.example.giiffinal.entidades.SessionManagement;
import com.example.giiffinal.entidades.Usuarios;

public class Proyectos extends AppCompatActivity {
    Button b21, crear_pro, cerrar_pro, insertar;
    DBHelper DB;
    EditText crear_e_pro, cerrar_e_pro, usuario_e_pro, nombre_e_pro;
    Usuarios usuarios;
    int id = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyectos);DB = new DBHelper(this);
        crear_e_pro=(EditText) findViewById(R.id.crear_e_pro);
        nombre_e_pro=(EditText) findViewById(R.id.nombre_e_pro);
        usuario_e_pro=(EditText) findViewById(R.id.usuario_e_pro);
        cerrar_e_pro=(EditText) findViewById(R.id.cerrar_e_pro);
        SessionManagement sessionManagement = new SessionManagement(Proyectos.this);
        int id = sessionManagement.getSession();

        if(id != -1) {

            DBUsuarios dbUsuarios = new DBUsuarios(Proyectos.this);
            usuarios = dbUsuarios.verUsuarios(id);
        }

        //darle valor
        b21 = (Button)findViewById(R.id.volver_pro);
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i22 = new Intent(getApplicationContext(), Menu_principal.class);
                startActivity(i22);

            }
        });
        crear_pro = (Button)findViewById(R.id.crear_pro);
        crear_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String crear_pro = crear_e_pro.getText().toString();

                String user_intent = usuarios.getUsername();

                String estado = "activo";
                if (crear_pro.equals("")) {
                    Toast.makeText(Proyectos.this, "Por favor inserte todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insert = DB.insertData6(crear_pro,estado, user_intent);
                    if (insert == true) {

                        Toast.makeText(Proyectos.this, "Datos insertados corractamente", Toast.LENGTH_LONG).show();

                        Intent i14 = new Intent(getApplicationContext(), Proyectos.class);
                        startActivity(i14);

                    } else {
                        Toast.makeText(Proyectos.this, "Inserta los datos correctamente", Toast.LENGTH_SHORT).show();
                    }

                }

            }

        }
        );
        insertar = (Button)findViewById(R.id.insertar);
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insertar1 = usuario_e_pro.getText().toString();
                String insertar2 = nombre_e_pro.getText().toString();
                if (insertar1.equals("") || insertar2.equals("")) {
                    Toast.makeText(Proyectos.this, "Por favor inserte todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insert = DB.insertData7(insertar2, insertar1);
                    if (insert == true) {
                        Toast.makeText(Proyectos.this, "Datos insertados corractamente", Toast.LENGTH_LONG).show();
                        Intent i14 = new Intent(getApplicationContext(), Proyectos.class);
                        startActivity(i14);
                    } else {
                        Toast.makeText(Proyectos.this, "Inserta los datos correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );
        cerrar_pro = (Button)findViewById(R.id.cerrar_pro);
        cerrar_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insertar3 = cerrar_e_pro.getText().toString();
                String estado = "inactivo";
                String user_intent=getIntent().getStringExtra("user");
                if (insertar3.equals("")) {
                    Toast.makeText(Proyectos.this, "Por favor inserte todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insert = DB.cerrarpro(estado, insertar3, user_intent);
                    if (insert == true) {
                        Toast.makeText(Proyectos.this, "Proyecto cerrado", Toast.LENGTH_LONG).show();
                        Intent i14 = new Intent(getApplicationContext(), Proyectos.class);
                        startActivity(i14);
                    } else {
                        Toast.makeText(Proyectos.this, "Inserta los datos correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );

    }
    }
