package com.example.giiffinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giiffinal.db.DBHelper;
import com.example.giiffinal.db.DBUsuarios;
import com.example.giiffinal.entidades.SessionManagement;
import com.example.giiffinal.entidades.Usuarios;

import java.util.Calendar;

public class Mas4 extends AppCompatActivity implements View.OnClickListener {

    private int dia, mes, ano;
    Button bfechainicio3, bfechafin3, b13, intro, mostar;
    EditText efechainicio3, efechafin3, transporte,distancia, peaje, parking;
    DBHelper DB;
    Usuarios usuarios;
    String DNI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas4);
        bfechainicio3 = (Button) findViewById(R.id.bfechainicio3);
        bfechafin3 = (Button) findViewById(R.id.bfechafin3);
        efechainicio3 = (EditText) findViewById(R.id.efechainicio3);
        efechafin3 = (EditText) findViewById(R.id.efechafin3);
        transporte = (EditText) findViewById(R.id.Transporte_Pantalla3a);
        distancia = (EditText) findViewById(R.id.Distancia_Pantalla3a);
        peaje = (EditText) findViewById(R.id.Peaje_Pantalla3a);
        parking = (EditText) findViewById(R.id.Parking_Pantalla3a);
        bfechainicio3.setOnClickListener(this);
        bfechafin3.setOnClickListener(this);
        DB = new DBHelper(this);

        //darle valor
        b13 = (Button) findViewById(R.id.button5_Pantalla3a);
        intro = (Button) findViewById(R.id.button3_Pantalla3a);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i13 = new Intent(getApplicationContext(), Menu_principal.class);
                startActivity(i13);
            }
        });
        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inicio = efechainicio3.getText().toString();
                String fin = efechafin3.getText().toString();
                String Transporte = transporte.getText().toString();
                String Diatancia = distancia.getText().toString();
                String Peaje = peaje.getText().toString();
                String Parking = parking.getText().toString();
                SessionManagement sessionManagement = new SessionManagement(Mas4.this);
                int id = sessionManagement.getSession();
                DBUsuarios dbUsuarios = new DBUsuarios(Mas4.this);

                usuarios = dbUsuarios.verUsuarios(id);
                if (usuarios != null) {
                    String DNI = usuarios.getDni();


                    if (efechainicio3.equals("") || efechafin3.equals("") || transporte.equals("") || distancia.equals("") || peaje.equals("") | parking.equals("")) {
                        Toast.makeText(Mas4.this, "Por favor inserte todos los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean insert = DB.insertData2(inicio, fin, Transporte, Diatancia, Peaje, Parking, DNI);
                        if (insert == true) {
                            Toast.makeText(Mas4.this, "Datos insertados corractamente", Toast.LENGTH_SHORT).show();
                            Intent i14 = new Intent(getApplicationContext(), Mas4.class);
                            startActivity(i14);
                        } else {
                            Toast.makeText(Mas4.this, "Algo Fallo", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        mostar = (Button) findViewById(R.id.button4_Pantalla3a);
        mostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i18 = new Intent(getApplicationContext(), MostrarDatos.class);
                startActivity(i18);
            }
        });
    }

    @Override

    public void onClick(View v) {
        if (v == bfechainicio3) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efechainicio3.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }
        if (v == bfechafin3) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efechafin3.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }
    }
}