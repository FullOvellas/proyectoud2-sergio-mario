package com.sergiomario.countryapi.dao;

import com.sergiomario.countryapi.model.country.Country;

import java.sql.*;
import java.util.ArrayList;

public class ServerDao {

    public static final ServerDao instance;

    private final String BBDD_USER = "";
    private final String BBDD_PWD = "";
    private Connection db;

    static {

        instance = new ServerDao();

    }

    private ServerDao() {

        try {

            db = DriverManager.getConnection("", BBDD_USER, BBDD_PWD);

        } catch (SQLException e) {



        }

    }

    public ArrayList<Country> searchByName(String searchStr) {

        ArrayList<Country> countries = new ArrayList<>();
        try {

            PreparedStatement ps = db.prepareStatement("SELECT * FROM PAISES WHERE NOMBRE = ?");
            ResultSet rs;

            ps.setString(1, searchStr);

            rs = ps.executeQuery();

            while (rs.next() ) {

                // Parsear string de pais

            }

        } catch (SQLException e) {

        }


        return countries;
    }


}
