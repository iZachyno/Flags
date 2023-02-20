package com.example.uipalettes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public MenuBar menuBar;

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
        String path = "C:\\Users\\Sarah\\Videos\\Anime\\Flags";

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
                        }
                        catch (Exception e)
                        {
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
