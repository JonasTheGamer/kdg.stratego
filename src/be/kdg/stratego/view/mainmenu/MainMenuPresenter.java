package be.kdg.stratego.view.mainmenu;

import be.kdg.stratego.model.Highscore;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.help.HelpPresenter;
import be.kdg.stratego.view.help.HelpView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        // Highscores
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
        Stage stage = (Stage) view.getScene().getWindow();

        view.getBtnQuit().setOnAction(actionEvent -> stage.close());
    }
}
