package com.example.uipalettes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Flag implements Initializable
{
    @FXML
    ImageView imgFlag;
    @FXML
    Label countryName, countryInfo;
    @FXML
    Button btnBack;

    void setData(ImageView image, StringBuilder fileName) throws FileNotFoundException {
        fileName.deleteCharAt(fileName.length() - 1);
        fileName.deleteCharAt(fileName.length() - 1);
        fileName.deleteCharAt(fileName.length() - 1);
        fileName.deleteCharAt(fileName.length() - 1);
        imgFlag.setImage(image.getImage());
        countryName.setText(fileName.toString());
        setCountryInfo(fileName);
    }
    void setCountryInfo(StringBuilder countryName) throws FileNotFoundException {
        File file = new File("L:\\Database\\Cpp\\current_output.txt");

        for (int i = 0; i < countryName.length(); i++)
        {
            if (countryName.charAt(i) == ' ')
            {
                countryName.setCharAt(i, '-');
            }
        }
        countryInfo.setText(countryName.toString());

        Scanner scanner = new Scanner(file);

        String name;

        while(scanner.hasNextLine())
        {
            name = scanner.nextLine();
            String longString = name;
            name = wordSplit(name, 2);
            StringBuilder newS = new StringBuilder(this.countryInfo.getText());
            String[] temp = wordSplit1(newS.toString());
            newS = new StringBuilder();
            for (String s : temp) {
                newS.append(s);
            }
            //System.out.println(name + " | " + newS);
            if (name.equals(newS.toString()))
            {
                String r = "";
                r += countryName + "\n\n" + wordSplit(longString, 0) + "\n\n" + wordSplit(longString, 1) + "\n\n" +
                        wordSplit(longString, 3) + "\n\n" + wordSplit(longString, 4) + "\n\n" + wordSplit(longString, 5) + "\n\n" +
                        wordSplit(longString, 6) + "\n\n" + wordSplit(longString, 7) + "\n\n" + wordSplit(longString, 8);
                countryInfo.setText(r);
            }
        }
    }
    String wordSplit(String s, int val)
    {
        String[] words = s.split("\t");

        String[] words1 = words[val].split("-");
        StringBuilder Return = new StringBuilder();

        for (String value : words1) {
            Return.append(value);
        }
        return Return.toString();
    }
    String[] wordSplit1(String s)
    {
        return s.split("\\W+");
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

    public void backIn()
    {
        btnBack.setStyle("-fx-background-color:  #111111; -fx-border-color:  #111; -fx-background-radius: 8px; -fx-border-radius: 8px; -fx-text-fill: ghostwhite;");
    }

    public void backOut()
    {
        btnBack.setStyle("-fx-background-color:  #11111115; -fx-border-color:  #111; -fx-text-fill: #111;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}

