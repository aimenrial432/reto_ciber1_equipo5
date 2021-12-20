package com.example.giiffinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giiffinal.db.DBUsuarios;
import com.example.giiffinal.entidades.SessionManagement;
import com.example.giiffinal.entidades.Usuarios;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    private static final int RESULT_OK = -1;
    View view;
    ImageView imagen;
    TextView P7Nombre;
    TextView P7Apellido;
    TextView P7DNI;
    TextView P7UltimaConexion;
    TextView P7Usuario;
    String nombre, apellido, dni, username;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_third, container, false);

        imagen = (ImageView) view.findViewById(R.id.img_usuario);
        P7Nombre = (TextView) view.findViewById(R.id.P7Nombre);
        P7Apellido = (TextView) view.findViewById(R.id.P7Apellido);
        P7DNI = (TextView) view.findViewById(R.id.P7DNI);
        P7UltimaConexion = (TextView) view.findViewById(R.id.P7UltimaConexion);
        P7Usuario = (TextView) view.findViewById(R.id.P7Usuario);


            Bundle data = getArguments();

            if (data !=null) {

                nombre = data.getString("nombre");
                P7Nombre.setText(nombre);

                apellido = data.getString("apellido");
                P7Apellido.setText(apellido);

                dni = data.getString("DNI");
                P7DNI.setText(dni);

                username = data.getString("username");
                P7Usuario.setText(username);

            }
        Date calendar =  Calendar.getInstance().getTime();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        P7UltimaConexion.setText(currentDate);


        return view;
    }


    public void onclick(View view) {
        cargarimagen();
    }

    private void cargarimagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imagen.setImageURI(path);
        }
    }

    public void getSharedPreferences(String shared_pref_name, int modePrivate) {
    }
}


