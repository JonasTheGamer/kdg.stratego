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
    private ProgrammaModel model;
    private MainMenuView view;

    private boolean togglerWinners;

    public MainMenuPresenter(ProgrammaModel model, MainMenuView view) {
        this.model = model;
        this.view = view;
        this.winnerTimelineSetup();
        this.addEventHandlers();
        this.updateView();
    }

    private void winnerTimelineSetup() {
        Timeline timelineWinners;
        togglerWinners = false;

        timelineWinners = new Timeline();
        timelineWinners.setCycleCount(Animation.INDEFINITE);
        timelineWinners.getKeyFrames().add(
                new KeyFrame(Duration.seconds(5), event -> {
                    togglerWinners = !togglerWinners;
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
            model.updateWinners();
            view.getGpWinners().getChildren().clear();

            int position = 0;
            if (togglerWinners) {
                ///Highscores
                view.getLblHighscores().setText("Highscores");

                for (Highscore highscore : model.getHighscores()) {
                    position++;
                    String name = highscore.getSpelernaam();
                    int score = highscore.getScore();

                    fillGpWinners(position, name, score);
                }
            } else {
                ///Lowturns
                view.getLblHighscores().setText("Lowturns");

                for (Lowturn lowturn : model.getLowturns()) {
                    position++;
                    String name = lowturn.getSpelernaam();
                    int turns = lowturn.getTurns();

                    fillGpWinners(position, name, turns);
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

    private void fillGpWinners(int position, String name, int value) {
        //Add scores to Gridpane
        if (position <= 10) {
            Label lblPositie = new Label(Integer.toString(position));
            Label lblName = new Label(name);
            Label lblValue = new Label(Integer.toString(value));

            Style.txt(lblPositie, 10);
            Style.txt(lblName, 10);
            Style.txt(lblValue, 10);

            GridPane.setHalignment(lblPositie, HPos.CENTER);
            GridPane.setHalignment(lblValue, HPos.RIGHT);

            view.getGpWinners().add(lblPositie, 0, position);
            view.getGpWinners().add(lblName, 1, position);
            view.getGpWinners().add(lblValue, 2, position);
        }
    }
}
