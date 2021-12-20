package com.example.giiffinal;

import androidx.appcompat.app.AppCompatActivity;

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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    EditText username, contraseña;
    Button b1, b4;
    DBHelper DB;
    com.example.giiffinal.db.DBUsuarios DBUsuarios;
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //darle valor

        username = (EditText) findViewById(R.id.P1_Usuario);
        contraseña = (EditText) findViewById(R.id.P1_Password);
        b1 = (Button)findViewById(R.id.P1_Registro);
        b4 = (Button)findViewById(R.id.P1_Entrar);
        DB = new DBHelper(this);
        DBUsuarios = new DBUsuarios(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), Registro.class);
                startActivity(i1);
            }
        });
        //darle valor
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = contraseña.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Inserta todos los datos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DBUsuarios.checkusernamepassword(user, md5(pass));
                    if (checkuserpass == true) {
                        Toast.makeText(MainActivity.this, "Estamos preparandolo todo...", Toast.LENGTH_SHORT).show();
                        Intent i4 = new Intent(getApplicationContext(), Pantalla_de_carga.class);


                        DBUsuarios dbUsuarios = new DBUsuarios(MainActivity.this);
                        usuarios = dbUsuarios.idUsuario(user);
                        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                        sessionManagement.saveSession(usuarios);

                        startActivity(i4);

                    }else{
                        Toast.makeText(MainActivity.this, "Las credenciales son invalidas", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}