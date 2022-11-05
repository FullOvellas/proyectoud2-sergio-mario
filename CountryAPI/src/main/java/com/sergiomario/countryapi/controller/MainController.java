package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import com.sergiomario.countryapi.dao.Cliente;
import com.sergiomario.countryapi.dao.CountryFetcher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.SocketException;

/**
 * Controlador de la vista del login
 */
public class MainController {

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

        try {

            String token = Cliente.instance.enviarCredenciales(txtUsuario.getText(), txtPassword.getText());

            if(!token.equals("ERROR")) {

                // Volver a hacer invisible por si se vuelve a la pantalla de login
                lblWrongPassword.setVisible(false);

                Main.activate("menu");

            } else {

                lblWrongPassword.setText("Usuario o contraseña incorrectos");
                lblWrongPassword.setVisible(true);

            }

        } catch (SocketException ex ) {

            lblWrongPassword.setText("No se pudo conectar con el servidor");
            lblWrongPassword.setVisible(true);

        }

    }

}