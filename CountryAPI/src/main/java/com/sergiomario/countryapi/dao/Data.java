package com.sergiomario.countryapi.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;

/**
 * Singleton para manejar el guardado y cargado de datos de la aplicación
 */
public class Data {

    public static Data instance;

    private Path propertiesFilePath;
    private String user;
    private String password;

    static {

        instance = new Data();

    }

    private Data() {

        try {

            propertiesFilePath = Path.of("res","properties.txt");
            loadCredentials();

        } catch (NullPointerException ex ) {

            user = "admin";
            password = "renaido";

        }

    }

    /**
     * Getter para obtener el usuario de login de la aplicación.
     * @return el login cargado del archivo de propiedades o "admin" por defecto
     */
    public String getUser() {
        return user;
    }

    /**
     * Getter para obtener la contraseña de login de la aplicación.
     * @return la contraseña del archivo de propiedades o "renaido" por defecto
     */
    public String getPassword() {
        return password;
    }

    /**
     * Método para cargar el usuario y la contraseña del archivo de propiedades.
     */
    private void loadCredentials() {

        try (BufferedReader reader = new BufferedReader(new FileReader(propertiesFilePath.toFile()))) {

            String[] lines = reader.lines().toArray(String[]::new);

            if( lines.length >= 2 && lines[0].startsWith("User:") && lines[1].startsWith("Password:") ) {

                // Checks para comprobar que:
                // - lines[0] tiene más contenido que solo "User:"
                // - lines[1] tiene más contenido que solo "Password:"
                if(lines[0].length() > 5 && lines[1].length() > 9) {

                    String rawUserString = lines[0].replace("User:", "");
                    String rawPasswordString = lines[1].replace("Password:", "");

                    user = new String(Base64.getDecoder().decode(rawUserString));
                    password = new String(Base64.getDecoder().decode(rawPasswordString));

                }

            }

        } catch (IOException ex ) {

        }

    }

}
