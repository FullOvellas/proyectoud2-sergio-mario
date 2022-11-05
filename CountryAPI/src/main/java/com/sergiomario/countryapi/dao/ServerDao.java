package com.sergiomario.countryapi.dao;

import com.sergiomario.countryapi.model.country.Country;

import java.util.ArrayList;

public class ServerDao {

    public static final ServerDao instance;

    private final String BBDD_USER = "";
    private final String BBDD_PWD = "";

    static {

        instance = new ServerDao();

    }

    private ServerDao() {



    }

    public ArrayList<Country> searchByName(String searchStr) {

        ArrayList<Country> countries = new ArrayList<>();

        // consulta

        return countries;
    }


}
