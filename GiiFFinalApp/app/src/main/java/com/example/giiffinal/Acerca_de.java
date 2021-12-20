package com.example.giiffinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Acerca_de extends AppCompatActivity {

    Button b22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        b22 = (Button)findViewById(R.id.volver_acercade);
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i22 = new Intent(getApplicationContext(), Menu_principal.class);
                startActivity(i22);
            }
        });
    }
}