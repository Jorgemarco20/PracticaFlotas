package com.empresa.flota.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Vehiculo implements Serializable {

    private String matricula;
    private String marca;
    private String modelo;
    private int anio;

    @SerializedName("kms_recorridos")
    private double kilometraje;

    private String categoriaMantenimiento;

    public Vehiculo() {
    }

    public Vehiculo(String matricula, String marca, String modelo,
                    int anio, double kilometraje,
                    String categoriaMantenimiento) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kilometraje = kilometraje;
        this.categoriaMantenimiento = categoriaMantenimiento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getCategoriaMantenimiento() {
        return categoriaMantenimiento;
    }

    public void setCategoriaMantenimiento(String categoriaMantenimiento) {
        this.categoriaMantenimiento = categoriaMantenimiento;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", kilometraje=" + kilometraje +
                ", categoriaMantenimiento='" + categoriaMantenimiento + '\'' +
                '}';
    }
}


