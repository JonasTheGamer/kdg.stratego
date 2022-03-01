package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.view.Backgrounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ArmySetupView extends BorderPane {
    // Controls
    private Label lblScreenTitle;
    private Label lblSetupTitle ;
    private Button btnLoad;
    private Button btnSave;

    private Label lblGameTitle;
    private Button btnStart;
    private Button btnBack;

    // Panes
    private GridPane gpPieces;
    private GridPane gpField;
    private VBox vbSetup;
    private VBox vbMenu;
    private VBox vbGame;

    public ArmySetupView() {
        InitializeNodes();
        LayoutNodes();
    }

    public void InitializeNodes() {
        // Controls

        //// Header
        lblScreenTitle = new Label("Player 1: Place your army");

        //// Menu setup
        lblSetupTitle = new Label("Setup");
        btnLoad = new Button("Load");
        btnSave = new Button("Save");

        //// Menu game
        lblGameTitle = new Label("Game");
        btnStart = new Button("Start");
        btnBack = new Button("Back");

        // Panes
        gpPieces = new GridPane(); 
        gpField = new GridPane();
        vbMenu = new VBox();
        vbSetup = new VBox();
        vbGame = new VBox();

        // The gridpane gpPieces will be filled in the presenter class with all pieces that are available.
        // The gridpane gpField will also be filled with the question marks and the pieces in the presenter, to allow for a dynamic map size. (x rows & x columns)

    }

    public void LayoutNodes() {
        // Styling variables
        double btnWidth = 400;
        double btnHeight = 50;
        Background btnBackground = new Background(new BackgroundFill(
                new Color(0, 0, 0.2, 0.9),
                new CornerRadii(20),
                null
        ));

        // Main borderpane
        this.setBackground(Backgrounds.background);

        // Header (title)

        //// Title (player X, place your army)
        lblScreenTitle.setFont(Font.font("Verdana", 50));
        this.setAlignment(lblScreenTitle, Pos.CENTER);
        this.setMargin(lblScreenTitle, new Insets(15,0,0,0));

        this.setTop(lblScreenTitle);

        //// Army pieces
        ////// The gridpane will be filled in the presenter class with all pieces that are available.
        this.setLeft(gpPieces);

        //// Field
        ////// The field will also be filled with the question marks and the pieces in the presenter, to allow for a dynamic map size. (x rows & x columns)
       this.setCenter(gpField);

        //// Menu
        vbMenu.setBackground(Backgrounds.boxBackground);
        vbMenu.setAlignment(Pos.CENTER);
        vbMenu.setPrefWidth(400);
        vbMenu.setSpacing(50);
        this.setMargin(vbMenu, new Insets(25, 75,50,0));
        this.setRight(vbMenu);

        ////// Setup buttons (load & save)
        lblSetupTitle.setFont(Font.font("Verdana", 15));
        lblSetupTitle.setTextFill(Color.WHITE);

        vbSetup.setAlignment(Pos.CENTER);
        vbSetup.setSpacing(20);

        btnLoad.setPrefWidth(btnWidth);
        btnLoad.setPrefHeight(btnHeight);
        btnLoad.setTextFill(Color.WHITE);
        btnLoad.setBackground(btnBackground);

        btnSave.setPrefWidth(btnWidth);
        btnSave.setPrefHeight(btnHeight);
        btnSave.setTextFill(Color.WHITE);
        btnSave.setBackground(btnBackground);

        vbSetup.getChildren().addAll(lblSetupTitle, btnLoad, btnSave);
        vbMenu.getChildren().add(vbSetup);

        ////// Game buttons (start & back)
        lblGameTitle.setFont(Font.font("Verdana", 15));
        lblGameTitle.setTextFill(Color.WHITE);

        vbGame.setAlignment(Pos.CENTER);
        vbGame.setSpacing(20);

        btnStart.setPrefWidth(btnWidth);
        btnStart.setPrefHeight(btnHeight);
        btnStart.setTextFill(Color.WHITE);
        btnStart.setBackground(btnBackground);

        btnBack.setPrefWidth(btnWidth);
        btnBack.setPrefHeight(btnHeight);
        btnBack.setTextFill(Color.WHITE);
        btnBack.setBackground(btnBackground);

        vbGame.getChildren().addAll(lblGameTitle, btnStart, btnBack);
        vbMenu.getChildren().add(vbGame);

        // Add to panes


    }

}
