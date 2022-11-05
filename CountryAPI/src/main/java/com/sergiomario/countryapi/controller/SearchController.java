package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import com.sergiomario.countryapi.dao.CountryFetcher;
import com.sergiomario.countryapi.model.country.Country;
import com.sergiomario.countryapi.model.country.Pais;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controlador de la vista "search" que se ocupa de las consultas a la API
 */
public class SearchController implements Initializable {

    private ArrayList<Pais> lastResult;
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
    private TableView<Pais> resultsTbl;

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

            } else if(modoBusqueda == 1 ) {

                // lastResult = CountryFetcher.searchCountriesByLanguage(rawBusqueda);

            } else if(modoBusqueda == 2){

                lastResult = CountryFetcher.searchCountriesByCurrency(rawBusqueda);

            } else {

                // lastResult = CountryFetcher.searchCountriesByCapital(rawBusqueda);

            }

            resultsTbl.setItems(FXCollections.observableArrayList(lastResult));

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

    @FXML
    private void onTableClick(MouseEvent event) {

        if(event.getClickCount() == 2 ) {

            int index = resultsTbl.getSelectionModel().getSelectedIndex();

            showCountry(lastResult.get(index));

            btnSiguiente.setDisable(false);
            btnAnterior.setDisable(false);

            btnSiguiente.setDisable( index == (lastResult.size() - 1) );
            btnAnterior.setDisable( index == 0);

            viewIndex = index;

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
    private void showCountry(Pais c ) {

        txtNombre.setText(c.getNombre());
        txtCapital.setText(c.getCapital());

        // TODO: imgBandera.setImage(new Image(c.getFlags().getPng()));

        txtPoblacion.setText("%,d".formatted(c.getNumHabitantes()));

        StringBuilder strMonedas = new StringBuilder("");

        for(int i = 0; i < c.getMonedas().size(); i++ ) {

            strMonedas.append(c.getMonedas().get(i));

            if(i != c.getMonedas().size() - 1 ) {

                strMonedas.append(", ");

            }

        }

        txtMonedas.setText(strMonedas.toString());

        imgBandera.setImage(CountryFetcher.getFlag(c.getNombre()));

        StringBuilder strIdiomas = new StringBuilder("");

        for(int i = 0; i < c.getIdiomas().size(); i++ ) {

            strIdiomas.append(c.getIdiomas().get(i));

            if(i != c.getIdiomas().size() - 1 ) {

                strIdiomas.append(", ");

            }

        }

        txtIdiomas.setText(strIdiomas.toString());

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

        TableColumn<Pais, String> nameCol = new TableColumn<>("Nombre");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Pais, Integer> popCol = new TableColumn<>("Población");
        popCol.setCellValueFactory(new PropertyValueFactory<>("numHabitantes"));
        NumberFormat formatNum = NumberFormat.getNumberInstance(Locale.getDefault());

        popCol.setCellFactory(tc -> new TableCell<Pais, Integer>() {

            @Override
            protected void updateItem(Integer pop, boolean empty) {
                super.updateItem(pop, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(formatNum.format(pop));
                }
            }
        });

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
