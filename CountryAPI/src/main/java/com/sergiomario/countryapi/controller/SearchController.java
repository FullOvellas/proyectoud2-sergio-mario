package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import com.sergiomario.countryapi.dao.CountryFetcher;
import com.sergiomario.countryapi.model.country.Country;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controlador de la vista "search" que se ocupa de las consultas a la API
 */
public class SearchController implements Initializable {

    private ArrayList<Country> lastResult;
    private int viewIndex;

    @FXML
    private Button btnAnterior;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Label lblNoEncontrado;
    @FXML
    private ImageView imgBandera;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPoblacion;
    @FXML
    private TextField txtMonedas;
    @FXML
    private TextField txtCapital;
    @FXML
    private TextField txtIdiomas;
    @FXML
    private ChoiceBox<String> cbCampoBusqueda;
    @FXML
    private TableView<Country> resultsTbl;

    /**
     * Método para cuando se da click al botón de salir al menú. Se volverá a la vista del menú
     */
    @FXML
    private void onExitButtonClick() {

        Main.activate("menu");

    }

    /**
     * Método llamado para buscar un país.
     */
    @FXML
    private void onBotonBuscarClick() {

        String rawBusqueda = txtBusqueda.getText();
        int modoBusqueda = cbCampoBusqueda.getSelectionModel().getSelectedIndex();

        clearText();
        btnSiguiente.setDisable(true);
        btnAnterior.setDisable(true);

        if(!rawBusqueda.equals("") ) {

            if(modoBusqueda == 0 ) {

                lastResult = CountryFetcher.searchCountriesByName(rawBusqueda);
                resultsTbl.setItems(FXCollections.observableArrayList(lastResult));


            } else if(modoBusqueda == 1 ) {

                lastResult = CountryFetcher.searchCountriesByLanguage(rawBusqueda);

            } else if(modoBusqueda == 2){

                lastResult = CountryFetcher.searchCountriesByCurrency(rawBusqueda);

            } else {

                lastResult = CountryFetcher.searchCountriesByCapital(rawBusqueda);

            }

            viewIndex = 0;

            if(lastResult.size() > 1 ) {

                btnSiguiente.setDisable(false);

            }

            if(!lastResult.isEmpty() ) {

                showCountry(lastResult.get(0));

            } else {

                lblNoEncontrado.setVisible(true);

            }

        }

    }

    /**
     * Método para mostrar el país siguiente del resultado de la búsqueda
     */
    @FXML
    private void onSiguienteButtonClick() {

        viewIndex++;

        if(viewIndex < lastResult.size() ) {


            showCountry(lastResult.get(viewIndex));

            btnAnterior.setDisable(false);

            if(viewIndex == lastResult.size() -1 ) {

                btnSiguiente.setDisable(true);

            }

        } else {

            viewIndex--;

        }

    }

    /**
     * Método para mostrar el país anterior del resultado de la búsqueda
     */
    @FXML
    private void onAnteriorButtonClick() {

        if(viewIndex > 0 ) {

            viewIndex--;
            showCountry(lastResult.get(viewIndex));

            btnSiguiente.setDisable(false);

            if(viewIndex == 0 ) {

                btnAnterior.setDisable(true);

            }

        }

    }

    /**
     * Nétodo para mostrar la información de un país
     * @param c el país a mostrar
     */
    private void showCountry(Country c ) {

        txtNombre.setText(c.getName());
        txtCapital.setText(c.getCapital());
        imgBandera.setImage(new Image(c.getFlags().getPng()));

        txtPoblacion.setText("%,d".formatted(c.getPopulation()));

        if(CountryFetcher.isConnected() ) {

            imgBandera.setImage(new Image(c.getFlags().getPng()));

        } else {

            imgBandera.setImage(new Image("file:res/img/%s.png".formatted(c.getName())));

        }

        if(c.getCurrencies() != null ) {

            StringBuilder strMonedas = new StringBuilder("");

            for(int i = 0; i < c.getCurrencies().size(); i++ ) {

                strMonedas.append(c.getCurrencies().get(i).getCode());

                if(i != c.getCurrencies().size() - 1 ) {

                    strMonedas.append(", ");

                }

            }

            txtMonedas.setText(strMonedas.toString());

        }

        if(c.getLanguages() != null ) {

            StringBuilder strIdiomas = new StringBuilder("");

            for(int i = 0; i < c.getLanguages().size(); i++ ) {

                strIdiomas.append(c.getLanguages().get(i).getName());
                strIdiomas.append("(");
                strIdiomas.append(c.getLanguages().get(i).getIso6391());
                strIdiomas.append(")");

                if(i != c.getLanguages().size() - 1 ) {

                    strIdiomas.append(", ");

                }

            }

            txtIdiomas.setText(strIdiomas.toString());

        }

    }

    /**
     * Método para limpiar todos los campos de información
     */
    private void clearText() {

        txtNombre.setText("");
        txtCapital.setText("");
        txtMonedas.setText("");
        txtPoblacion.setText("");
        txtIdiomas.setText("");
        imgBandera.setImage(null);
        lblNoEncontrado.setVisible(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbCampoBusqueda.getItems().add("Nombre");
        cbCampoBusqueda.getItems().add("Idioma");
        cbCampoBusqueda.getItems().add("Moneda");
        cbCampoBusqueda.getItems().add("Capital");

        TableColumn<Country, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Country, Integer> popCol = new TableColumn<>("Población");
        popCol.setCellValueFactory(new PropertyValueFactory<>("population"));

        resultsTbl.getColumns().addAll(nameCol, popCol);
        resultsTbl.getSortOrder().add(popCol);
        popCol.setPrefWidth(196.0);
        nameCol.setPrefWidth(196.0);
        popCol.setSortable(false);
        nameCol.setSortable(false);

        lastResult = new ArrayList<>();

        cbCampoBusqueda.getSelectionModel().select(0);

    }

}
