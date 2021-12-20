package com.example.giiffinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.giiffinal.db.DBUsuarios;
import com.example.giiffinal.entidades.SessionManagement;
import com.example.giiffinal.entidades.Usuarios;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu_principal extends AppCompatActivity {

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    boolean correcto = false;
    Usuarios usuarios;
    int id = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);



            BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    protected void onStart(){
        super.onStart();
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(firstFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
                    return true;
                case R.id.secondFragment:
                    loadFragment(secondFragment);
                    return true;
                case R.id.thirdFragment:
                    loadFragment(thirdFragment);
                    return true;

            }
            return false;
        }

    };

    public void loadFragment (Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (fragment == firstFragment){
            SessionManagement sessionManagement = new SessionManagement(Menu_principal.this);
            int id = sessionManagement.getSession();

            if(id != -1) {

                DBUsuarios dbUsuarios = new DBUsuarios(Menu_principal.this);
                usuarios = dbUsuarios.verUsuarios(id);

                if (usuarios != null) {
                    Bundle data = new Bundle();
                    data.putString("username", usuarios.getUsername());

                    fragment.setArguments(data);
                }
            }
        }

        if (fragment == secondFragment) {


        }


        if (fragment == thirdFragment){
            SessionManagement sessionManagement = new SessionManagement(Menu_principal.this);
            int id = sessionManagement.getSession();

            if(id != -1) {

                DBUsuarios dbUsuarios = new DBUsuarios(Menu_principal.this);
                usuarios = dbUsuarios.verUsuarios(id);


                if (usuarios != null) {
                    Bundle data = new Bundle();
                    data.putString("nombre", usuarios.getNombre());
                    data.putString("apellido", usuarios.getApellido());
                    data.putString("DNI", usuarios.getDni());
                    data.putString("username", usuarios.getUsername());

                    fragment.setArguments(data);

                }
            }
        }

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}


