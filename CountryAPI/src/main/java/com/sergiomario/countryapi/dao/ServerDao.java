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

        }

    }

    private ArrayList<Pais> parseCountries(ResultSet rs) throws SQLException {

        ArrayList<Pais> out = new ArrayList<>();

        while (rs.next()) {

            int idPais = rs.getInt(1);
            String nombre = rs.getString(2);
            int habitantes = rs.getInt(3);
            String capital = rs.getString(4);
            ArrayList<String> idiomas = searchIdiomasById(idPais);
            ArrayList<String> monedas = searchMonedasById(idPais);

            Pais p = new Pais(nombre, capital, habitantes, idiomas, monedas);

            out.add(p);

        }

        return out;

    }

    public ArrayList<Pais> searchByName(String searchStr) {

        ArrayList<Pais> out = new ArrayList<>();

        try {

            PreparedStatement ps = db.prepareStatement("SELECT ID_PAIS,NOMBRE,NUM_HABITANTES,CAPITAL  FROM PAISES WHERE NOMBRE LIKE ?");

            ps.setString(1, "%" + searchStr + "%");
            out = parseCountries(ps.executeQuery());

        } catch (SQLException ex) {

            System.out.println("Erro na busca por nome");

        }

        return out;
    }

    public ArrayList<Pais> searchByCurrency(String searchStr) {

        ArrayList<Pais> out = new ArrayList<>();

        try {

            PreparedStatement ps = db.prepareStatement("SELECT ID_PAIS,P.NOMBRE,NUM_HABITANTES,CAPITAL FROM PAISES AS P INNER JOIN MONEDAS_PAISES AS MP ON P.ID_PAIS = MP.PAIS INNER JOIN MONEDAS AS M ON MP.MONEDA = M.ID_MONEDA AND M.NOMBRE = ?");

            ps.setString(1, "%" + searchStr + "%");
            out = parseCountries(ps.executeQuery());

        } catch (SQLException ex) {

            System.out.println("Erro na busca por nome");

        }

        return out;
    }

    private ArrayList<String> searchIdiomasById(int idPais ) {

        ArrayList<String> out = new ArrayList<>();

        try {

            PreparedStatement ps = db.prepareStatement("SELECT NOMBRE FROM IDIOMAS AS I INNER JOIN IDIOMAS_PAISES AS IP ON I.ID_IDIOMA = IP.IDIOMA AND IP.PAIS = ?");
            ResultSet rs;

            ps.setInt( 1, idPais);
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
            
            PreparedStatement ps = db.prepareStatement("SELECT NOMBRE FROM MONEDAS AS M INNER JOIN MONEDAS_PAISES AS MP ON M.ID_MONEDA = MP.MONEDA AND MP.PAIS = ?");
            ResultSet rs;

            ps.setInt( 1, idPais);
            rs = ps.executeQuery();

            while (rs.next() ) {

                out.add(rs.getString(1));

            }

        } catch (SQLException ex ) {


        }

        return out;
    }

}
