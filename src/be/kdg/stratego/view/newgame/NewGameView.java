package be.kdg.stratego.view.newgame;

import be.kdg.stratego.view.Style;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NewGameView extends HBox {
    private Label[] lblName;
    private Label[] lblTxtName;
    private TextField[] txtName;
    private Label[] lblCpColor;
    private ColorPicker[] cpColor;
    private Label[] lblFlagTitle;
    private Button[][] btnFlag;
    private ImageView imgTitle;
    private Button btnReady;
    private Button btnCancel;
    private VBox[] vbPlayer;
    private HBox[] hbName;
    private HBox[] hbColor;
    private VBox[] vbNameColor;
    private GridPane[] gpFlags;
    private VBox[] vbFlags;
    private VBox vbMenu;
    private VBox vbButtons;
    private HBox hbBackground;

    public NewGameView() {
        this.InitialiseNodes();
        this.layoutNodes();
    }

    public void InitialiseNodes() {
        // Controls
        lblName = new Label[2];
        lblName[0] = new Label("Player 1");
        lblName[1] = new Label("Player 2");

        lblTxtName = new Label[2];
        lblTxtName[0] = new Label("Name");
        lblTxtName[1] = new Label("Name");

        txtName = new TextField[2];
        txtName[0] = new TextField();
        txtName[1] = new TextField();

        lblCpColor = new Label[2];
        lblCpColor[0] = new Label("Color");
        lblCpColor[1] = new Label("Color");

        cpColor = new ColorPicker[2];
        cpColor[0] = new ColorPicker();
        cpColor[1] = new ColorPicker();

        lblFlagTitle = new Label[2];
        lblFlagTitle[0] = new Label("Flag");
        lblFlagTitle[1] = new Label("Flag");

        btnFlag = new Button[2][4];
        btnFlag[0][0] = new Button();
        btnFlag[0][1] = new Button();
        btnFlag[0][2] = new Button();
        btnFlag[0][3] = new Button();
        btnFlag[1][0] = new Button();
        btnFlag[1][1] = new Button();
        btnFlag[1][2] = new Button();
        btnFlag[1][3] = new Button();

        imgTitle = new ImageView("/title.png");
        btnReady = new Button("Ready");
        btnCancel = new Button("Cancel");

        // Panes
        hbName = new HBox[2];
        hbName[0] = new HBox();
        hbName[1] = new HBox();

        hbColor = new HBox[2];
        hbColor[0] = new HBox();
        hbColor[1] = new HBox();

        vbNameColor = new VBox[2];
        vbNameColor[0] = new VBox();
        vbNameColor[1] = new VBox();

        gpFlags = new GridPane[2];
        gpFlags[0] = new GridPane();
        gpFlags[1] = new GridPane();

        vbFlags = new VBox[2];
        vbFlags[0] = new VBox();
        vbFlags[1] = new VBox();

        vbPlayer = new VBox[2];
        vbPlayer[0] = new VBox();
        vbPlayer[1] = new VBox();

        vbButtons = new VBox();
        vbMenu = new VBox();
        hbBackground = new HBox();
    }

    public void layoutNodes() {
        ///Players
        for (Label lbl : lblName) {
            Style.txt(lbl, 20);
        }

        ////Name
        for (Label lbl : lblTxtName) {
            Style.txt(lbl, 15);
        }

        for (TextField txt : txtName) {
            txt.setPrefWidth(Style.size(200));
        }

        for (int i = 0; i < hbName.length; i++) {
            hbName[i].getChildren().addAll(lblTxtName[i], txtName[i]);
            hbName[i].setAlignment(Pos.CENTER);
            hbName[i].setSpacing(Style.size(20));
        }

        ////Color
        for (Label lbl : lblCpColor) {
            Style.txt(lbl, 15);
        }

        for (ColorPicker cp : cpColor) {
            cp.setPrefWidth(Style.size(200));
        }
        cpColor[0].setValue(Color.DARKBLUE);
        cpColor[1].setValue(Color.DARKRED);

        for (int i = 0; i < hbName.length; i++) {
            hbColor[i].getChildren().addAll(lblCpColor[i], cpColor[i]);
            hbColor[i].setAlignment(Pos.CENTER);
            hbColor[i].setSpacing(Style.size(25));
        }

        ////Name en Color
        for (int i = 0; i < vbNameColor.length; i++) {
            vbNameColor[i].getChildren().addAll(lblName[i], hbName[i], hbColor[i]);
            vbNameColor[i].setAlignment(Pos.CENTER);
            vbNameColor[i].setSpacing(Style.size(20));
        }

        ////Flags
        for (Label lbl : lblFlagTitle) {
            Style.txt(lbl, 15);
        }

        for (Button[] playerButtons : btnFlag) {
            for (Button btn : playerButtons) {
                btn.setPrefSize(Style.size(125), Style.size(70));
                btn.setBorder(Style.border(Color.WHITE));
            }
            playerButtons[0].setBackground(Style.bgImage("/pieces/flag.png", false));
            playerButtons[1].setBackground(Style.bgImage("/pieces/flag_belguim.png", false));
            playerButtons[2].setBackground(Style.bgImage("/pieces/flag_europe.gif", false));
            playerButtons[3].setBackground(Style.bgImage("/pieces/flag.png", false));
        }

        for (int i = 0; i < gpFlags.length; i++) {
            gpFlags[i].add(btnFlag[i][0], 0, 0);
            gpFlags[i].add(btnFlag[i][1], 1, 0);
            gpFlags[i].add(btnFlag[i][2], 0, 1);
            gpFlags[i].add(btnFlag[i][3], 1, 1);

            gpFlags[i].setHgap(Style.size(10));
            gpFlags[i].setVgap(Style.size(10));
            gpFlags[i].setAlignment(Pos.CENTER);
        }

        for (int i = 0; i < vbFlags.length; i++) {
            vbFlags[i].getChildren().addAll(lblFlagTitle[i], gpFlags[i]);
            vbFlags[i].setAlignment(Pos.CENTER);
            vbFlags[i].setSpacing(Style.size(20));
        }

        ////Pane
        for (int i = 0; i < vbPlayer.length; i++) {
            vbPlayer[i].getChildren().addAll(vbNameColor[i], vbFlags[i]);
            vbPlayer[i].setAlignment(Pos.CENTER);
            vbPlayer[i].setSpacing(Style.size(60));
            vbPlayer[i].setPrefWidth(Style.size(600));
        }

        ///Menu
        imgTitle.setScaleX(Style.scale(1.5));
        imgTitle.setScaleY(Style.scale(1.5));

        Style.btn(btnReady, 15, 400, 50);
        Style.btn(btnCancel, 15, 400, 50);

        vbButtons.getChildren().addAll(btnReady, btnCancel);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(Style.size(20));

        vbMenu.getChildren().addAll(imgTitle, vbButtons);
        vbMenu.setAlignment(Pos.TOP_CENTER);
        vbMenu.setTranslateY(Style.size(150));
        vbMenu.setSpacing(Style.size(150));

        hbBackground.setBackground(Style.background);
        hbBackground.getChildren().addAll(vbPlayer[0], vbMenu, vbPlayer[1]);

        ///View
        this.getChildren().addAll(hbBackground);
        this.setSpacing(Style.size(20));
        this.setBackground(Style.bgApplication);
        this.setAlignment(Pos.CENTER);
    }

    public Label[] getLblName() {
        return lblName;
    }

    public TextField[] getTxtName() {
        return txtName;
    }

    public ColorPicker[] getCpColor() {
        return cpColor;
    }

    public Button[][] getBtnFlag() {
        return btnFlag;
    }

    public Button getBtnReady() {
        return btnReady;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }
}
