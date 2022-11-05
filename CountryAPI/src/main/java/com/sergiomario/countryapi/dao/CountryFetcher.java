package com.sergiomario.countryapi.dao;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.sergiomario.countryapi.model.country.Country;
import com.sergiomario.countryapi.model.country.CurrenciesItem;
import com.sergiomario.countryapi.model.country.LanguagesItem;

import com.sergiomario.countryapi.model.country.Pais;
import javafx.scene.image.Image;


public class CountryFetcher {

    private static ArrayList<Country> cachedCountries;
    private static boolean hasConnection;

    private CountryFetcher() {}

    public static void init() {

        Cliente client = Cliente.instance;

        cachedCountries = new ArrayList<>();

        client.configurarConexion("127.0.0.1"); // TODO: TEMP

    }

    public static ArrayList<Country> searchCountriesByCapital(String searchStr ) {
        ArrayList<Country> out;

        if(hasConnection ) {

            out = genericSearch("capital", searchStr);

        } else {

            out = new ArrayList<>();

            cachedCountries.forEach(country -> {

                if(country.getCapital() != null ) {

                    if(country.getCapital().matches(searchStr) || country.getCapital().contains(searchStr ) ) {

                        out.add(country);

                    }

                }

            });

        }

        out.sort(Comparator.comparingInt(country -> -country.getPopulation()));

        return out;
    }

    public static ArrayList<Pais> searchCountriesByName(String searchStr ) {
        ArrayList<Pais> out = new ArrayList<>();

        Cliente.instance.enviar("SEARCH-NAME-" + searchStr.length() + "-" +  searchStr + "-" + Cliente.instance.getToken());

        try {

            String data = Cliente.instance.recibir();

            if(data != null && !data.equals("ERROR") ) {

                try {

                    byte[] b = Base64.getDecoder().decode(data.getBytes());

                    ByteArrayInputStream byteStream = new ByteArrayInputStream(b);
                    ObjectInputStream objStream = new ObjectInputStream(byteStream);

                    out = (ArrayList<Pais>) objStream.readObject();

                    out.sort(Comparator.comparingInt(country -> -country.getNumHabitantes()));

                } catch (IOException | ClassNotFoundException ex ) {

                }

            }

        } catch (SocketException ex ) {



        }

        return out;
    }

    public static ArrayList<Country> searchCountriesByCurrency(String searchStr ) {
        ArrayList<Country> out;

        if(hasConnection ) {

            out = genericSearch("currency", searchStr);

        } else {

            out = new ArrayList<>();

            cachedCountries.forEach(country -> {

                boolean hasCurrency = false;

                if(country.getCurrencies() != null ) {

                    for(CurrenciesItem currency : country.getCurrencies() ) {

                        if(currency.getName().toLowerCase().matches(searchStr.toLowerCase() ) || currency.getCode().toLowerCase().matches(searchStr.toLowerCase()) ) {

                            hasCurrency = true;

                        }

                    }

                    if(hasCurrency ) {

                        out.add(country);

                    }

                }

            });

        }

        out.sort(Comparator.comparingInt(country -> -country.getPopulation()));

        return out;
    }

    public static ArrayList<Country> searchCountriesByLanguage(String searchStr ) {

        ArrayList<Country> out;

        if(hasConnection ) {

            out = genericSearch("lang", searchStr);

        } else {

            out = new ArrayList<>();

            cachedCountries.forEach(country -> {

                boolean hasLanguage = false;

                for(LanguagesItem lang : country.getLanguages() ) {

                    if(lang.getIso6391() != null && lang.getIso6391().toLowerCase().matches(searchStr.toLowerCase()) ) {

                        hasLanguage = true;

                    } else if(lang.getName() != null && lang.getName().toLowerCase().matches(searchStr.toLowerCase()) ) {

                        hasLanguage = true;

                    }

                }

                if(hasLanguage ) {

                    out.add(country);

                }

            });

        }

        out.sort(Comparator.comparingInt(country -> -country.getPopulation()));

        return out;
    }

    private static ArrayList<Country> genericSearch(String urlString, String search ) {

        ArrayList<Country> out = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();


        try {

            URI url = new URI("https", "//restcountries.com/v2/"+ urlString + "/" + search, null);

            BufferedReader br = new BufferedReader(new InputStreamReader(url.toURL().openStream()));

            String json = br.readLine();
            out = objectMapper.readValue(json, new TypeReference<>() {});

        } catch (URISyntaxException | IOException e) {

            System.out.println("No se encontró la URL");

        }

        return out;

    }

    private static ArrayList<Country> loadFromFile(Path file) {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Country> out = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(file.toFile()));

            String json = br.readLine();

            out = objectMapper.readValue(json, new TypeReference<>() {});

        } catch (IOException e) {

        }

        return out;
    }

    public static Country[] getRandomCountries(int countryNum) {

        Country[] countries = new Country[countryNum];
        ArrayList<Country> countrShuffle;
        String json = "";
        ObjectMapper om = new ObjectMapper();

        try (BufferedReader br = Files.newBufferedReader(Path.of("res/CountryCache.json"))) {

            json = br.readLine();

        } catch (IOException e) {

            e.printStackTrace();

        }

        try {
            countrShuffle = om.readValue(json, new TypeReference<>() {});
            Collections.shuffle(countrShuffle);

            for (int i = 0; i < countryNum; i++) {

                countries[i] = countrShuffle.get(i);

            }

        } catch (JsonProcessingException e) {

            throw new RuntimeException(e);

        }

        return countries;

    }

    public static Image getFlag(Country c) {

        String path = "file:res/img/%s.png";

        return new Image(String.format(path, c.getName()));

    }

}
