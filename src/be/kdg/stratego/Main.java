package be.kdg.stratego;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.mainmenu.MainMenuPresenter;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ProgrammaModel model = new ProgrammaModel();

        MainMenuView view = new MainMenuView();
        MainMenuPresenter presenter = new MainMenuPresenter(model, view);

        primaryStage.setScene(new Scene(view));
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle("Stratego");
        primaryStage.getIcons().add(new Image("icon.png"));

        presenter.addWindowEventHandlers();

        primaryStage.show();
    }
}