package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * Controlador de la vista del menú
 */
public class MenuController {

    /**
     * Método para cuando se da click al botón para la opción de búsqueda. Pasará a verse la vista de
     * búsqueda
     */
    @FXML
    protected void onBotonBusquedaClick() {

        Main.activate("search");

    }


    /**
     * Método para cuando se da click al botón para la opción del juego de las banderas. Pasará a verse la vista del
     * juego de las banderas
     */
    @FXML
    protected void onBotonBanderasClick() {

        Main.activate("flags");

    }

    /**
     * Método para cuando se da click al botón de salir al login. Se volverá a la vista del login
     */
    @FXML
    protected void onBotonSalidaClick() {

        Main.activate("main");

    }


    @FXML
    public void onBotonPoblacionesClick() {

        Main.activate("populations");

    }
}
