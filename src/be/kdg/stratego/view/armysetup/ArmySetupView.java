package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.view.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ArmySetupView extends BorderPane {
    // Controls
    private Label lblScreenTitle;
    private Label lblSetupTitle;
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
        // Main borderpane
        this.setBackground(Style.applicationBackground);

        // Header (title)

        //// Title (player X, place your army)
        Style.txt(lblScreenTitle,50,Color.BLACK);
        this.setAlignment(lblScreenTitle, Pos.CENTER);
        this.setMargin(lblScreenTitle, new Insets(15, 0, 0, 0));

        this.setTop(lblScreenTitle);

        //// Army pieces
        ////// The gridpane will be filled in the presenter class with all pieces that are available.
        //this.setLeft(gpPieces);

        //// Field
        ////// The field will also be filled with the question marks and the pieces in the presenter, to allow for a dynamic map size. (x rows & x columns)
        this.setCenter(gpField);
        gpField.setStyle("-fx-background-color: cell-border-color, cell-color;");
        gpField.setStyle("-fx-background-insets: 0, 1 1 0 0;");
        gpField.setStyle("-fx-padding: 1 ;");

        gpField.setAlignment(Pos.CENTER);

        //// Menu
        vbMenu.setBackground(Style.background);
        vbMenu.setAlignment(Pos.CENTER);
        vbMenu.setPrefWidth(400);
        vbMenu.setSpacing(50);
        this.setMargin(vbMenu, new Insets(25, 75, 50, 0));
        this.setRight(vbMenu);

        ////// Setup buttons (load & save)
        Style.txt(lblSetupTitle, 15);

        vbSetup.setAlignment(Pos.CENTER);
        vbSetup.setSpacing(20);

        Style.btn(btnLoad, 15);
        Style.btn(btnSave, 15);

        vbSetup.getChildren().addAll(lblSetupTitle, btnLoad, btnSave);
        vbMenu.getChildren().add(vbSetup);

        ////// Game buttons (start & back)
        Style.txt(lblGameTitle, 15);

        vbGame.setAlignment(Pos.CENTER);
        vbGame.setSpacing(20);

        Style.btn(btnStart, 15);
        Style.btn(btnBack, 15);

        vbGame.getChildren().addAll(lblGameTitle, btnStart, btnBack);
        vbMenu.getChildren().add(vbGame);

        // Add to panes

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

    public GridPane getGpPieces() {
        return gpPieces;
    }

    public GridPane getGpField() {
        return gpField;
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
