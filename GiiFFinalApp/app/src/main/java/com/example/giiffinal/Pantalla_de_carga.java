package com.example.giiffinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Pantalla_de_carga extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_carga);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String user_intent=getIntent().getStringExtra("user");
                Intent intent = new Intent(Pantalla_de_carga.this, Menu_principal.class);
                intent.putExtra("user", user_intent);
                startActivity(intent);
                finish();
            }
        },7300);
    }
}
