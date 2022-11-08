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

    private static Scene main;

    /**
     * Método para mostrar una pantalla de entre las pantallas disponibles
     * @param name el nombre de la pantalla disponible
     */
    public static void activate(String name){

        try {

            switch (name ) {
                case "main":
                    main.setRoot(FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("main-view.fxml"))));
                    break;
                case "menu":
                    main.setRoot(FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("menu-view.fxml"))));
                    break;
                case "flags":
                    main.setRoot(FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("flagGame-view.fxml"))));
                    break;
                case "populations":
                    main.setRoot(FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("populationGame-view.fxml"))));
                    break;
                case "search":
                    main.setRoot(FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("search-view.fxml"))));
                    break;
            }

        } catch (IOException ex ) {

            System.out.println("ERROR");

        }
   
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        main = new Scene(fxmlLoader.load());

        stage.setTitle("Country BBDD");
        stage.setScene(main);
        stage.show();

    }

    public static void main(String[] args) {

        Main.launch();

    }

}