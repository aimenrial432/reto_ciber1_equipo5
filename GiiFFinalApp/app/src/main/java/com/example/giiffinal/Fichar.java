package com.example.giiffinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.giiffinal.db.DBHelper;
import com.example.giiffinal.db.DBUsuarios;
import com.example.giiffinal.entidades.SessionManagement;
import com.example.giiffinal.entidades.Usuarios;

import java.util.Calendar;

public class Fichar extends AppCompatActivity {
    private int dia, mes, ano, hora, minutos;
    Button bfinicio, bfsalida, bhinicio, bhsalida, ben, bsal, bvolfich;
    EditText efinicio, efsalida, ehinicio, ehsalida;
    TextView nombre_fichar;
    DBHelper DB;
    Usuarios usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichar);

        DB = new DBHelper(this);
        bfinicio = (Button) findViewById(R.id.f_inicio);
        bfsalida = (Button) findViewById(R.id.f_salida);
        bhinicio = (Button) findViewById(R.id.h_inicio);
        bhsalida = (Button) findViewById(R.id.h_salida);
        efinicio = (EditText) findViewById(R.id.fecha_entrada);
        efsalida = (EditText) findViewById(R.id.fecha_salida);
        ehinicio = (EditText) findViewById(R.id.hora_entrada);
        ehsalida = (EditText) findViewById(R.id.hora_salida);
        nombre_fichar = (TextView) findViewById(R.id.nombre_fichar);
        bvolfich = findViewById(R.id.volver_fichar);

        bfinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Fichar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        efinicio.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }
                        , ano, mes, dia);
                datePickerDialog.show();
            }
        });
        bfsalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Fichar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        efsalida.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }
                        , ano, mes, dia);
                datePickerDialog.show();
            }
        });
        bhinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Fichar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        ehinicio.setText(hourOfDay + ":" + minute);
                    }
                }, hora, minutos, false);
                timePickerDialog.show();
            }
        });
        bhsalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Fichar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        ehsalida.setText(hourOfDay + ":" + minute);
                    }
                }, hora, minutos, false);
                timePickerDialog.show();
            }
        });
        bvolfich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i104 = new Intent(getApplicationContext(), Menu_principal.class);
                startActivity(i104);
            }
        });

        SessionManagement sessionManagement = new SessionManagement(Fichar.this);
        int id = sessionManagement.getSession();
        DBUsuarios dbUsuarios = new DBUsuarios(Fichar.this);
        usuarios = dbUsuarios.verUsuarios(id);
        if (usuarios != null) {
            nombre_fichar.setText(usuarios.getUsername());
        }

        ben = (Button) findViewById(R.id.introducir_entrada);
        ben.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {

                                       String finicio = efinicio.getText().toString();
                                       String hinicio = ehinicio.getText().toString();
                                       String nombrefichar = nombre_fichar.getText().toString();

                                       Boolean insert = DB.insertData4(finicio, hinicio, nombrefichar);
                                       if (insert == true) {
                                           Toast.makeText(Fichar.this, "Datos insertados corractamente", Toast.LENGTH_SHORT).show();
                                           Intent i14 = new Intent(getApplicationContext(), Fichar.class);
                                           startActivity(i14);

                                       } else {
                                           Toast.makeText(Fichar.this, "Inserta los datos correctamente", Toast.LENGTH_SHORT).show();
                                       }

                                   }

                               }
        );
        bsal = (Button) findViewById(R.id.introducir_salida);
        bsal.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String fsalida = efsalida.getText().toString();
                                        String hsalida = ehsalida.getText().toString();
                                        String nombrefichar = nombre_fichar.getText().toString();

                                        Boolean insert = DB.insertData5(fsalida, hsalida, nombrefichar);
                                        if (insert == true) {
                                            Toast.makeText(Fichar.this, "Datos insertados corractamente", Toast.LENGTH_SHORT).show();
                                            Intent i14 = new Intent(getApplicationContext(), Fichar.class);
                                            startActivity(i14);

                                        } else {
                                            Toast.makeText(Fichar.this, "Inserta los datos correctamente", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
        );


    }
}