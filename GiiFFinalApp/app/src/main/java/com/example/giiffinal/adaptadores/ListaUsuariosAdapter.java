package com.example.giiffinal.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giiffinal.Editar;
import com.example.giiffinal.R;
import com.example.giiffinal.entidades.Usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.UsuarioViewHolder>{

   ArrayList<Usuarios> listaUsuarios;
    ArrayList<Usuarios>listaOriginal;

    public ListaUsuariosAdapter(ArrayList<Usuarios> listaUsuarios){
        this.listaUsuarios = listaUsuarios;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaUsuarios);
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuario, null, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        holder.textUsername.setText(listaUsuarios.get(position).getUsername());
        holder.textNombre.setText(listaUsuarios.get(position).getNombre());
        holder.textApellido.setText(listaUsuarios.get(position).getApellido());

    }

    public void  filtrado(String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0){
            listaUsuarios.clear();
            listaUsuarios.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Usuarios> collection = listaUsuarios.stream().filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaUsuarios.clear();
                listaUsuarios.addAll(collection);
            } else {
                for (Usuarios u: listaOriginal) {
                    if (u.getUsername().contains(txtBuscar.toLowerCase())){
                        listaUsuarios.add(u);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }


    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView textUsername, textNombre, textApellido;

        public UsuarioViewHolder(@NonNull View itemview) {
            super(itemview);

            textUsername = itemView.findViewById(R.id.textUsername);
            textNombre = itemView.findViewById(R.id.textNombre);
            textApellido = itemView.findViewById(R.id.textApellido);

            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, Editar.class);
                    intent.putExtra("ID", listaUsuarios.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
