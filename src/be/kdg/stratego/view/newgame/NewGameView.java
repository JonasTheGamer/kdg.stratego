package be.kdg.stratego.view.newgame;

import be.kdg.stratego.view.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NewGameView extends HBox {
    // Controls
    private Label[] lblName;

    private Label[] lblTxtName;

    private TextField[] txtName;

    private Label[] lblCmbColor;

    private ColorPicker[] cmbColor;

    private Label[] lblFlagTitle;

    private GridPane[] gpFlags;

    private Button[] btnPlayer1Flag;
    private Button[] btnPlayer2Flag;

    private ImageView imgTitle;

    private Button btnReady;
    private Button btnCancel;

    // Panes
    private VBox[] vbPlayer;
    private HBox[] hbName;
    private HBox[] hbColor;
    private VBox[] vbNameColor;
    private VBox[] vbFlags;

    private VBox vbMenu;
    private VBox vbButtons;

    public NewGameView() {
        this.InitialiseNodes();
        this.layoutNodes();
    }

    public void InitialiseNodes() {
        // Controls
        lblName = new Label[2];
        lblName[1] = new Label("Player 1");
        lblName[2] = new Label("Player 2");

        lblTxtName = new Label[2];
        lblTxtName[1] = new Label("Name");
        lblTxtName[2] = new Label("Name");

        txtName = new TextField[2];
        txtName[1] = new TextField();
        txtName[2] = new TextField();

        lblCmbColor = new Label[2];
        lblCmbColor[1] = new Label("Color");
        lblCmbColor[2] = new Label("Color");

        cmbColor = new ColorPicker[2];
        cmbColor[1] = new ColorPicker();
        cmbColor[2] = new ColorPicker();

        lblFlagTitle = new Label[2];
        lblFlagTitle[1] = new Label("Flag");
        lblFlagTitle[2] = new Label("Flag");

        gpFlags = new GridPane[2];
        gpFlags[1] = new GridPane();
        gpFlags[2] = new GridPane();

        btnPlayer1Flag = new Button[4];
        btnPlayer1Flag[1] = new Button("Flag 1");
        btnPlayer1Flag[2] = new Button("Flag 2");
        btnPlayer1Flag[3] = new Button("Flag 3");
        btnPlayer1Flag[4] = new Button("Flag 4");

        btnPlayer2Flag = new Button[4];
        btnPlayer2Flag[1] = new Button("Flag 1");
        btnPlayer2Flag[2] = new Button("Flag 2");
        btnPlayer2Flag[3] = new Button("Flag 3");
        btnPlayer2Flag[4] = new Button("Flag 4");

        imgTitle = new ImageView("/title.png");

        btnReady = new Button("Ready");
        btnCancel = new Button("Cancel");

        // Panes
        vbMenu = new VBox();

        vbPlayer[] = new VBox();

        hbPlayer1Name = new HBox();
        hbPlayer2Name = new HBox();

        hbPlayer1Color = new HBox();
        hbPlayer2Color = new HBox();

        vbPlayer1NameColor = new VBox();
        vbPlayer2NameColor = new VBox();

        vbPlayer1Flags = new VBox();
        vbPlayer2Flags = new VBox();

        vbButtons = new VBox();
    }

    public void layoutNodes() {
        // Styling variables
        final int playerSetupPrefWidth = 300;
        Color textColorWhite = new Color(1, 1, 1, 1);
        Background backgroundBtn = new Background(new BackgroundFill(
                new Color(0, 0, 0.2, 0.9),
                new CornerRadii(20),
                null
        ));
        Background backgroundPane = new Background(new BackgroundFill(
                new Color(0, 0, 0, 0.8),
                new CornerRadii(20),
                new Insets(-10)
        ));

        // Styling
        ///Players
        for (Label lbl : lblName) {
            lbl.setFont(Font.font("Verdana", 20));
            lbl.setTextFill(Color.WHITE);
        }

        ////Name
        for (Label lbl : lblTxtName) {
            lbl.setFont(Font.font("Verdana", 15));
            lbl.setTextFill(Color.WHITE);
        }

        for (TextField txt : txtName) {
            txt.setPrefWidth(200);
        }


        hbPlayer1Name.getChildren().addAll(lblTxtNamePlayer1, txtNamePlayer1);
        hbPlayer1Name.setAlignment(Pos.CENTER);
        hbPlayer1Name.setSpacing(20);

        ////Color
        lblCmbPlayer1Color.setFont(Font.font("Verdana", 15));
        lblCmbPlayer1Color.setTextFill(Color.WHITE);

        cmbPlayer1Color.setPrefWidth(200);

        hbPlayer1Color.getChildren().addAll(lblCmbPlayer1Color, cmbPlayer1Color);
        hbPlayer1Color.setAlignment(Pos.CENTER);
        hbPlayer1Color.setSpacing(25);

        ////Naam en Color
        vbPlayer1NameColor.getChildren().addAll(lblNamePlayer1, hbPlayer1Name, hbPlayer1Color);
        vbPlayer1NameColor.setSpacing(20);
        vbPlayer1NameColor.setAlignment(Pos.CENTER);

        ////Flags
        lblFlagTitle[1].setFont(Font.font("Verdana", 15));
        lblPlayer1FlagTitle.setTextFill(Color.WHITE);

        btnPlayer1Flag1.setPrefSize(125, 70);
        btnPlayer1Flag2.setPrefSize(125, 70);
        btnPlayer1Flag3.setPrefSize(125, 70);
        btnPlayer1Flag4.setPrefSize(125, 70);

        gpPlayer1Flags.add(btnPlayer1Flag1, 0, 0);
        gpPlayer1Flags.add(btnPlayer1Flag2, 1, 0);
        gpPlayer1Flags.add(btnPlayer1Flag3, 0, 1);
        gpPlayer1Flags.add(btnPlayer1Flag4, 1, 1);
        gpPlayer1Flags.setHgap(10);
        gpPlayer1Flags.setVgap(10);
        gpPlayer1Flags.setAlignment(Pos.CENTER);

        vbPlayer1Flags.getChildren().addAll(lblPlayer1FlagTitle, gpPlayer1Flags);
        vbPlayer1Flags.setSpacing(20);
        vbPlayer1Flags.setAlignment(Pos.CENTER);

        ////Pane
        vbPlayer1.getChildren().addAll(
                vbPlayer1NameColor,
                vbPlayer1Flags
        );
        vbPlayer1.setAlignment(Pos.CENTER);
        vbPlayer1.setPrefWidth(playerSetupPrefWidth);
        vbPlayer1.setSpacing(60);

        ///Player 2
        lblNamePlayer2.setFont(Font.font("Verdana", 20));
        lblNamePlayer2.setTextFill(Color.WHITE);

        ////Name
        lblTxtNamePlayer2.setFont(Font.font("Verdana", 15));
        lblTxtNamePlayer2.setTextFill(Color.WHITE);

        hbPlayer2Name.getChildren().addAll(lblTxtNamePlayer2, txtNamePlayer2);
        HBox.setMargin(hbPlayer2Name, new Insets(10, 10, 10, 10));
        hbPlayer2Name.setSpacing(20);

        ////Color
        lblCmbPlayer2Color.setFont(Font.font("Verdana", 15));
        lblCmbPlayer2Color.setTextFill(Color.WHITE);

        hbPlayer2Color.getChildren().addAll(lblCmbPlayer2Color, cmbPlayer2Color);
        hbPlayer2Color.setSpacing(20);

        ////Flags
        gpPlayer2Flags.add(btnPlayer2Flag1, 0, 0);
        gpPlayer2Flags.add(btnPlayer2Flag2, 1, 0);
        gpPlayer2Flags.add(btnPlayer2Flag3, 0, 1);
        gpPlayer2Flags.add(btnPlayer2Flag4, 1, 1);

        ////Pane
        vbPlayer2.getChildren().addAll(
                lblNamePlayer2,
                hbPlayer2Name,
                hbPlayer2Color,
                lblPlayer2FlagTitle,
                gpPlayer2Flags
        );
        vbPlayer2.setAlignment(Pos.CENTER);
        vbPlayer2.setPrefWidth(playerSetupPrefWidth);

        ///Menu
        btnReady.setPrefWidth(widthBtn);
        btnReady.setPrefHeight(heightBtn);
        btnReady.setBackground(backgroundBtn);
        btnReady.setTextFill(textColorWhite);

        btnCancel.setPrefWidth(widthBtn);
        btnCancel.setPrefHeight(heightBtn);
        btnCancel.setBackground(backgroundBtn);
        btnCancel.setTextFill(textColorWhite);

        vbButtons.getChildren().addAll(btnReady, btnCancel);
        vbButtons.setSpacing(20);
        vbButtons.setAlignment(Pos.CENTER);

        vbMenu.getChildren().addAll(imgTitle, vbButtons);
        vbMenu.setAlignment(Pos.CENTER);
        vbMenu.setSpacing(150);
        vbMenu.setTranslateY(-140);

        ///View
        this.getChildren().addAll(vbPlayer1, vbMenu, vbPlayer2);
        this.setSpacing(50);
        this.setBackground(Style.background);
        this.setAlignment(Pos.CENTER);
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

    public ColorPicker getCmbPlayer1Color() {
        return cmbPlayer1Color;
    }

    public ColorPicker getCmbPlayer2Color() {
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

    public ImageView getImgTitle() {
        return imgTitle;
    }

    public Button getBtnReady() {
        return btnReady;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public VBox getVbPlayer1() {
        return vbPlayer1;
    }

    public VBox getVbMenu() {
        return vbMenu;
    }

    public VBox getVbPlayer2() {
        return vbPlayer2;
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

    public VBox getVbPlayer1NameColor() {
        return vbPlayer1NameColor;
    }

    public VBox getVbPlayer2NameColor() {
        return vbPlayer2NameColor;
    }

    public VBox getVbPlayer1Flags() {
        return vbPlayer1Flags;
    }

    public VBox getVbPlayer2Flags() {
        return vbPlayer2Flags;
    }
}
