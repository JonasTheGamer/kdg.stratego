package be.kdg.stratego.view.newgame;

import be.kdg.stratego.view.Style;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class NewGameOld extends Application {
    public static void main(String[] args) {
        System.out.println("About to run!");
        Application.launch(args);
    }

    @Override
    public void start(Stage stgNewGame) {
        //////////////////////
        // Main structure  //
        ////////////////////

        // Create panes
        HBox hbMain = new HBox();
        VBox vbSpeler1 = new VBox();
        BorderPane bpMenu = new BorderPane();
        VBox vbSpeler2 = new VBox();

        hbMain.getChildren().addAll(vbSpeler1, bpMenu, vbSpeler2);
        hbMain.setBackground(Style.bgApplication);
        hbMain.setAlignment(Pos.CENTER);

        // Styling vbSpeler1
        //VBox.setMargin(vbSpeler1, new Insets(100,100,100,100));
        vbSpeler1.setBackground(Style.background);
        vbSpeler1.prefWidth(900);


        //bpMenu.setBackground(Backgrounds.green);
        vbSpeler2.setBackground(Style.background);


        // Create scene
        Scene scNewGame = new Scene(hbMain);

        // Add scene to stage
        stgNewGame.setScene(scNewGame);

        //////////////////////
        // Create nodes    //
        ////////////////////

        //-----------------//
        // Player 1       //
        //---------------//

        // Label with player name
        Label lblNamePlayer1 = new Label("Player 1");

        // HBOX playername
        HBox hbPlayer1Name = new HBox();
        // label name
        Label lblTxtNamePlayer1 = new Label("Name:");
        // textfield
        TextField txtNamePlayer1 = new TextField();
        hbPlayer1Name.getChildren().addAll(lblTxtNamePlayer1, txtNamePlayer1);

        // HBOX player color
        HBox hbPlayer1Color = new HBox();
        // label color
        Label lblTxtPlayer1Color = new Label("Color:");
        // textfield
        TextField txtPlayer1Color = new TextField();
        hbPlayer1Color.getChildren().addAll(lblTxtPlayer1Color, txtPlayer1Color);

        // Label "Flag"
        Label lblPlayer1FlagTitle = new Label("Flag");

        // Gridpane with flag images to choose from
        GridPane gpPlayer1Flags = new GridPane();
        Button btnPlayer1Flag1 = new Button("Flag 1");
        Button btnPlayer1Flag2 = new Button("Flag 2");
        Button btnPlayer1Flag3 = new Button("Flag 3");
        Button btnPlayer1Flag4 = new Button("Flag 4");
        gpPlayer1Flags.add(btnPlayer1Flag1, 0, 0);
        gpPlayer1Flags.add(btnPlayer1Flag2, 1, 0);
        gpPlayer1Flags.add(btnPlayer1Flag3, 0, 1);
        gpPlayer1Flags.add(btnPlayer1Flag4, 1, 1);

        // Alles van speler 1 toevoegen aan de vbox ervan
        vbSpeler1.getChildren().addAll(lblNamePlayer1, hbPlayer1Name, hbPlayer1Color, lblPlayer1FlagTitle, gpPlayer1Flags);


        //-----------------//
        // Menu           //
        //---------------//
        ImageView ivStratego = new ImageView("/title.png");
        VBox vbButtons = new VBox();

        Button btnReady = new Button("Ready");
        Button btnCancel = new Button("Cancel");
        vbButtons.getChildren().addAll(btnReady, btnCancel);

        bpMenu.setTop(ivStratego);
        bpMenu.setCenter(vbButtons);

        //-----------------//
        // Player 2       //
        //---------------//

        // Label with player name
        Label lblNamePlayer2 = new Label("Player 2");

        // HBOX playername
        HBox hbPlayer2Name = new HBox();
        // label name
        Label lblTxtNamePlayer2 = new Label("Name:");
        // textfield
        TextField txtNamePlayer2 = new TextField();
        hbPlayer2Name.getChildren().addAll(lblTxtNamePlayer2, txtNamePlayer2);

        // HBOX player color
        HBox hbPlayer2Color = new HBox();
        // label name
        Label lblTxtPlayer2Color = new Label("Color:");
        // textfield
        TextField txtPlayer2Color = new TextField();
        hbPlayer2Color.getChildren().addAll(lblTxtPlayer2Color, txtPlayer2Color);

        // Label "Flag"
        Label lblPlayer2FlagTitle = new Label("Flag");

        // Gridpane with flag images to choose from
        GridPane gpPlayer2Flags = new GridPane();
        Button btnPlayer2Flag1 = new Button("Flag 1");
        Button btnPlayer2Flag2 = new Button("Flag 2");
        Button btnPlayer2Flag3 = new Button("Flag 3");
        Button btnPlayer2Flag4 = new Button("Flag 4");
        gpPlayer2Flags.add(btnPlayer2Flag1, 0, 0);
        gpPlayer2Flags.add(btnPlayer2Flag2, 1, 0);
        gpPlayer2Flags.add(btnPlayer2Flag3, 0, 1);
        gpPlayer2Flags.add(btnPlayer2Flag4, 1, 1);

        // Alles van speler 2 toevoegen aan de vbox ervan
        vbSpeler2.getChildren().addAll(lblNamePlayer2, hbPlayer2Name, hbPlayer2Color, lblPlayer2FlagTitle, gpPlayer2Flags);

        stgNewGame.setScene(scNewGame);
        stgNewGame.setFullScreen(true);
        stgNewGame.setFullScreenExitHint("");
        stgNewGame.show();

        //Code
        stgNewGame.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode() == KeyCode.ESCAPE) {
                stgNewGame.close();
            }
        });
    }
}
