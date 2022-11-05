package com.sergiomario.countryapi.model.country;

import java.io.Serializable;
import java.util.ArrayList;

public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String capital;
    private int numHabitantes;
    private ArrayList<String> monedas;
    private ArrayList<String> idiomas;

    public Pais(String nombre, String capital, int numHabitantes, ArrayList<String> idiomas, ArrayList<String> monedas ) {

        this.nombre = nombre;
        this.capital = capital;
        this.numHabitantes = numHabitantes;
        this.monedas = monedas;
        this.idiomas = idiomas;

    }

    public String getNombre() {
        return nombre;
    }

    public String getCapital() {
        return capital;
    }

    public int getNumHabitantes() {
        return numHabitantes;
    }

    public ArrayList<String> getIdiomas() {
        return idiomas;
    }

    public ArrayList<String> getMonedas() {
        return monedas;
    }

}
