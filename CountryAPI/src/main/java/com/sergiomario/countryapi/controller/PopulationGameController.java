package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import com.sergiomario.countryapi.dao.CountryFetcher;
import com.sergiomario.countryapi.model.country.Country;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private Country countryLeft;
    @FXML
    private Country countryRight;
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

        Country[] countries = CountryFetcher.getRandomCountries(2);
        countryLeft = countries[0];
        countryRight = countries[1];

        correctButton = countries[0].getPopulation() > countries[1].getPopulation() ? lowerButton : higherButton;

        flagLeft.setImage(CountryFetcher.getFlag(countryLeft.getName()));
        flagRight.setImage(CountryFetcher.getFlag(countryRight.getName()));

        countryLeftLbl.setText(countries[0].getName());
        countryRightLbl.setText(countries[1].getName());

        populationLbl.setText("%,d".formatted(countries[0].getPopulation()));

        scoreLbl.setText("Puntuación: 0");
        highScoreLbl.setText("Récord: 0");

        score = 0;
        highScore = 0;

        higherButton.setGraphic(new ImageView(new Image("file:res/img/up-arrow.png")));
        lowerButton.setGraphic(new ImageView(new Image("file:res/img/down-arrow.png")));

    }

    public void checkAnswer(ActionEvent e) {

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
                    updateCountries();

                } else {

                    updateScore();
                    updateCountries();
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

    private void updateCountries() {

        countryLeft = countryRight;
        countryLeftLbl.setText(countryRightLbl.getText());
        flagLeft.setImage(flagRight.getImage());
        populationLbl.setText("%,d".formatted(countryLeft.getPopulation()));

        countryRight = CountryFetcher.getRandomCountries(1)[0];

        while (countryLeft.equals(countryRight)) {

            countryRight = CountryFetcher.getRandomCountries(1)[0];

        }

        flagRight.setImage(CountryFetcher.getFlag(countryRight.getName()));
        countryRightLbl.setText(countryRight.getName());

        correctButton = countryLeft.getPopulation() > countryRight.getPopulation() ? lowerButton : higherButton;

    }

    public void toMenu() {

        Main.activate("menu");

    }
}
