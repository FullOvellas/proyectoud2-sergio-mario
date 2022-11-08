package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import com.sergiomario.countryapi.dao.CountryFetcher;
import com.sergiomario.countryapi.dao.ServerDao;
import com.sergiomario.countryapi.model.country.Pais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class PopulationGameController {

    private Button correctButton;
    @FXML
    private Button higherButton;
    @FXML
    private Button lowerButton;
    @FXML
    private ImageView flagRight;
    @FXML
    private ImageView flagLeft;
    @FXML
    private Pais countryLeft;
    @FXML
    private Pais countryRight;
    @FXML
    private Label countryRightLbl;
    @FXML
    private Label countryLeftLbl;
    @FXML
    private Label populationLbl;
    @FXML
    private Label scoreLbl;
    @FXML
    private Label highScoreLbl;
    private int score;
    private int highScore;

    public void initialize() {

        Pais[] countries = CountryFetcher.getRandomCountries(2);
        countryLeft = countries[0];
        countryRight = countries[1];

        correctButton = countries[0].getNumHabitantes() > countries[1].getNumHabitantes() ? lowerButton : higherButton;

        flagLeft.setImage(CountryFetcher.getFlag(countryLeft.getNombre()));
        flagRight.setImage(CountryFetcher.getFlag(countryRight.getNombre()));

        countryLeftLbl.setText(countries[0].getNombre());
        countryRightLbl.setText(countries[1].getNombre());

        populationLbl.setText("%,d".formatted(countries[0].getNumHabitantes()));

        scoreLbl.setText("Puntuación: 0");
        highScoreLbl.setText("Récord: 0");

        score = 0;
        highScore = 0;

        higherButton.setGraphic(new ImageView(new Image("file:res/img/up-arrow.png")));
        lowerButton.setGraphic(new ImageView(new Image("file:res/img/down-arrow.png")));

    }

    public void checkAnswer(ActionEvent e) throws SQLException {

        if (e.getSource().equals(correctButton)) {

            ++score;
            updateScore();

            updateCountries();

        } else {

            score = 0;

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setContentText("Continuar?");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);

            dialog.showAndWait().ifPresent(response -> {

                if (response == ButtonType.YES) {

                    updateScore();
                    try {
                        updateCountries();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else {

                    updateScore();
                    try {
                        updateCountries();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    toMenu();

                }

            });

        }

    }

    public void updateScore() {

        scoreLbl.setText("Puntuación: " + score);

        if (score > highScore) {

            highScore = score;
            highScoreLbl.setText("Récord: " + highScore);

        }

    }

    private void updateCountries() throws SQLException {

        countryLeft = countryRight;
        countryLeftLbl.setText(countryRightLbl.getText());
        flagLeft.setImage(flagRight.getImage());
        populationLbl.setText("%,d".formatted(countryLeft.getNumHabitantes()));

        countryRight = CountryFetcher.getRandomCountries(1)[0];

        while (countryLeft.equals(countryRight)) {

            countryRight = CountryFetcher.getRandomCountries(1)[0];

        }

        flagRight.setImage(CountryFetcher.getFlag(countryRight.getNombre()));
        countryRightLbl.setText(countryRight.getNombre());

        correctButton = countryLeft.getNumHabitantes() > countryRight.getNumHabitantes() ? lowerButton : higherButton;

    }

    public void toMenu() {

        Main.activate("menu");

    }
}
