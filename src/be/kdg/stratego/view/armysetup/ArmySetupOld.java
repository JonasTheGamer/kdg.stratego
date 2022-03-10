package be.kdg.stratego.view.armysetup;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArmySetupOld extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stgArmySetup) throws Exception {
        //////////////////////
        // Main structure  //
        ////////////////////

        // Create panes
        BorderPane bpMain = new BorderPane();
        HBox hbCenter = new HBox();
        GridPane gpPieces = new GridPane();
        GridPane gpField = new GridPane();
        VBox vbMenu = new VBox();

        // Gridpane pieces maken
        int column = 0;
        int row = 0;
        for (int i = 0; i < 12; i++) {
            HBox vlagElement = new HBox();
            ImageView ivPiece = new ImageView("/legermanneke.png");

        }

        // Create scene
        Scene scNewGame = new Scene(bpMain);

        // Add scene to stage
        stgArmySetup.setScene(scNewGame);

        //////////////////////
        // Create nodes    //
        ////////////////////

        stgArmySetup.setScene(scNewGame);
        stgArmySetup.setFullScreen(true);
        stgArmySetup.setFullScreenExitHint("");
        stgArmySetup.show();

        //Code
        stgArmySetup.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode() == KeyCode.ESCAPE) {
                stgArmySetup.close();
            }
        });
    }
}
