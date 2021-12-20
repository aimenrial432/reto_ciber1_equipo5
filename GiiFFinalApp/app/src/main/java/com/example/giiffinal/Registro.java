package com.example.giiffinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giiffinal.db.DBHelper;
import com.example.giiffinal.db.DBUsuarios;
import com.example.giiffinal.entidades.AES_Decrypt;
import com.example.giiffinal.entidades.SessionManagement;
import com.example.giiffinal.entidades.Usuarios;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Registro  extends AppCompatActivity {

    EditText dni, username, contraseña, nombre, apellido;
    Button b2, b3;
    DBHelper DB;
    com.example.giiffinal.db.DBUsuarios DBUsuarios;
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
//darle valor

        dni = (EditText) findViewById(R.id.P2_DNI2);
        username = (EditText) findViewById(R.id.P2_Usuario2);
        contraseña = (EditText) findViewById(R.id.P2_Contraseña2);
        nombre = (EditText) findViewById(R.id.P2_Nombre2);
        apellido = (EditText) findViewById(R.id.P2_Apellido2);
        b2 = (Button)findViewById(R.id.button_volver);
        b3 = (Button)findViewById(R.id.button2_reg);
        DB = new DBHelper(this);
        DBUsuarios = new DBUsuarios(this);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i2);
               // overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        //darle valor

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DNI = dni.getText().toString();
                String user = username.getText().toString();
                String pass = contraseña.getText().toString();
                String nombr = nombre.getText().toString();
                String apell = apellido.getText().toString();

                if (DNI.equals("")||user.equals("")||pass.equals("")||nombr.equals("")||apell.equals(""))
                    Toast.makeText(Registro.this,"Por favor inserte todos los datos",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DBUsuarios.checkusername(user);
                    if (checkuser == false) {


                        DBUsuarios dbUsuarios = new DBUsuarios(Registro.this);
                        long id = dbUsuarios.insertarContactos(dni.getText().toString(), username.getText().toString(), md5(contraseña.getText().toString()), nombre.getText().toString(), apellido.getText().toString());
                        if (id > 0) {
                            Toast.makeText(Registro.this, "Cargando datos...",Toast.LENGTH_SHORT).show();
                            Intent i3 = new Intent(getApplicationContext(), Pantalla_de_carga.class);

                            usuarios = dbUsuarios.idUsuario(user);

                            SessionManagement sessionManagement = new SessionManagement(Registro.this);
                            sessionManagement.saveSession(usuarios);

                            startActivity(i3);
                            //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } else {
                            Toast.makeText(Registro.this,"Registro fallido",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Registro.this,"Usuario existente",Toast.LENGTH_SHORT).show();

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
