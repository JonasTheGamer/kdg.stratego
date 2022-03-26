package be.kdg.stratego.view.endofgame;

import be.kdg.stratego.view.Style;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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

        // Title & subtitle
        Style.txt(lblQuote, 35);
        Style.txt(lblSubtitle, 20);

        // Turns
        Style.txt(lblTurnsTitle, 25);
        Style.txt(lblTurnsValue, 20);

        // Score
        Style.txt(lblScoreTitle, 25);
        Style.txt(lblScoreValue, 20);

        // Back to menu button
        Style.btn(btnMenu, 20, 300, 150);

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
    public Label getLblQuote() {
        return lblQuote;
    }

    public Label getLblSubtitle() {
        return lblSubtitle;
    }

    public Label getLblTurnsTitle() {
        return lblTurnsTitle;
    }

    public Label getLblTurnsValue() {
        return lblTurnsValue;
    }

    public Label getLblScoreTitle() {
        return lblScoreTitle;
    }

    public Label getLblScoreValue() {
        return lblScoreValue;
    }

    public Button getBtnMenu() {
        return btnMenu;
    }
}
