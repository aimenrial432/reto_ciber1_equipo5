package com.example.giiffinal.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giiffinal.R;
import com.example.giiffinal.entidades.MostrarDatos_SI;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fini_SI, ffin_SI, transporte_SI, Distancia_SI, Parking_SI, Peaje_SI;
        ImageView foto_gastos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fini_SI=(TextView) itemView.findViewById(R.id.fini_SI);
            ffin_SI=(TextView) itemView.findViewById(R.id.ffin_SI);
            transporte_SI=(TextView) itemView.findViewById(R.id.transporte_SI);
            Distancia_SI=(TextView) itemView.findViewById(R.id.Distancia_SI);
            Parking_SI=(TextView) itemView.findViewById(R.id.Parking_SI);
            Peaje_SI=(TextView) itemView.findViewById(R.id.Peaje_SI);
            foto_gastos=(ImageView) itemView.findViewById(R.id.foto_gastos);

        }
    }

    public List<MostrarDatos_SI> datoslista;

    public RecyclerViewAdaptador(List<MostrarDatos_SI> datoslista) {
        this.datoslista = datoslista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mostrarsi,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fini_SI.setText(datoslista.get(position).getFini_SI());
        holder.ffin_SI.setText(datoslista.get(position).getFfin_SI());
        holder.transporte_SI.setText(datoslista.get(position).getTransporte_SI());
        holder.Distancia_SI.setText(datoslista.get(position).getDistancia_SI());
        holder.Parking_SI.setText(datoslista.get(position).getParking_SI());
        holder.Peaje_SI.setText(datoslista.get(position).getPeaje_SI());
    }

    @Override
    public int getItemCount() {
        return datoslista.size();
    }

}
