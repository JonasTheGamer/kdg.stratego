package be.kdg.stratego.view.endofgame;

import be.kdg.stratego.view.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EndOfGameView extends VBox {
    // Controls
    private Label lblQuote;
    private Label lblSubtitle;
    private Label lblTurnsTitle;
    private Label lblTurnsValue;
    private Label lblScoreTitle;
    private Label lblScoreValue;
    private Button btnMenu;

    public EndOfGameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    public void initialiseNodes() {
        // Title & subtitle
        lblQuote = new Label("Finally, peace...");
        lblSubtitle = new Label();

        // Turns
        lblTurnsTitle = new Label("Amount of turns");
        lblTurnsValue = new Label();

        // Score
        lblScoreTitle = new Label("Score of leftover pieces");
        lblScoreValue = new Label();

        // Back to menu button
        btnMenu = new Button("Back to menu");
    }

    public void layoutNodes() {
        // Main screen layout
        this.setAlignment(Pos.TOP_CENTER);
        this.setBackground(new Background(new BackgroundFill(
                Color.BLACK,
                null,
                null
        )));

        // Title & subtitle
        Style.txt(lblQuote, 35);
        this.setMargin(lblQuote, new Insets(30, 15, 5, 15));
        Style.txt(lblSubtitle, 20);
        this.setMargin(lblSubtitle, new Insets(5, 15, 25, 15));

        // Turns
        Style.txt(lblTurnsTitle, 25);
        this.setMargin(lblTurnsTitle, new Insets(5, 15, 5, 15));
        Style.txt(lblTurnsValue, 20);
        this.setMargin(lblTurnsValue, new Insets(5, 15, 20, 15));

        // Score
        Style.txt(lblScoreTitle, 25);
        this.setMargin(lblScoreTitle, new Insets(5, 15, 5, 15));
        Style.txt(lblScoreValue, 20);
        this.setMargin(lblScoreValue, new Insets(5, 15, 20, 15));

        // Back to menu button
        Style.btn(btnMenu, 20, 300, 45);
        this.setMargin(btnMenu, new Insets(5, 15, 20, 15));

        // Add all nodes to the pane
        this.getChildren().addAll(
                lblQuote,
                lblSubtitle,
                lblTurnsTitle,
                lblTurnsValue,
                lblScoreTitle,
                lblScoreValue,
                btnMenu
        );

    }

    // Getters

    public Label getLblSubtitle() {
        return lblSubtitle;
    }

    public Label getLblTurnsValue() {
        return lblTurnsValue;
    }

    public Label getLblScoreValue() {
        return lblScoreValue;
    }

    public Button getBtnMenu() {
        return btnMenu;
    }
}
