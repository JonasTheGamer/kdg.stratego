package be.kdg.stratego.view.mainmenu;

import be.kdg.stratego.model.Highscore;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.help.HelpPresenter;
import be.kdg.stratego.view.help.HelpView;
import be.kdg.stratego.view.newgame.NewGamePresenter;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuPresenter {
    private ProgrammaModel model;
    private MainMenuView view;

    public MainMenuPresenter(ProgrammaModel model, MainMenuView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //Code
        view.getBtnPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(model, newGameView);
                view.getScene().setRoot(newGameView);
            }
        });

        view.getBtnQuit().setOnAction(actionEvent -> {
            Stage mainStage = (Stage) view.getScene().getWindow();
            mainStage.close();
        });

        view.getBtnHelp().setOnAction(actionEvent -> {
            HelpView helpView = new HelpView();
            HelpPresenter helpPresenter = new HelpPresenter(model, helpView);

            Stage mijnStage = new Stage();
            mijnStage.initOwner(view.getScene().getWindow());
            mijnStage.initModality(Modality.APPLICATION_MODAL);
            mijnStage.setScene(new Scene(helpView));
            mijnStage.showAndWait();
        });
    }

    private void updateView() {
        /* Highscores */
        String highscores = "";
        int positie = 0;
        model.updateHighscores();

        for (Highscore highscore : model.getHighscores()) {
            positie++;
            if (positie <= 10) {
                Label lblPositie = new Label(Integer.toString(positie));
                Label lblScore = new Label(Integer.toString(highscore.getScore()));
                Label lblName = new Label(highscore.getSpelernaam());

                Style.txt(lblPositie, 10);
                Style.txt(lblScore, 10);
                Style.txt(lblName, 10);

                view.getGpHighscores().add(lblPositie, 0, positie);
                view.getGpHighscores().add(lblScore, 1, positie);
                view.getGpHighscores().add(lblName, 2, positie);
            } else {
                return;
            }
        }
    }

    public void addWindowEventHandlers() {

    }

}
