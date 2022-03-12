package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.view.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ArmySetupView extends BorderPane {
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
        btnFill = new Button("Fill board");

        // Panes
        gpPieces = new GridPane();
        gpBoard = new GridPane();
        vbMenu = new VBox();
        vbSetup = new VBox();
        vbGame = new VBox();

        // The gridpane gpPieces will be filled in the presenter class with all pieces that are available.
    }

    public void LayoutNodes() {
        // Main borderpane
        this.setBackground(Style.applicationBackground);

        // Header (title)

        //// Title (player X, place your army)
        Style.txt(lblScreenTitle,Style.fontSize(40),Color.BLACK);
        this.setAlignment(lblScreenTitle, Pos.CENTER);
        this.setMargin(lblScreenTitle, new Insets(Style.size(15), 0, 0, 0));

        this.setTop(lblScreenTitle);

        //// Army pieces
        ////// The gridpane will be filled in the presenter class with all pieces that are available.
        gpPieces.setPrefWidth(Style.size(400));
        gpPieces.setHgap(Style.size(30));
        gpPieces.setVgap(Style.size(30));
        gpPieces.setAlignment(Pos.CENTER);

        this.setMargin(gpPieces, new Insets(0, Style.size(50), 0, Style.size(50)));
        this.setLeft(gpPieces);

        //// Field
        ////// The field will also be filled with the question marks and the pieces in the presenter, to allow for a dynamic map size. (x rows & x columns)
        this.setCenter(gpBoard);
        gpBoard.setAlignment(Pos.CENTER);

        //// Menu
        vbMenu.setBackground(Style.bgBoxNoPadding);
        vbMenu.setAlignment(Pos.CENTER);

        vbMenu.setSpacing(Style.size(100));
        vbMenu.setPadding(new Insets(0, Style.size(50), 0, Style.size(50)));
        this.setMargin(vbMenu, new Insets(Style.size(138), Style.size(92), Style.size(138), Style.size(98)));

        this.setRight(vbMenu);

        ////// Setup buttons (load & save)
        Style.txt(lblSetupTitle, Style.fontSize(20));

        vbSetup.setAlignment(Pos.CENTER);
        vbSetup.setSpacing(Style.size(20));

        Style.btn(btnLoad, Style.fontSize(15), 225, 50);
        Style.btn(btnSave, Style.fontSize(15),  225, 50);

        vbSetup.getChildren().addAll(lblSetupTitle, btnLoad, btnSave);
        vbMenu.getChildren().add(vbSetup);

        ////// Game buttons (start & back)
        Style.txt(lblGameTitle, Style.fontSize(20));

        vbGame.setAlignment(Pos.CENTER);
        vbGame.setSpacing(Style.size(20));

        Style.btn(btnStart, Style.fontSize(15), 225,50);
        Style.btn(btnBack, Style.fontSize(15), 225,50);
        Style.btn(btnFill, Style.fontSize(15), 225, 50);

        vbGame.getChildren().addAll(lblGameTitle, btnStart, btnBack, btnFill);
        vbMenu.getChildren().add(vbGame);
    }

    public Label getLblScreenTitle() {
        return lblScreenTitle;
    }

    public Label getLblSetupTitle() {
        return lblSetupTitle;
    }

    public Button getBtnLoad() {
        return btnLoad;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Label getLblGameTitle() {
        return lblGameTitle;
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

    public VBox getVbSetup() {
        return vbSetup;
    }

    public VBox getVbMenu() {
        return vbMenu;
    }

    public VBox getVbGame() {
        return vbGame;
    }
}
