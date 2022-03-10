package be.kdg.stratego.view.mainmenu;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.newgame.NewGamePresenter;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

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
                /*view.getBtnPlay().setText(
                        String.valueOf(view.getBtnPlay().getWidth())
                );*/

                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(model, newGameView);
                view.getScene().setRoot(newGameView);
            }
        });

        view.getBtnQuit().setOnAction(actionEvent -> {
            Stage mainStage = (Stage) view.getScene().getWindow();
            mainStage.close();
        });

        view.getBtnHelp().setOnAction(actionEvent -> view.getBtnHelp().setText("Nee"));
    }

    private void updateView() {
        /* Highscores */
        try {
            File highscores = new File("highscores.csv");
            if (!highscores.exists()) {
                highscores.createNewFile();
            }
            List<String> inhoud = Files.readAllLines(highscores.toPath());
            int teller = 1;
            String scores = "";

            for (String huidigeRegel : inhoud) {
                if (teller <= 10) {
                    scores += huidigeRegel + "\n";
                }
                teller++;
            }
            view.getLblScores().setText(scores);


        } catch (IOException ex) {
            System.out.println("Fout");
        }
    }

    public void addWindowEventHandlers() {

    }

}
