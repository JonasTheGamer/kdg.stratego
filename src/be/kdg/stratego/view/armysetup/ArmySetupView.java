package be.kdg.stratego.view.armysetup;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ArmySetupView extends BorderPane {
    // Controls
    private Label lblScreenTitle = new Label("Player 1: Place your army");
    private Label lblSetupTitle = new Label("Setup");
    private Button btnLoad = new Button("Load");
    private Button btnSave = new Button("Save");

    private Label lblGameTitle = new Label("Game");
    private Button btnStart = new Button ("Start");
    private Button btnBack = new Button ("Back");

    // Panes
    private GridPane gpPieces = new GridPane(); 
    private GridPane gpField = new GridPane();
    private HBox hbMain = new HBox();
    private VBox vbMenu = new VBox();
    private VBox vbSetup = new VBox();
    private VBox vbGame = new VBox();

    public ArmySetupView() {
        InitializeNodes();
        LayoutNodes();
    }

    public void InitializeNodes() {
        // Controls
        lblScreenTitle = new Label("Player 1: Place your army");
        lblSetupTitle = new Label("Setup");
        btnLoad = new Button("Load");
        btnSave = new Button("Save");
        lblGameTitle = new Label("Game");
        btnStart = new Button("Start");
        btnBack = new Button("Back");

        // Panes
        gpPieces = new GridPane(); 
        gpField = new GridPane();
        hbMain = new HBox();
        vbMenu = new VBox();
        vbSetup = new VBox();
        vbGame = new VBox();

        // The gridpane will be filled in the presenter class with all pieces that are available.
        // The field will also be filled with the question marks and the pieces in the presenter, to allow for a dynamic map size. (x rows & x columns)

    }

    public void LayoutNodes() {
        // Styling variables

        // Styling controls

        // Styling panes
        

        // Add to panes
        this.setTop(lblGameTitle);
        this.setCenter(hbMain);

        hbMain.getChildren().addAll(gpPieces, gpField, vbMenu);
        vbMenu.getChildren().addAll(vbSetup, vbGame);

        vbSetup.getChildren().addAll(lblSetupTitle, btnLoad, btnSave);
        vbGame.getChildren().addAll(lblGameTitle, btnStart, btnBack);
    }

}
