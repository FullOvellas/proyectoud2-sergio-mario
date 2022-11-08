package com.sergiomario.countryapi.dao;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;


import com.sergiomario.countryapi.model.country.Pais;
import javafx.scene.image.Image;


public class CountryFetcher {

    private CountryFetcher() {}

    public static ArrayList<Pais> searchCountriesByCapital(String searchStr ) {
        ArrayList<Pais> out = new ArrayList<>();

        try {

            Cliente.instance.enviar("SEARCH-CAPITAL-" + searchStr.length() + "-" +  searchStr + "-TOKEN-" + Cliente.instance.getToken());

            out = recibirPaises();

        } catch (SocketException ex ) {



        }

        return out;
    }

    public static ArrayList<Pais> searchCountriesByName(String searchStr ) {
        ArrayList<Pais> out = new ArrayList<>();

        try {

            Cliente.instance.enviar("SEARCH-NAME-" + searchStr.length() + "-" +  searchStr + "-TOKEN-" + Cliente.instance.getToken());

            out = recibirPaises();

        } catch (SocketException ex ) {



        }

        return out;
    }

    private static ArrayList<Pais> recibirPaises() throws SocketException {

        ArrayList<Pais> paises = new ArrayList<>();
        String data = Cliente.instance.recibir();

        if(data != null && !data.equals("ERROR") ) {

            try {

                byte[] b = Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));

                ByteArrayInputStream byteStream = new ByteArrayInputStream(b);
                ObjectInputStream objStream = new ObjectInputStream(byteStream);

                try {

                    while (true ) {

                        paises.add((Pais) objStream.readObject());

                    }

                } catch (EOFException  ex ) {

                }

                paises.sort(Comparator.comparingInt(country -> -country.getNumHabitantes()));

            } catch (IOException | ClassNotFoundException ex ) {

                ex.printStackTrace();

            }

        }

        return paises;
    }

    public static ArrayList<Pais> searchCountriesByCurrency(String searchStr ) {
        ArrayList<Pais> out = new ArrayList<>();

        try {

            Cliente.instance.enviar("SEARCH-CURRENCY-" + searchStr.length() + "-" +  searchStr + "-TOKEN-" + Cliente.instance.getToken());

            out = recibirPaises();

        } catch (SocketException ex ) {



        }

        return out;
    }

    public static ArrayList<Pais> searchCountriesByLanguage(String searchStr ) {
        ArrayList<Pais> out = new ArrayList<>();

        try {

            Cliente.instance.enviar("SEARCH-LANG-" + searchStr.length() + "-" +  searchStr + "-TOKEN-" + Cliente.instance.getToken());

            out = recibirPaises();

        } catch (SocketException ex ) {



        }

        return out;
    }

    public static Pais[] getRandomCountries(int quantity) {

        Pais[] out = new Pais[0];

        try {

            Cliente.instance.enviar("RAND-" + quantity + "-TOKEN-" + Cliente.instance.getToken());

            out = recibirPaises().toArray(Pais[]::new);

        } catch (SocketException ex ) {

        }


        return out;

    }

    public static Image getFlag(String name) {

        String path = "file:res/img/%s.png";

        return new Image(String.format(path, name));

    }

}
