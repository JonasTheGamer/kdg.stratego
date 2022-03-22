package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.view.Style;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class BattleFieldView extends BorderPane {
    private Label lblScreenTitle;
    private Label lblClock;
    private Button btnClose;
    private HBox hbHeader;
    private GridPane gpBoard;

    public BattleFieldView() {
        InitializeNodes();
        LayoutNodes();
    }

    public void InitializeNodes() {
        // Controls
        lblClock = new Label("00:00");
        lblScreenTitle = new Label("STRATEGO");
        btnClose = new Button("X");

        // Panes
        hbHeader = new HBox();
        gpBoard = new GridPane();
    }

    public void LayoutNodes() {
        // Main borderpane
        this.setBackground(Style.bgApplication);

        // Header (clock, title & close button)
        hbHeader.setAlignment(Pos.CENTER);
        hbHeader.getChildren().addAll(lblClock, lblScreenTitle, btnClose);
        this.setTop(hbHeader);

        //// Clock
        Style.txt(lblClock, 20, Color.BLACK);

        //// Title (player X, place your army)
        Style.txt(lblScreenTitle,40,Color.BLACK);

        //// Close button
        Style.btn(btnClose, 20, Style.size(45), Style.size(45));

        //// Field (will be filled in the presenter class with all pieces that are available)
        gpBoard.setAlignment(Pos.CENTER);
        this.setCenter(gpBoard);
    }

    public Label getLblScreenTitle() {
        return lblScreenTitle;
    }

    public GridPane getGpBoard() {
        return gpBoard;
    }

}
