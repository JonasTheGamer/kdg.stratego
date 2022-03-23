package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.view.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BattleFieldView extends BorderPane {
    private Label lblScreenTitle;
    private Label lblClock;
    private Button btnClose;
    private BorderPane bpHeader;
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

        // Panes
        bpHeader = new BorderPane();
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
        this.setCenter(gpBoard);
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
}