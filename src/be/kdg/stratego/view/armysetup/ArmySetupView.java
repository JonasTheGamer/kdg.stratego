package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.view.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ArmySetupView extends BorderPane {
    private Label lblScreenTitle;
    private Label lblSetupTitle;
    private Button btnLoad;
    private Button btnSave;
    private Label lblGameTitle;
    private Button btnStart;
    private Button btnBack;
    private Button btnFill;
    private GridPane gpPieces;
    private GridPane gpBoard;
    private VBox vbSetup;
    private VBox vbMenu;
    private VBox vbGame;

    public ArmySetupView() {
        InitializeNodes();
        LayoutNodes();
    }

    public void InitializeNodes() {
        // Controls
        lblScreenTitle = new Label();
        lblSetupTitle = new Label("Setup");
        btnLoad = new Button("Load");
        btnSave = new Button("Save");

        lblGameTitle = new Label("Game");
        btnStart = new Button("Continue");
        btnBack = new Button("Back");
        btnFill = new Button("Fill board");

        // Panes
        gpPieces = new GridPane();
        gpBoard = new GridPane();
        vbMenu = new VBox();
        vbSetup = new VBox();
        vbGame = new VBox();
    }

    public void LayoutNodes() {
        // Main borderpane
        this.setBackground(Style.bgApplication);

        //// Title (player X, place your army)
        Style.txt(lblScreenTitle,40,Color.BLACK);
        setAlignment(lblScreenTitle, Pos.CENTER);
        setMargin(lblScreenTitle, new Insets(Style.size(15), 0, 0, 0));
        this.setTop(lblScreenTitle);


        //// Army pieces (will be filled in the presenter class with all pieces that are available)
        gpPieces.setPrefWidth(Style.size(400));
        gpPieces.setHgap(Style.size(30));
        gpPieces.setVgap(Style.size(30));
        gpPieces.setAlignment(Pos.CENTER);
        setMargin(gpPieces, new Insets(0, Style.size(50), 0, Style.size(50)));
        this.setLeft(gpPieces);


        //// Field (will be filled in the presenter class with all pieces that are available)
        gpBoard.setAlignment(Pos.CENTER);
        this.setCenter(gpBoard);


        //// Menu
        vbMenu.setBackground(Style.bgBoxNoPadding);
        vbMenu.setAlignment(Pos.CENTER);
        vbMenu.setSpacing(Style.size(100));
        vbMenu.setPadding(new Insets(0, Style.size(50), 0, Style.size(50)));
        setMargin(vbMenu, new Insets(Style.size(138), Style.size(92), Style.size(138), Style.size(98)));
        this.setRight(vbMenu);
        vbMenu.getChildren().addAll(vbSetup,vbGame);

        ////// Setup buttons (load & save)
        vbSetup.getChildren().addAll(lblSetupTitle, btnLoad, btnSave);
        vbSetup.setAlignment(Pos.CENTER);
        vbSetup.setSpacing(Style.size(20));

        Style.txt(lblSetupTitle, 20);
        Style.btn(btnLoad, 15, 225, 50);
        Style.btn(btnSave, 15,  225, 50);

        ////// Game buttons (start & back)
        vbGame.setAlignment(Pos.CENTER);
        vbGame.setSpacing(Style.size(20));
        vbGame.getChildren().addAll(lblGameTitle, btnStart, btnBack, btnFill);

        Style.txt(lblGameTitle, 20);
        Style.btn(btnStart, 15, 225,50);
        Style.btn(btnBack, 15, 225,50);
        Style.btn(btnFill, 15, 225, 50);
    }

    public Label getLblScreenTitle() {
        return lblScreenTitle;
    }

    public Button getBtnStart() {
        return btnStart;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnFill() {
        return btnFill;
    }

    public GridPane getGpPieces() {
        return gpPieces;
    }

    public GridPane getGpBoard() {
        return gpBoard;
    }
}
