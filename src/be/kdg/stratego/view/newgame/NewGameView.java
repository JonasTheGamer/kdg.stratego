package be.kdg.stratego.view.newgame;

import be.kdg.stratego.view.Backgrounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class NewGameView extends HBox {
    // Controls
    private Label lblNamePlayer1;
    private Label lblNamePlayer2;

    private Label lblTxtNamePlayer1;
    private Label lblTxtNamePlayer2;

    private TextField txtNamePlayer1;
    private TextField txtNamePlayer2;

    private Label lblCmbPlayer1Color;
    private Label lblCmbPlayer2Color;

    private ComboBox cmbPlayer1Color;
    private ComboBox cmbPlayer2Color;

    private Label lblPlayer1FlagTitle;
    private Label lblPlayer2FlagTitle;

    private GridPane gpPlayer1Flags;
    private GridPane gpPlayer2Flags;

    private Button btnPlayer1Flag1;
    private Button btnPlayer2Flag1;

    private Button btnPlayer1Flag2;
    private Button btnPlayer2Flag2;

    private Button btnPlayer1Flag3;
    private Button btnPlayer2Flag3;

    private Button btnPlayer1Flag4;
    private Button btnPlayer2Flag4;

    private ImageView ivStratego;

    private Button btnReady;
    private Button btnCancel;

    // Panes
    private VBox vbSpeler1;
    private BorderPane bpMenu;
    private VBox vbSpeler2;
    private HBox hbPlayer1Name;
    private HBox hbPlayer2Name;
    private HBox hbPlayer1Color;
    private HBox hbPlayer2Color;
    private VBox vbButtons;


    public NewGameView() {
        this.InitialiseNodes();
        this.layoutNodes();
    }

    public void InitialiseNodes() {
        // Controls
        lblNamePlayer1 = new Label("Player 1");
        lblNamePlayer2 = new Label("Player 2");

        lblTxtNamePlayer1 = new Label("Name:");
        lblTxtNamePlayer2 = new Label("Name:");

        txtNamePlayer1 = new TextField();
        txtNamePlayer2 = new TextField();

        lblCmbPlayer1Color = new Label("Color:");
        lblCmbPlayer2Color = new Label("Color:");

        cmbPlayer1Color = new ComboBox();
        cmbPlayer2Color = new ComboBox();

        lblPlayer1FlagTitle = new Label("Flag:");
        lblPlayer2FlagTitle = new Label("Flag:");

        gpPlayer1Flags = new GridPane();
        gpPlayer2Flags = new GridPane();

        btnPlayer1Flag1 = new Button("Flag 1");
        btnPlayer2Flag1 = new Button("Flag 1");

        btnPlayer1Flag2 = new Button("Flag 2");
        btnPlayer2Flag2 = new Button("Flag 2");

        btnPlayer1Flag3 = new Button("Flag 3");
        btnPlayer2Flag3 = new Button("Flag 3");

        btnPlayer1Flag4 = new Button("Flag 4");
        btnPlayer2Flag4 = new Button("Flag 4");
        ivStratego = new ImageView("/title.png");

        btnReady = new Button("Ready");
        btnCancel = new Button("Cancel");

        // Panes
        vbSpeler1 = new VBox();
        bpMenu = new BorderPane();
        vbSpeler2 = new VBox();

        hbPlayer1Name = new HBox();
        hbPlayer2Name = new HBox();

        hbPlayer1Color = new HBox();
        hbPlayer2Color = new HBox();

        vbButtons = new VBox();
    }

    public void layoutNodes() {
        // Styling variables
        final int playerSetupPrefWidth = 300;

        // Styling
        this.setBackground(Backgrounds.background);
        this.setAlignment(Pos.CENTER);

        vbSpeler1.setBackground(Backgrounds.boxBackground);
        vbSpeler1.setPrefWidth(playerSetupPrefWidth);

        vbSpeler2.setBackground(Backgrounds.boxBackground);
        vbSpeler2.setPrefWidth(playerSetupPrefWidth);

        lblNamePlayer1.setFont(Font.font ("Verdana", 20));
        lblNamePlayer1.setTextFill(Color.WHITE);

        lblNamePlayer2.setFont(Font.font ("Verdana", 20));
        lblNamePlayer2.setTextFill(Color.WHITE);

        vbSpeler1.setAlignment(Pos.CENTER);
        vbSpeler2.setAlignment(Pos.CENTER);

        // Name field
        HBox.setMargin(hbPlayer1Name, new Insets(10,10,10,10));
        lblTxtNamePlayer1.setFont(Font.font("Verdana",15));
        lblTxtNamePlayer1.setTextFill(Color.WHITE);

        hbPlayer1Name.setSpacing(20);
        hbPlayer1Name.setBackground(Backgrounds.red);

        hbPlayer2Name.setSpacing(20);
        hbPlayer2Name.setBackground(Backgrounds.red);

        // Add to panes
        this.getChildren().addAll(vbSpeler1, bpMenu, vbSpeler2);

        hbPlayer1Name.getChildren().addAll(lblTxtNamePlayer1, txtNamePlayer1);
        hbPlayer2Name.getChildren().addAll(lblTxtNamePlayer2, txtNamePlayer2);

        hbPlayer1Color.getChildren().addAll(lblCmbPlayer1Color, cmbPlayer1Color);
        hbPlayer2Color.getChildren().addAll(lblCmbPlayer2Color, cmbPlayer2Color);

        gpPlayer1Flags.add(btnPlayer1Flag1, 0, 0);
        gpPlayer1Flags.add(btnPlayer1Flag2, 1, 0);
        gpPlayer1Flags.add(btnPlayer1Flag3, 0, 1);
        gpPlayer1Flags.add(btnPlayer1Flag4, 1, 1);

        gpPlayer2Flags.add(btnPlayer2Flag1, 0, 0);
        gpPlayer2Flags.add(btnPlayer2Flag2, 1, 0);
        gpPlayer2Flags.add(btnPlayer2Flag3, 0, 1);
        gpPlayer2Flags.add(btnPlayer2Flag4, 1, 1);

        vbSpeler1.getChildren().addAll(
                lblNamePlayer1,
                hbPlayer1Name,
                hbPlayer1Color,
                lblPlayer1FlagTitle,
                gpPlayer1Flags
        );

        vbSpeler2.getChildren().addAll(
                lblNamePlayer2,
                hbPlayer2Name,
                hbPlayer2Color,
                lblPlayer2FlagTitle,
                gpPlayer2Flags
        );

        vbButtons.getChildren().addAll(btnReady, btnCancel);
        bpMenu.setTop(ivStratego);
        bpMenu.setCenter(vbButtons);

    }

    public Label getLblNamePlayer1() {
        return lblNamePlayer1;
    }

    public Label getLblNamePlayer2() {
        return lblNamePlayer2;
    }

    public Label getLblTxtNamePlayer1() {
        return lblTxtNamePlayer1;
    }

    public Label getLblTxtNamePlayer2() {
        return lblTxtNamePlayer2;
    }

    public TextField getTxtNamePlayer1() {
        return txtNamePlayer1;
    }

    public TextField getTxtNamePlayer2() {
        return txtNamePlayer2;
    }

    public Label getLblCmbPlayer1Color() {
        return lblCmbPlayer1Color;
    }

    public Label getLblCmbPlayer2Color() {
        return lblCmbPlayer2Color;
    }

    public ComboBox getCmbPlayer1Color() {
        return cmbPlayer1Color;
    }

    public ComboBox getCmbPlayer2Color() {
        return cmbPlayer2Color;
    }

    public Label getLblPlayer1FlagTitle() {
        return lblPlayer1FlagTitle;
    }

    public Label getLblPlayer2FlagTitle() {
        return lblPlayer2FlagTitle;
    }

    public GridPane getGpPlayer1Flags() {
        return gpPlayer1Flags;
    }

    public GridPane getGpPlayer2Flags() {
        return gpPlayer2Flags;
    }

    public Button getBtnPlayer1Flag1() {
        return btnPlayer1Flag1;
    }

    public Button getBtnPlayer2Flag1() {
        return btnPlayer2Flag1;
    }

    public Button getBtnPlayer1Flag2() {
        return btnPlayer1Flag2;
    }

    public Button getBtnPlayer2Flag2() {
        return btnPlayer2Flag2;
    }

    public Button getBtnPlayer1Flag3() {
        return btnPlayer1Flag3;
    }

    public Button getBtnPlayer2Flag3() {
        return btnPlayer2Flag3;
    }

    public Button getBtnPlayer1Flag4() {
        return btnPlayer1Flag4;
    }

    public Button getBtnPlayer2Flag4() {
        return btnPlayer2Flag4;
    }

    public ImageView getIvStratego() {
        return ivStratego;
    }

    public Button getBtnReady() {
        return btnReady;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public VBox getVbSpeler1() {
        return vbSpeler1;
    }

    public BorderPane getBpMenu() {
        return bpMenu;
    }

    public VBox getVbSpeler2() {
        return vbSpeler2;
    }

    public HBox getHbPlayer1Name() {
        return hbPlayer1Name;
    }

    public HBox getHbPlayer2Name() {
        return hbPlayer2Name;
    }

    public HBox getHbPlayer1Color() {
        return hbPlayer1Color;
    }

    public HBox getHbPlayer2Color() {
        return hbPlayer2Color;
    }

    public VBox getVbButtons() {
        return vbButtons;
    }
}
