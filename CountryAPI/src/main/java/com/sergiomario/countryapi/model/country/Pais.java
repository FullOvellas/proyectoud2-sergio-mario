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

}
