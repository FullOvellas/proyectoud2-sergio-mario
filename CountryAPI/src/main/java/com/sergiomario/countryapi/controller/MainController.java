package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import com.sergiomario.countryapi.dao.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.SocketException;

/**
 * Controlador de la vista del login
 */
public class MainController {

    private boolean ipConfigurada = false;

    @FXML
    TextField txtDireccion;

    @FXML
    TextField txtUsuario;

    @FXML
    Label lblWrongPassword;

    @FXML
    PasswordField txtPassword;

    @FXML
    private void onCkeckAddressClick() {

        String ip = txtDireccion.getText();
        boolean encontrada = Cliente.instance.configurarConexion(ip);

        if(encontrada) {

            ipConfigurada = true;
            txtDireccion.setStyle("-fx-control-inner-background: #416d31");

        } else {

            txtDireccion.setText("");
            txtDireccion.setStyle("-fx-control-inner-background: #FF0000");

        }

    }

    /**
     * Método para cuando se da click al botón de iniciar sesión. Comprueba las credenciales y pasa a mostrar
     * el menú si son correctas
     */
    @FXML
    protected void onLoginClick() {

        if(ipConfigurada ) {

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

        } else {

            lblWrongPassword.setText("Introduce la IP del server");
            lblWrongPassword.setVisible(true);

        }

    }

}