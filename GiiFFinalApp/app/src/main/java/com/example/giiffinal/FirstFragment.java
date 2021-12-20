package com.example.giiffinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giiffinal.entidades.Usuarios;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    TextView Usuario;
    String usuario;
    View view;
    Button tarjertas, fichar, Acerca, Salir, GUsuarios, Proy;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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
        view = inflater.inflate(R.layout.fragment_first, container, false);

        Usuario = (TextView) view.findViewById(R.id.Usuario);

        Bundle data = getArguments();

        if (data !=null) {
            usuario = data.getString("username");
            Usuario.setText(usuario);
        }


        fichar =(Button) view.findViewById(R.id.fichar_char);
        fichar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Fichar.class);
                startActivity(intent);
            }
        });

        tarjertas = (Button) view.findViewById(R.id.tarjetas);
        tarjertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in = new Intent(getActivity(),Tarjetas.class);
               startActivity(in);

            }
        });

        Acerca = (Button) view.findViewById(R.id.acercade_pantalla4);
        Acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),Acerca_de.class);
                startActivity(in);

            }
        });

        GUsuarios = (Button) view.findViewById(R.id.gestionusuario_Pantalla4);
        GUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i22 = new Intent(getActivity(), Gestion_usuarios.class);
                startActivity(i22);
            }
        });

        Proy = (Button) view.findViewById(R.id.pro_p4);
        Proy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Proyectos.class);
                startActivity(in);
            }
        });

        Salir = (Button) view.findViewById(R.id.salir);
        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), MainActivity.class);
                startActivity(in);
            }
        });

                return view;


    }
}
