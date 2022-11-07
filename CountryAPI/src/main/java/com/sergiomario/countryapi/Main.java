package com.sergiomario.countryapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Main extends Application {


    private static HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene main;

    /**
     * Método para mostrar una pantalla de entre las pantallas disponibles
     * @param name el nombre de la pantalla disponible
     */
    public static void activate(String name){
    
       main.setRoot( screenMap.get(name) );
   
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        main = new Scene(fxmlLoader.load());

        try {

            // Añadir pantalla de búsqueda a las pantallas disponibles
            screenMap.put("search", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search-view.fxml"), "No se pudo cargar la vista de búsqueda")));

            // Pantalla de main
            screenMap.put("main", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml"), "No se pudo cargar la vista main")));

            // Pantalla de menú
            screenMap.put("menu", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu-view.fxml"), "No se pudo cargar la vista del menú")));

            // Pantalla de xogo bandeiras
            screenMap.put("flags", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("flagGame-view.fxml"), "No se pudo cargar la vista del juego de banderas")));

            // Pantalla de xogo poboacións
            screenMap.put("populations", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("populationGame-view.fxml"), "No se pudo cargar la vista del juego de poblaciones")));

        } catch (IOException ex ) {


            ex.printStackTrace();

        }

        stage.setTitle("Country BBDD");
        stage.setScene(main);
        stage.show();

    }

    public static void main(String[] args) {

        Main.launch();

    }

}