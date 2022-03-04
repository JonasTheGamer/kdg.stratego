package be.kdg.stratego;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.armysetup.ArmySetupPresenter;
import be.kdg.stratego.view.armysetup.ArmySetupView;
import be.kdg.stratego.view.mainmenu.MainMenuPresenter;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import be.kdg.stratego.view.newgame.NewGamePresenter;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ProgrammaModel model = new ProgrammaModel();

        //MainMenuView view = new MainMenuView();
        //MainMenuPresenter presenter = new MainMenuPresenter(model, view);

        //NewGameView view = new NewGameView();
        //NewGamePresenter presenter = new NewGamePresenter(model, view);

        ArmySetupView view = new ArmySetupView();
        ArmySetupPresenter presenter = new ArmySetupPresenter(model, view);


        // Set window to show this view
        primaryStage.setScene(new Scene(view));

        // Add event handlers
        presenter.addWindowEventHandlers();

        // Show window
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}