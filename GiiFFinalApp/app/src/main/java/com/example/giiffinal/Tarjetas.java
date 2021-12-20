package com.example.giiffinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.giiffinal.entidades.AES_Decrypt;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.GeneralSecurityException;

public class Tarjetas extends AppCompatActivity {

    private TextView tv_main_card_type;
    private TextView tv_main_card_number;
    private TextView tv_main_card_active;
    private Button bt_main_cambiar;
    private RequestQueue requestQueue;
    private String password = "KeyMustBe16ByteOR24ByteOR32ByT4!";
    private static String URL1 = "http://10.122.27.162:4000/infocards4/user1";

    Button b74;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetas);


        initUI();

        stringRequest();
        b74 = (Button) findViewById(R.id.volver_tj);
        b74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i22 = new Intent(getApplicationContext(), Menu_principal.class);
                startActivity(i22);
            }
        });

    }


    private void initUI() {
        tv_main_card_type = findViewById(R.id.tv_main_card_type);
        tv_main_card_number = findViewById(R.id.tv_main_card_number);
        tv_main_card_active = findViewById(R.id.tv_main_card_active);
        bt_main_cambiar = findViewById(R.id.bt_main_cambiar);

    }


    public void stringRequest() {

        requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject datos = new JSONObject(AES_Decrypt.decrypt(password, response));
                    tv_main_card_type.setText(datos.getString("current_card"));

                    if (datos.getString("current_card").equals("EUROPE")) {

                        tv_main_card_number.setText(datos.getString("card_europe"));
                        tv_main_card_active.setText(datos.getString("lastactive_eur"));
                        bt_main_cambiar.setText("Internacional");
                    } else {
                        tv_main_card_number.setText(datos.getString("card_international"));
                        tv_main_card_active.setText(datos.getString("lastactive_int"));
                        bt_main_cambiar.setText("Europea");
                    }

                } catch (GeneralSecurityException | JSONException e) {

                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Context context = getApplicationContext();
                        CharSequence text = "Error al efectuar la request";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }
                }
        );
        requestQueue.add(request);

    }

    public void changeCard(View view) {

        String region;

        region = (String) bt_main_cambiar.getText();

        if (region.equals("Europea")) {

            region = "EUROPE";
            String URL2 = "http://10.122.27.162:4000/enablecard4/user1/" + region;

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URL2,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject datos = new JSONObject(AES_Decrypt.decrypt(password, response));
                                tv_main_card_type.setText(datos.getString("current_card"));
                                tv_main_card_number.setText(datos.getString("card_europe"));
                                tv_main_card_active.setText(datos.getString("lastactive_eur"));
                                bt_main_cambiar.setText("Internacional");

                            } catch (JSONException | GeneralSecurityException e) {

                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Context context = getApplicationContext();
                            CharSequence text = "Error al cambiar la tarjeta";
                            int duration = Toast.LENGTH_LONG;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
            );
            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }

        if (region.equals("Internacional")) {

            region = "INTERNATIONAL";
            String URL2 = "http://10.122.27.162:4000/enablecard4/user1/" + region;

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URL2,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject datos = new JSONObject(AES_Decrypt.decrypt(password, response));
                                tv_main_card_type.setText(datos.getString("current_card"));
                                tv_main_card_number.setText(datos.getString("card_international"));
                                tv_main_card_active.setText(datos.getString("lastactive_int"));
                                bt_main_cambiar.setText("Europea");


                            } catch (JSONException | GeneralSecurityException e) {

                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Context context = getApplicationContext();
                            CharSequence text = "Error al cambiar la tarjeta";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
            );
            queue.add(stringRequest);

        }
    }

//darle valor



}
