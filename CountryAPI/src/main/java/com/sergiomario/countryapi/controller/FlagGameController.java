package com.sergiomario.countryapi.controller;

import com.sergiomario.countryapi.Main;
import com.sergiomario.countryapi.dao.CountryFetcher;
import com.sergiomario.countryapi.model.country.Country;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.Random;

public class FlagGameController {

    private Button correctButton;
    private int score = 0;
    private int highScore = 0;

    @FXML
    public ImageView flag;

    @FXML
    public Button optBtn1;

    @FXML
    public Button optBtn2;

    @FXML
    public Button optBtn3;
    @FXML
    public Label scoreLbl;
    @FXML
    public Label highScoreLbl;

    @FXML
    public void initialize() {
        setCurrentCountries();
    }

    @FXML
    public void checkAnswer(ActionEvent actionEvent) {

        String correctColor = "#78f59b";
        String wrongColor =   "#f5788d";
        Button b = (Button) actionEvent.getSource();

        if (b.equals(correctButton)) {

            correctButton.setStyle("-fx-background-color: " + correctColor);
            score++;
            updateScore();

            PauseTransition pause = new PauseTransition(Duration.seconds(1.0));
            pause.setOnFinished(event -> {
                setCurrentCountries();
            });
            pause.play();

        } else {

            b.setStyle("-fx-background-color: " + wrongColor);
            correctButton.setStyle("-fx-background-color: " + correctColor);

            PauseTransition pause = new PauseTransition(Duration.seconds(1.0));
            pause.play();

            score = 0;

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setContentText("Continuar?");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);

            dialog.showAndWait().ifPresent(response -> {

                if (response == ButtonType.YES) {

                    updateScore();
                    setCurrentCountries();

                } else {

                    updateScore();
                    setCurrentCountries();
                    toMenu();

                }

            });

        }

    }

    public void setCurrentCountries() {

        String defaultColor = "#b5b3b3";

        Random random = new Random();
        int correct = random.nextInt(3);
        optBtn1.setStyle("-fx-background-color:" + defaultColor);
        optBtn2.setStyle("-fx-background-color:" + defaultColor);
        optBtn3.setStyle("-fx-background-color:" + defaultColor);

        // sleep?

        Country[] countries = CountryFetcher.getRandomCountries(3);

        switch (correct) {

            case 0 -> {
                correctButton = optBtn1;
                flag.setImage(CountryFetcher.getFlag(countries[0]));
            }
            case 1 -> {
                correctButton = optBtn2;
                flag.setImage(CountryFetcher.getFlag(countries[1]));
            }
            case 2 -> {
                correctButton = optBtn3;
                flag.setImage(CountryFetcher.getFlag(countries[2]));
            }
        }

        optBtn1.setText(countries[0].getName());
        optBtn2.setText(countries[1].getName());
        optBtn3.setText(countries[2].getName());

    }

    public void updateScore() {

        scoreLbl.setText("Puntuación: " + score);

        if (score > highScore) {

            highScore = score;
            highScoreLbl.setText("Récord: " + highScore);

        }

    }

    @FXML
    private void toMenu() {

        Main.activate("menu");

    }

}
