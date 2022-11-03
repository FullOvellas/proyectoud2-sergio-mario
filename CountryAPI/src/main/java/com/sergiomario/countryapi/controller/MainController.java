package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import com.sergiomario.countryapi.dao.Data;
import com.sergiomario.countryapi.dao.CountryFetcher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controlador de la vista del login
 */
public class MainController {

    @FXML
    Button btnDescarga;
    @FXML
    TextField txtUsuario;

    @FXML
    Label lblWrongPassword;

    @FXML
    PasswordField txtPassword;

    /**
     * Método para cuando se da click al botón de iniciar sesión. Comprueba las credenciales y pasa a mostrar
     * el menú si son correctas
     */
    @FXML
    protected void onLoginClick() {

        Data appData = Data.instance;

        if(txtUsuario.getText().equals(appData.getUser()) && txtPassword.getText().equals(appData.getPassword())) {

            // Volver a hacer invisible por si se vuelve a la pantalla de login
            lblWrongPassword.setVisible(false);

            Main.activate("menu");

        } else {

            lblWrongPassword.setVisible(true);

        }

    }

    @FXML
    private void onDescargarButtonClick() {

        CountryFetcher.fetch();
        btnDescarga.setDisable(true);

    }



}