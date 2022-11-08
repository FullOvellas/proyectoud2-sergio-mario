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

    public String getCredentials(String login ) {

        String credentials = null;

        try {

            PreparedStatement ps = db.prepareStatement("SELECT PASSWD FROM USERS WHERE LOGIN = ?");
            ResultSet rs;

            ps.setString(1, login);

            rs = ps.executeQuery();

            while (rs.next() ) {

                credentials = rs.getString(1);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return credentials;
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

            PreparedStatement ps = db.prepareStatement("SELECT ID_PAIS,NOMBRE,NUM_HABITANTES,CAPITAL FROM PAISES WHERE NOMBRE LIKE ?");

            searchStr = "%" + searchStr + "%";

            ps.setString(1, searchStr);
            out = parseCountries(ps.executeQuery());

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return out;
    }

    public ArrayList<Pais> searchByCurrency(String searchStr) {

        ArrayList<Pais> out = new ArrayList<>();

        try {

            PreparedStatement ps = db.prepareStatement("SELECT ID_PAIS,P.NOMBRE,NUM_HABITANTES,CAPITAL FROM PAISES AS P INNER JOIN MONEDAS_PAISES AS MP ON P.ID_PAIS = MP.PAIS INNER JOIN MONEDAS AS M ON MP.MONEDA = M.ID_MONEDA AND M.NOMBRE LIKE ?");

            searchStr = "%" + searchStr + "%";

            ps.setString(1, searchStr);
            out = parseCountries(ps.executeQuery());

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return out;
    }

    public ArrayList<Pais> searchByCapital(String searchStr) {
        ArrayList<Pais> out = new ArrayList<>();

        try {

            PreparedStatement ps = db.prepareStatement("SELECT ID_PAIS,NOMBRE,NUM_HABITANTES,CAPITAL FROM PAISES WHERE CAPITAL LIKE ?");

            searchStr = "%" + searchStr + "%";

            ps.setString(1, searchStr);
            out = parseCountries(ps.executeQuery());

        } catch (SQLException e) {

        }

        return out;
    }

    public ArrayList<Pais> searchByLanguage(String searchStr ) {
        ArrayList<Pais> out = new ArrayList<>();


        //TODO: acabar
        try {

            PreparedStatement ps = db.prepareStatement("SELECT ID_PAIS,NOMBRE,NUM_HABITANTES,CAPITAL  FROM PAISES WHERE CAPITAL LIKE ?");

            searchStr = "%" + searchStr + "%";

            ps.setString(1, searchStr);
            out = parseCountries(ps.executeQuery());

        } catch (SQLException e) {

        }

        return out;
    }

    private ArrayList<String> searchIdiomasById(int idPais ) {

        ArrayList<String> out = new ArrayList<>();

        try {
                                                                                        // TODO: es PAIS o ID_PAIS ??
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
                                                                                                // TODO: es PAIS o ID_PAIS ??
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

    public ArrayList<Pais> getRandomCountries(int quantity) {
        ArrayList<Pais> out = new ArrayList<>();

        try {

            PreparedStatement ps = db.prepareStatement("SELECT ID_PAIS,NOMBRE,NUM_HABITANTES,CAPITAL FROM PAISES ORDER BY RAND() LIMIT ?");

            ps.setInt(1, quantity);

            out = parseCountries(ps.executeQuery());

        } catch (SQLException ex ) {



        }

        return out;
    }

    public void registrarToken(String userToken, String ip, String userLogin) {

        try {

            String databaseToken = getUserToken(ip, userLogin);

            PreparedStatement psToken;

            if(databaseToken != null ) {

                // Token de usuario-IP existente -> borrar y crear uno nuevo
                psToken = db.prepareStatement("UPDATE TOKENS_USERS SET TOKEN = ? WHERE ID_USER = ? AND USER_IP = ?");

                psToken.setString(1, userToken);
                psToken.setInt(2, getUserId(userLogin));
                psToken.setString(3, ip);

            } else {

                // Token de usuario-IP existente -> borrar y crear uno nuevo
                psToken = db.prepareStatement("INSERT INTO TOKENS_USERS (ID_USER, USER_IP, TOKEN) VALUES (?, ?, ?)");

                psToken.setInt(1, getUserId(userLogin));
                psToken.setString(2, ip);
                psToken.setString(3, userToken);

            }
            psToken.execute();

        } catch (SQLException ex ) {

            System.out.println("Error al registrar token");

            ex.printStackTrace();

        }

    }

    public ArrayList<String> getIpTokens(String ip ) {

        ArrayList<String> out = new ArrayList<>();

        try {

            PreparedStatement ps = db.prepareStatement("SELECT TOKEN FROM TOKENS_USERS WHERE USER_IP = ?");
            ResultSet rs;

            ps.setString(1, ip);

            rs = ps.executeQuery();

            while(rs.next() ) {

                out.add(rs.getString(1));

            }

        } catch (SQLException ex ) {

        }

        return out;
    }

    public String getUserToken(String ip, String userLogin) {

        String out = null;

        try {

            PreparedStatement ps = db.prepareStatement("SELECT TOKEN FROM TOKENS_USERS WHERE USER_IP = ? AND ID_USER = ?");
            ResultSet rs;

            ps.setString(1, ip);
            ps.setInt(2, getUserId(userLogin));

            rs = ps.executeQuery();

            if(rs.next() ) {

                out = rs.getString(1);

            }

        } catch (SQLException ex ) {

            ex.printStackTrace();

        }

        return out;
    }

    public int getUserId(String login) {

        int userId = -1;

        try {

            PreparedStatement ps = db.prepareStatement("SELECT ID_USER FROM USERS WHERE LOGIN = ?");
            ResultSet rs;

            ps.setString(1, login);

            rs = ps.executeQuery();

            while (rs.next() ) {

                userId = rs.getInt(1);

            }

        } catch (SQLException ex ) {



        }

        return userId;
    }

}
