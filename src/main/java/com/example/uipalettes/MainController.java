package com.example.uipalettes;

import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //Declarations

    //Node
    @FXML
    BorderPane homePane;
    @FXML
    BorderPane titleBar, borderHome;
    @FXML
    TilePane canvas;
    @FXML
    Button btnExit, btnMin, btnMax;

    @FXML
    Button handToolBtn, eyeDropperBtn;

    //Doubles

    private double xOffset = 0;
    private double yOffset = 0;

    /*
     * Setting up the titlebar
     * Minimize, Maximize and Close
     */

    //Hovering
    public void onExitIn()
    {
        btnExit.setStyle("-fx-background-color: red;");
    }

    public void onExitOut()
    {
        btnExit.setStyle("-fx-background-color: transparent;");
    }

    public void onMaxIn()
    {
        btnMax.setStyle("-fx-background-color:  #333;");
    }

    public void onMaxOut()
    {
        btnMax.setStyle("-fx-background-color:  transparent;");
    }

    public void onMinIn()
    {
        btnMin.setStyle("-fx-background-color:  #333;");
    }

    public void onMinOut()
    {
        btnMin.setStyle("-fx-background-color:  transparent;");
    }

    /*
     * This window has a custom TitleBar, onDrag(MouseEvent event) drags the window around
     * !LOGICAL::On first click sets window origin to cursor
     */
    @FXML
    protected void onDrag(MouseEvent event)
    {
        //When dragging Window
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if (stage.isMaximized())
        {
            stage.setMaximized(false);
        }
        titleBar.setOnMousePressed(event1 -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });
        titleBar.setOnMouseDragged(event12 -> {
            stage.setX(event12.getScreenX() - xOffset);
            stage.setY(event12.getScreenY() - yOffset);
        });
    }


    @FXML
    protected void fExit()
    {
        //Exit program
        btnExit.fire();
    }
    @FXML
    protected void exit(ActionEvent event)
    {
        //Close program
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void min(ActionEvent event)
    {
        //Minimize activity
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    protected void max(ActionEvent event)
    {
        //Maximize window
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    //Initial Load Canvas Buttons

    /*
     * enableToolButtons() sets any buttons in the toolbar to enabled
     * It is called whenever a tool is changed to make it possible to switch between tools
     */

    private void enableToolButtons()
    {
        handToolBtn.setDisable(false);
        handToolBtn.setStyle("-fx-background-color:  transparent;");
        eyeDropperBtn.setDisable(false);
        eyeDropperBtn.setStyle("-fx-background-color:  transparent;");
    }
    /*
     * Tools
     * Below are tool button Theme/Hover effects
     */

    public void handToolIn()
    {
        if (!handToolBtn.isDisabled())
            handToolBtn.setStyle("-fx-background-color:  #333;");
    }

    public void handToolOut()
    {
        if (!handToolBtn.isDisabled())
            handToolBtn.setStyle("-fx-background-color:  transparent;");
    }

    public void eyeDropperIn()
    {
        if (!eyeDropperBtn.isDisabled())
            eyeDropperBtn.setStyle("-fx-background-color:  #333;");
    }

    public void eyeDropperOut()
    {
        if (!eyeDropperBtn.isDisabled())
            eyeDropperBtn.setStyle("-fx-background-color:  transparent;");
    }

    /*
     * Below are tool implementations
     */

    public void handTool()
    {
        enableToolButtons();
        handToolBtn.setDisable(true);
        handToolBtn.setStyle("-fx-background-color:  #333;");
    }
    public void eyeDropperTool()
    {
        enableToolButtons();
        eyeDropperBtn.setDisable(true);
        eyeDropperBtn.setStyle("-fx-background-color:  #333;");
    }
    private void loadCanvas()
    {
        String path = "C:\\Users\\Sarah\\IdeaProjects\\Flags\\src\\main\\resources\\com\\example\\uipalettes\\img";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;

        for (final File file : listOfFiles)
        {
            ImageView image;
            image = createImageView(file);
            image.setCursor(Cursor.HAND);
            image.setSmooth(true);

            VBox imageCard = new VBox();
            imageCard.setAlignment(Pos.CENTER);
            imageCard.setPrefWidth(150);
            imageCard.setPrefHeight(190);
            imageCard.setPadding(new Insets(8));
            imageCard.setCursor(Cursor.HAND);

            Button fileName = new Button();
            fileName.setText(file.getName());
            fileName.setStyle("-fx-text-fill: #999; -fx-background-color: transparent; -fx-border-color: transparent");
            fileName.setCursor(Cursor.HAND);

            imageCard.getChildren().addAll(image, fileName);
            imageCard.setStyle("-fx-background-color: #111; -fx-background-radius: 8px;");

            imageCard.setOnMouseEntered(mouseEvent ->
            {
                imageCard.setStyle("-fx-background-color: #222; -fx-background-radius: 8px;");
                fileName.setStyle("-fx-text-fill: skyblue; -fx-background-color: transparent; -fx-border-color: transparent");
            });
            imageCard.setOnMouseExited(mouseEvent ->
            {
                imageCard.setStyle("-fx-background-color: #111; -fx-background-radius: 8px;");
                fileName.setStyle("-fx-text-fill: #999; -fx-background-color: transparent; -fx-border-color: transparent");
            });

            canvas.getChildren().addAll(imageCard);
        }

    }
    private ImageView createImageView(final File imageFile) {
        // DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
        // The last two arguments are: preserveRatio, and use smooth (slower)
        // resizing

        ImageView imageView = null;

        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true, true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);

            ImageView finalImageView = imageView;
            imageView.setOnMouseClicked(mouseEvent -> {

                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                    if(mouseEvent.getClickCount() == 2){
                        try
                        {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("flag.fxml"));
                            BorderPane BP = fxmlLoader.load();
                            Flag cardController = fxmlLoader.getController();
                            StringBuilder FileName = new StringBuilder(imageFile.getName());
                            cardController.setData(finalImageView, FileName);
                            borderHome.setCenter(BP);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }

    /*
     * Below is where the canvas is set up before it is loaded
     */
    private void setUpListeners()
    {
        homePane.setOnKeyPressed(keyEvent -> homePane.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent1 -> {
            if (keyEvent1.getCode() == KeyCode.H)
            {
                handTool();
            }
            else if (keyEvent1.getCode() == KeyCode.E)
            {
                eyeDropperTool();
            }

        }));
        final KeyCombination keyCombinationShiftC = new KeyCodeCombination(
                KeyCode.C, KeyCombination.CONTROL_DOWN);

        homePane.setOnKeyPressed(event -> {
            if (keyCombinationShiftC.match(event)) {
                fExit();
            }
        });
    }
    private void setUpTooltips()
    {
        handToolBtn.setTooltip(new Tooltip("Navigate easily"));
        eyeDropperBtn.setTooltip(new Tooltip("Copy colours"));

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        setUpListeners();
        setUpTooltips();
        loadCanvas();
    }

}

/*

package com.example.uipalettes;

import ColorCard.ColorClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GeneratePalettes implements Initializable
{
    public ScrollPane scrollPaneTrending;
    @FXML
    BorderPane homePane;
    @FXML
    HBox trendingColorsLayout;
    @FXML
    GridPane paletteList;

    private List<ColorClass> recentlyAdded()
    {
        List<ColorClass> ls = new ArrayList<>();
        ColorClass colorClass;

        colorClass = new ColorClass();
        colorClass.setColor1("1D1E18");
        colorClass.setColor2("6B8F71");
        colorClass.setColor3("AAD2BA");
        colorClass.setColor4("D9FFF5");
        colorClass.setColor5("B9F5D8");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("4E5166");
        colorClass.setColor2("C98686");
        colorClass.setColor3("F2B880");
        colorClass.setColor4("FFF4EC");
        colorClass.setColor5("E7CFBC");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("966B9D");
        colorClass.setColor2("7C90A0");
        colorClass.setColor3("B5AA9D");
        colorClass.setColor4("B9B7A7");
        colorClass.setColor5("747274");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("C5D1EB");
        colorClass.setColor2("92AFD7");
        colorClass.setColor3("5A7684");
        colorClass.setColor4("395B50");
        colorClass.setColor5("1F2F16");

        ls.add(colorClass);

        return ls;
    }
    private List<ColorClass> mainPalettes()
    {
        List<ColorClass> ls = new ArrayList<>();
        ColorClass colorClass = new ColorClass();
        colorClass.setColor1("817F82");
        colorClass.setColor2("AE8CA3");
        colorClass.setColor3("E7DFC6");
        colorClass.setColor4("6B8F71");
        colorClass.setColor5("D9FFF5");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("1D1E18");
        colorClass.setColor2("6B8F71");
        colorClass.setColor3("AAD2BA");
        colorClass.setColor4("D9FFF5");
        colorClass.setColor5("B9F5D8");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("4E5166");
        colorClass.setColor2("C98686");
        colorClass.setColor3("F2B880");
        colorClass.setColor4("FFF4EC");
        colorClass.setColor5("E7CFBC");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("966B9D");
        colorClass.setColor2("7C90A0");
        colorClass.setColor3("B5AA9D");
        colorClass.setColor4("B9B7A7");
        colorClass.setColor5("747274");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("571F4E");
        colorClass.setColor2("5D5179");
        colorClass.setColor3("4F759B");
        colorClass.setColor4("92C9B1");
        colorClass.setColor5("A2FAA3");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("C5D1EB");
        colorClass.setColor2("92AFD7");
        colorClass.setColor3("5A7684");
        colorClass.setColor4("395B50");
        colorClass.setColor5("1F2F16");

        ls.add(colorClass);

        colorClass.setColor1("817F82");
        colorClass.setColor2("AE8CA3");
        colorClass.setColor3("E7DFC6");
        colorClass.setColor4("6B8F71");
        colorClass.setColor5("D9FFF5");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("1D1E18");
        colorClass.setColor2("6B8F71");
        colorClass.setColor3("AAD2BA");
        colorClass.setColor4("D9FFF5");
        colorClass.setColor5("B9F5D8");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("4E5166");
        colorClass.setColor2("C98686");
        colorClass.setColor3("F2B880");
        colorClass.setColor4("FFF4EC");
        colorClass.setColor5("E7CFBC");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("966B9D");
        colorClass.setColor2("7C90A0");
        colorClass.setColor3("B5AA9D");
        colorClass.setColor4("B9B7A7");
        colorClass.setColor5("747274");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("571F4E");
        colorClass.setColor2("5D5179");
        colorClass.setColor3("4F759B");
        colorClass.setColor4("92C9B1");
        colorClass.setColor5("A2FAA3");

        ls.add(colorClass);

        colorClass = new ColorClass();
        colorClass.setColor1("C5D1EB");
        colorClass.setColor2("92AFD7");
        colorClass.setColor3("5A7684");
        colorClass.setColor4("395B50");
        colorClass.setColor5("1F2F16");

        ls.add(colorClass);

        return ls;
    }

    private boolean prepareStage() throws IOException
    {
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<ColorClass> recentlyAdded = new ArrayList<>(recentlyAdded());
        List<ColorClass> mainPalettes = new ArrayList<>(mainPalettes());

        try
        {
            if (!prepareStage())
            {
                return;
            }
            for (ColorClass colorClass : recentlyAdded)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("paletteCard.fxml"));
                HBox cardBox = fxmlLoader.load();
                Card cardController = fxmlLoader.getController();
                cardController.setData(colorClass);
                cardBox.setStyle("-fx-background-color: transparent;");
                trendingColorsLayout.getChildren().add(cardBox);
            }
            int row = 1, column = 0;
            for (ColorClass colorClass : mainPalettes)
            {
                if (column == 4)
                {
                    column = 0;
                    row++;
                }
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("paletteCard.fxml"));
                HBox cardBox = fxmlLoader.load();
                Card cardController = fxmlLoader.getController();
                cardController.setData(colorClass);
                cardBox.setStyle("-fx-background-color: transparent;");

                paletteList.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}



 */