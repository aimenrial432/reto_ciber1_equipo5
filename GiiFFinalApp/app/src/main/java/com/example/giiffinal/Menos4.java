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

public class Menos4 extends AppCompatActivity  implements View.OnClickListener {

    private int dia,mes,ano;
    Button bfechainicio2, bfechafin2 , b14, bin;
    EditText efechainicio2, efechafin2,pais, ciudad, gasto_total;
    DBHelper DB;
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menos4);
        bfechainicio2 = (Button)findViewById(R.id.fechainicio2);
        bfechafin2 = (Button)findViewById(R.id.fechafin2);
        efechainicio2 = (EditText)findViewById(R.id.efechainicio2);
        efechafin2 = (EditText)findViewById(R.id.efechafin2);
        pais = (EditText)findViewById(R.id.Pais_Pantalla3a2);
        ciudad = (EditText)findViewById(R.id.Ciudad_Pantalla3a2);
        gasto_total = (EditText)findViewById(R.id.Gasto_Pantalla3a2);
        bfechainicio2.setOnClickListener(this);
        bfechafin2.setOnClickListener(this);
        //darle valor
        b14 = (Button)findViewById(R.id.volver_Pantalla3a2);
        DB = new DBHelper(this);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i14 = new Intent(getApplicationContext(), Menu_principal.class);
                startActivity(i14);
            }
        });
        //darle valor
        bin = (Button)findViewById(R.id.introducir_Pantalla3a2);
        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inicio = efechainicio2.getText().toString();
                String fin = efechafin2.getText().toString();
                String Pais = pais.getText().toString();
                String Ciudad = ciudad.getText().toString();
                String Gastos = gasto_total.getText().toString();

                SessionManagement sessionManagement = new SessionManagement(Menos4.this);
                int id = sessionManagement.getSession();
                DBUsuarios dbUsuarios = new DBUsuarios(Menos4.this);

                usuarios = dbUsuarios.verUsuarios(id);
                if (usuarios != null) {
                    String DNI = usuarios.getDni();

                    if (efechainicio2.equals("") || efechafin2.equals("") || pais.equals("") || ciudad.equals("") || gasto_total.equals("")) {
                        Toast.makeText(Menos4.this, "Por favor inserte todos los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean insert = DB.insertData3(DNI, inicio, fin, Pais, Ciudad, Gastos);
                        if (insert == true) {
                            Toast.makeText(Menos4.this, "Datos insertados corractamente", Toast.LENGTH_SHORT).show();
                            Intent i14 = new Intent(getApplicationContext(), Menos4.class);
                            startActivity(i14);
                        } else {
                            Toast.makeText(Menos4.this, "Algo Fallo", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    @Override

    public void onClick(View v) {
        if(v==bfechainicio2) {
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efechainicio2.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                }
            }
                    ,ano,mes,dia);
            datePickerDialog.show();
        }
        if(v==bfechafin2) {
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efechafin2.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                }
            }
                    ,ano,mes,dia);
            datePickerDialog.show();
        }
    }
}