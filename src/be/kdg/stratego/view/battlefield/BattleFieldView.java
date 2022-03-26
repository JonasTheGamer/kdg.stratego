package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.view.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class BattleFieldView extends BorderPane {
    // Controls
    private Label lblScreenTitle;
    private Label lblClock;
    private Button btnClose;
    private Button btnNextPlayer;

    // Panes
    private BorderPane bpHeader;
    private StackPane spBoard;
    private GridPane gpBoard;

    public BattleFieldView() {
        InitializeNodes();
        LayoutNodes();
    }

    public void InitializeNodes() {
        // Controls
        lblClock = new Label("00:00");
        lblScreenTitle = new Label("Player 1234567890");
        btnClose = new Button("X");
        btnNextPlayer = new Button("");

        // Panes
        bpHeader = new BorderPane();
        spBoard = new StackPane();
        gpBoard = new GridPane();
    }

    public void LayoutNodes() {
        // Main borderpane
        this.setBackground(Style.bgApplication);

        // Header (clock, title & close button)
        this.setTop(bpHeader);
        bpHeader.setLeft(lblClock);
        bpHeader.setCenter(lblScreenTitle);
        bpHeader.setRight(btnClose);

        //// Clock
        Style.txt(lblClock, 20, Color.BLACK);
        setMargin(lblClock, new Insets(Style.size(20)));

        //// Title (player X, place your army)
        Style.txt(lblScreenTitle, 40, Color.BLACK);
        setMargin(lblScreenTitle, new Insets(Style.size(40)));

        //// Close button
        Style.btn(btnClose, 20, Style.size(45), Style.size(45));
        setMargin(btnClose, new Insets(Style.size(20)));

        //// Field (will be filled in the presenter class with all pieces that are available)
        this.setCenter(spBoard);
        spBoard.getChildren().add(gpBoard);

        // Add the next player overlay
        btnNextPlayer.setPrefWidth(Style.size(650));
        btnNextPlayer.setPrefHeight(Style.size(650));
        btnNextPlayer.setTextAlignment(TextAlignment.CENTER);
        Style.btn(btnNextPlayer, 20, 800, 735);
        btnNextPlayer.setBackground(new Background(new BackgroundFill(
                new Color(0, 0, 0.2, 1),
                new CornerRadii(0),
                null
        )));
        btnNextPlayer.setVisible(false);

        spBoard.getChildren().add(btnNextPlayer);

        gpBoard.setAlignment(Pos.TOP_CENTER);
    }

    public Label getLblScreenTitle() {
        return lblScreenTitle;
    }

    public GridPane getGpBoard() {
        return gpBoard;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public Button getBtnNextPlayer() {
        return btnNextPlayer;
    }
}