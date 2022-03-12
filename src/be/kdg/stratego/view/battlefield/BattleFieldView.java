package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.view.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class BattleFieldView extends BorderPane {
    // Controls
    private Label lblScreenTitle;
    private Label lblClock;
    private Button btnClose;

    // Panes
    private HBox hbHeader;
    private GridPane gpBoard;

    public BattleFieldView() {
        InitializeNodes();
        LayoutNodes();
    }

    public void InitializeNodes() {
        // Controls
        //// Header
        lblClock = new Label("00:00");
        lblScreenTitle = new Label("STRATEGO");
        btnClose = new Button("X");

        // Panes
        hbHeader = new HBox();
        gpBoard = new GridPane();

    }

    public void LayoutNodes() {
        // Main borderpane
        this.setBackground(Style.applicationBackground);

        // Header (clock, title & close button)
        hbHeader.setAlignment(Pos.CENTER);

        //// Clock
        Style.txt(lblClock, Style.fontSize(20), Color.BLACK);

        //// Title (player X, place your army)
        Style.txt(lblScreenTitle,Style.fontSize(40),Color.BLACK);

        //// Close button
        Style.btn(btnClose, Style.fontSize(20), Style.size(45), Style.size(45));

        hbHeader.getChildren().addAll(lblClock, lblScreenTitle, btnClose);

        this.setTop(hbHeader);


        //// Field
        ////// The field will also be filled with the question marks and the pieces in the presenter, to allow for a dynamic map size. (x rows & x columns)
        this.setCenter(gpBoard);
        gpBoard.setAlignment(Pos.CENTER);

    }

    public Label getLblScreenTitle() {
        return lblScreenTitle;
    }

    public GridPane getGpBoard() {
        return gpBoard;
    }

}
