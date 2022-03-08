package be.kdg.stratego.view.newgame;

import be.kdg.stratego.view.Style;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class NewGameView extends HBox {
    // Controls
    private Label[] lblName;

    private Label[] lblTxtName;

    private TextField[] txtName;

    private Label[] lblCpColor;

    private ColorPicker[] cpColor;

    private Label[] lblFlagTitle;

    private GridPane[] gpFlags;

    private Button[][] btnFlag;

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

    private HBox hb;

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
        btnFlag[0][0] = new Button("Flag 1");
        btnFlag[0][1] = new Button("Flag 2");
        btnFlag[0][2] = new Button("Flag 3");
        btnFlag[0][3] = new Button("Flag 4");
        btnFlag[1][0] = new Button("Flag 1");
        btnFlag[1][1] = new Button("Flag 2");
        btnFlag[1][2] = new Button("Flag 3");
        btnFlag[1][3] = new Button("Flag 4");

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

        hb = new HBox();
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
            txt.setPrefWidth(Style.width(200));
        }

        for (int i = 0; i < hbName.length; i++) {
            hbName[i].getChildren().addAll(lblTxtName[i], txtName[i]);
            hbName[i].setAlignment(Pos.CENTER);
            hbName[i].setSpacing(Style.width(20));
        }

        ////Color
        for (Label lbl : lblCpColor) {
            Style.txt(lbl, 15);
        }

        for (ColorPicker cp : cpColor) {
            cp.setPrefWidth(Style.width(200));
        }

        for (int i = 0; i < hbName.length; i++) {
            hbColor[i].getChildren().addAll(lblCpColor[i], cpColor[i]);
            hbColor[i].setAlignment(Pos.CENTER);
            hbColor[i].setSpacing(Style.width(25));
        }

        ////Name en Color
        for (int i = 0; i < vbNameColor.length; i++) {
            vbNameColor[i].getChildren().addAll(lblName[i], hbName[i], hbColor[i]);
            vbNameColor[i].setAlignment(Pos.CENTER);
            vbNameColor[i].setSpacing(Style.width(20));
        }

        ////Flags
        for (Label lbl : lblFlagTitle) {
            Style.txt(lbl, 15);
        }

        for (Button[] player : btnFlag) {
            for (Button btn : player) {
                btn.setPrefSize(Style.width(125), Style.height(70));
            }
        }

        for (int i = 0; i < gpFlags.length; i++) {
            gpFlags[i].add(btnFlag[i][0], 0, 0);
            gpFlags[i].add(btnFlag[i][1], 1, 0);
            gpFlags[i].add(btnFlag[i][2], 0, 1);
            gpFlags[i].add(btnFlag[i][3], 1, 1);

            gpFlags[i].setHgap(Style.width(10));
            gpFlags[i].setVgap(Style.height(10));
            gpFlags[i].setAlignment(Pos.CENTER);
        }

        for (int i = 0; i < vbFlags.length; i++) {
            vbFlags[i].getChildren().addAll(lblFlagTitle[i], gpFlags[i]);
            vbFlags[i].setAlignment(Pos.CENTER);
            vbFlags[i].setSpacing(Style.height(20));
        }

        ////Pane
        for (int i = 0; i < vbPlayer.length; i++) {
            vbPlayer[i].getChildren().addAll(vbNameColor[i], vbFlags[i]);
            vbPlayer[i].setAlignment(Pos.CENTER);
            vbPlayer[i].setSpacing(Style.height(60));
            vbPlayer[i].setPrefWidth(Style.height(600));
        }

        ///Menu
        imgTitle.setScaleX(1.5);
        imgTitle.setScaleY(1.5);

        Style.btn(btnReady, 15);
        Style.btn(btnCancel, 15);

        vbButtons.getChildren().addAll(btnReady, btnCancel);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(Style.height(20));

        vbMenu.getChildren().addAll(imgTitle, vbButtons);
        vbMenu.setAlignment(Pos.CENTER);
        vbMenu.setSpacing(Style.height(150));
        vbMenu.setTranslateY(Style.height(-140));

        hb.setBackground(Style.background);
        hb.getChildren().addAll(vbPlayer[0], vbMenu, vbPlayer[1]);

        ///View
        this.getChildren().addAll(hb);
        this.setSpacing(Style.width(20));
        this.setBackground(Style.applicationBackground);
        this.setAlignment(Pos.CENTER);
    }

    public Label[] getLblName() {
        return lblName;
    }

    public Label[] getLblTxtName() {
        return lblTxtName;
    }

    public TextField[] getTxtName() {
        return txtName;
    }

    public Label[] getLblCpColor() {
        return lblCpColor;
    }

    public ColorPicker[] getCpColor() {
        return cpColor;
    }

    public Label[] getLblFlagTitle() {
        return lblFlagTitle;
    }

    public GridPane[] getGpFlags() {
        return gpFlags;
    }

    public Button[][] getBtnFlag() {
        return btnFlag;
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

    public VBox[] getVbPlayer() {
        return vbPlayer;
    }

    public HBox[] getHbName() {
        return hbName;
    }

    public HBox[] getHbColor() {
        return hbColor;
    }

    public VBox[] getVbNameColor() {
        return vbNameColor;
    }

    public VBox[] getVbFlags() {
        return vbFlags;
    }

    public VBox getVbMenu() {
        return vbMenu;
    }

    public VBox getVbButtons() {
        return vbButtons;
    }

    public HBox getHb() {
        return hb;
    }
}
