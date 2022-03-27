package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.view.Style;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ArmySetupView extends VBox {
    // Controls
    private Label lblScreenTitle;
    private Label lblSetupTitle;
    private Button btnLoad;
    private Button btnSave;
    private Label lblGameTitle;
    private Button btnStart;
    private Button btnBack;
    private Button btnFill;

    // Panes
    private GridPane gpPieces;
    private GridPane gpBoard;
    private VBox vbSetup;
    private VBox vbMenu;
    private VBox vbGame;
    private HBox hbCenter;

    public ArmySetupView() {
        InitializeNodes();
        LayoutNodes();
    }

    public void InitializeNodes() {
        // Controls
        lblScreenTitle = new Label("Player x: Place your army");
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
        hbCenter = new HBox();

    }

    public void LayoutNodes() {
        // Main borderpane
        this.setBackground(Style.bgApplication);
        this.getChildren().addAll(lblScreenTitle,hbCenter);
        this.setSpacing(Style.size(40));
        this.setAlignment(Pos.CENTER);


        /// Title (player X, place your army)
        Style.txt(lblScreenTitle, 40, Color.BLACK);
        lblScreenTitle.setAlignment(Pos.CENTER);

        /// hbCenter (gpPieces, gpBoard, vbMenu)
        hbCenter.getChildren().addAll(gpPieces, gpBoard, vbMenu);
        hbCenter.setAlignment(Pos.TOP_CENTER);
        hbCenter.setSpacing(Style.size(80));
        hbCenter.setMaxHeight(550);

        //// Army pieces (will be filled in the presenter class with all pieces that are available)
        gpPieces.setPrefWidth(Style.size(350));
        gpPieces.setHgap(Style.size(10));
        gpPieces.setVgap(Style.size(10));
        gpPieces.setAlignment(Pos.CENTER);

        //// Field (will be filled in the presenter class with all pieces that are available)
        gpBoard.setAlignment(Pos.CENTER);

        /// Menu
        vbMenu.setPrefWidth(Style.size(350));
        vbMenu.setSpacing(Style.size(40));
        vbMenu.setBackground(Style.bgBoxNoPadding);
        vbMenu.setAlignment(Pos.CENTER);
        vbMenu.getChildren().addAll(vbSetup, vbGame);

        //// Setup buttons (load & save)
        vbSetup.getChildren().addAll(lblSetupTitle, btnLoad, btnSave);
        vbSetup.setAlignment(Pos.CENTER);
        vbSetup.setSpacing(Style.size(10));

        Style.txt(lblSetupTitle, 20);
        Style.btn(btnLoad, 15, 225, 50);
        Style.btn(btnSave, 15, 225, 50);

        //// Game buttons (start & back)
        vbGame.setAlignment(Pos.CENTER);
        vbGame.setSpacing(Style.size(10));
        vbGame.getChildren().addAll(lblGameTitle, btnStart, btnBack, btnFill);

        Style.txt(lblGameTitle, 20);
        Style.btn(btnStart, 15, 225, 50);
        Style.btn(btnBack, 15, 225, 50);
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
