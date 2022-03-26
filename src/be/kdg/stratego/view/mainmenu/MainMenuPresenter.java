package be.kdg.stratego.view.mainmenu;

import be.kdg.stratego.model.Highscore;
import be.kdg.stratego.model.Lowturn;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.help.HelpPresenter;
import be.kdg.stratego.view.help.HelpView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainMenuPresenter {
    private final ProgrammaModel model;
    private final MainMenuView view;

    private boolean toggler;
    private Timeline timelineWinners;

    public MainMenuPresenter(ProgrammaModel model, MainMenuView view) {
        this.model = model;
        this.view = view;
        this.winnerTimelineSetup();
        this.addEventHandlers();
        this.updateView();
    }

    private void winnerTimelineSetup() {
        toggler = false;

        timelineWinners = new Timeline();
        timelineWinners.setCycleCount(Animation.INDEFINITE);
        timelineWinners.getKeyFrames().add(
                new KeyFrame(Duration.seconds(5), event -> {
                    toggler = !toggler;
                    updateView();
                }));
        timelineWinners.play();
    }

    private void addEventHandlers() {
        view.getBtnPlay().setOnAction(actionEvent -> Style.changeScreen(Style.Screens.NEWGAME, model, view));

        view.getBtnHelp().setOnAction(actionEvent -> {
            HelpView helpView = new HelpView();
            HelpPresenter helpPresenter = new HelpPresenter(model, helpView);

            Stage stage = new Stage();
            stage.setScene(new Scene(helpView));

            stage.setTitle("How to play Stratego");
            stage.initOwner(view.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            helpPresenter.addWindowEventHandlers();

            stage.showAndWait();
        });
    }

    private void updateView() {
        // Winners
        try {
            int positie = 0;
            model.updateWinners();
            view.getGpWinners().getChildren().clear();

            if (toggler) {
                ///Highscores
                view.getLblHighscores().setText("Highscores");

                for (Highscore highscore : model.getHighscores()) {
                    positie++;
                    if (positie <= 10) {
                        Label lblPositie = new Label(Integer.toString(positie));
                        Label lblName = new Label(highscore.getSpelernaam());
                        Label lblScore = new Label(Integer.toString(highscore.getScore()));

                        fillGpWinners(positie, lblPositie, lblName, lblScore);
                    } else {
                        return;
                    }
                }
            } else {
                ///Lowturns
                view.getLblHighscores().setText("Lowturns");

                for (Lowturn lowturn : model.getLowturns()) {
                    positie++;
                    if (positie <= 10) {
                        Label lblPositie = new Label(Integer.toString(positie));
                        Label lblName = new Label(lowturn.getSpelernaam());
                        Label lblTurns = new Label(Integer.toString(lowturn.getTurns()));

                        fillGpWinners(positie, lblPositie, lblName, lblTurns);
                    } else {
                        return;
                    }
                }
            }
        } catch (IOException e) {
            view.getLblHighscores().setText("No file was found");
        }
    }

    public void addWindowEventHandlers() {
        Stage stage = (Stage) view.getScene().getWindow();
        view.getBtnQuit().setOnAction(actionEvent -> stage.close());
    }

    private void fillGpWinners(int positie, Label lblPositie, Label lblName, Label lblExtra) {
        Style.txt(lblPositie, 10);
        Style.txt(lblName, 10);
        Style.txt(lblExtra, 10);

        GridPane.setHalignment(lblPositie, HPos.CENTER);
        GridPane.setHalignment(lblExtra, HPos.RIGHT);

        view.getGpWinners().add(lblPositie, 0, positie);
        view.getGpWinners().add(lblName, 1, positie);
        view.getGpWinners().add(lblExtra, 2, positie);
    }
}
