package com.example.uipalettes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class Flag
{
    @FXML
    ImageView imgFlag;
    @FXML
    Label countryName;
    @FXML
    Button btnBack;

    void setData(ImageView image, StringBuilder fileName)
    {
        fileName.deleteCharAt(fileName.length() - 1);
        fileName.deleteCharAt(fileName.length() - 1);
        fileName.deleteCharAt(fileName.length() - 1);
        fileName.deleteCharAt(fileName.length() - 1);
        imgFlag.setImage(image.getImage());
        countryName.setText(fileName.toString());
    }
    @FXML
    void goBack(ActionEvent ac) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main Screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) ac.getSource()).getScene().getWindow();
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
