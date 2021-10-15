package com.example.gestion_gastos_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
}