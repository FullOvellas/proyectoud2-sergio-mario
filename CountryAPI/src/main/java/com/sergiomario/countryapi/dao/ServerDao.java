package com.sergiomario.countryapi.dao;

import com.sergiomario.countryapi.model.country.Pais;

import java.sql.*;
import java.util.ArrayList;

public class ServerDao {

    public static final ServerDao instance;

    private final String BBDD_USER = "root";
    private final String BBDD_PWD = "root";
    private Connection db;

    static {

        instance = new ServerDao();

    }

    private ServerDao() {

        try {

            db = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBDD_PAISES", BBDD_USER, BBDD_PWD);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public ArrayList<Pais> searchByName(String searchStr) {

        ArrayList<Pais> out = new ArrayList<>();

        try {
                                                    // TODO: editar para que sea LIKE NOMBRE o similar
            PreparedStatement ps = db.prepareStatement("SELECT ID_PAIS,NOMBRE,NUM_HABITANTES,CAPITAL  FROM PAISES WHERE NOMBRE = ?");
            ResultSet rs;

            ps.setString(1, searchStr);

            rs = ps.executeQuery();

            while (rs.next() ) {

                int idPais = rs.getInt(1);
                String nombre = rs.getString(2);
                int habitantes = rs.getInt(3);
                String capital = rs.getString(4);
                ArrayList<String> idiomas = searchIdiomasById(idPais);
                ArrayList<String> monedas = searchMonedasById(idPais);

                Pais p = new Pais(nombre,capital,habitantes,idiomas, monedas);

                out.add(p);

            }

        } catch (SQLException e) {

        }

        return out;
    }

    private ArrayList<String> searchIdiomasById(int idPais ) {

        ArrayList<String> out = new ArrayList<>();

        try {
                                                                                        // TODO: es PAIS o ID_PAIS ??
            PreparedStatement ps = db.prepareStatement("SELECT NOMBRE FROM MONEDAS INNER JOIN MONEDAS_PAISES WHERE PAIS = ?");
            ResultSet rs;

            ps.setInt(idPais, 1);
            rs = ps.executeQuery();

            while (rs.next() ) {

                out.add(rs.getString(1));

            }

        } catch (SQLException ex ) {



        }

        return out;
    }

    private ArrayList<String> searchMonedasById(int idPais ) {

        ArrayList<String> out = new ArrayList<>();

        try {
                                                                                                // TODO: es PAIS o ID_PAIS ??
            PreparedStatement ps = db.prepareStatement("SELECT NOMBRE FROM IDIOMAS INNER JOIN IDIOMAS_PAISES WHERE PAIS = ?");
            ResultSet rs;

            ps.setInt(idPais, 1);
            rs = ps.executeQuery();

            while (rs.next() ) {

                out.add(rs.getString(1));

            }

        } catch (SQLException ex ) {



        }

        return out;
    }

}
