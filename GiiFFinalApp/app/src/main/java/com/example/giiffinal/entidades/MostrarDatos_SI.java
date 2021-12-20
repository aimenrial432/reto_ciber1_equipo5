package com.example.giiffinal.entidades;

public class MostrarDatos_SI{
    private String fini_SI, ffin_SI, transporte_SI, distancia_SI, parking_SI, peaje_SI;
    private int foto_gastos;

    public MostrarDatos_SI() {
    }

    public MostrarDatos_SI(String fini_SI, String ffin_SI, String transporte_SI, String distancia_SI, String parking_SI, String peaje_SI, int foto_gastos) {
        this.fini_SI = fini_SI;
        this.ffin_SI = ffin_SI;
        this.transporte_SI = transporte_SI;
        this.distancia_SI = distancia_SI;
        this.parking_SI = parking_SI;
        this.peaje_SI = peaje_SI;
        this.foto_gastos = foto_gastos;
    }

    public String getFini_SI() {
        return fini_SI;
    }

    public void setFini_SI(String fini_SI) {
        this.fini_SI = fini_SI;
    }

    public String getFfin_SI() {
        return ffin_SI;
    }

    public void setFfin_SI(String ffin_SI) {
        this.ffin_SI = ffin_SI;
    }

    public String getTransporte_SI() {
        return transporte_SI;
    }

    public void setTransporte_SI(String transporte_SI) {
        this.transporte_SI = transporte_SI;
    }

    public String getDistancia_SI() {
        return distancia_SI;
    }

    public void setDistancia_SI(String distancia_SI) {
        this.distancia_SI = distancia_SI;
    }

    public String getParking_SI() {
        return parking_SI;
    }

    public void setParking_SI(String parking_SI) {
        this.parking_SI = parking_SI;
    }

    public String getPeaje_SI() {
        return peaje_SI;
    }

    public void setPeaje_SI(String peaje_SI) {
        this.peaje_SI = peaje_SI;
    }

    public int getFoto_gastos() {
        return foto_gastos;
    }

    public void setFoto_gastos(int foto_gastos) {
        this.foto_gastos = foto_gastos;
    }
}
