package com.proiect.proiect_igu.sample;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ConfirmBox {

    static boolean answear;

    public static boolean display(String title, String message){
        //cream fereastra
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        //cream elementele ce vor fi afisate in fereastra
        //(simple label, graphic label, hyperlink, graphic button)
        Label label = new Label();
        label.setText(message);
        Label img = new Label();
        Image imageInfo = new Image("file:adv_grf.png");
        ImageView imageViewInfo = new ImageView(imageInfo);
        imageViewInfo.setPreserveRatio(true);
        img.setGraphic(imageViewInfo);

        Hyperlink link = new Hyperlink();
        String url = "https://openjfx.io/";
        link.setText(url);
        link.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URL(url).toURI());
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        HBox buttons = new HBox(20);

        Image imageYes = new Image("file:yes.png");
        ImageView imageViewYes = new ImageView(imageYes);
        imageViewYes.setFitHeight(20);
        imageViewYes.setFitWidth(40);
        Button yesButton  = new Button("Yes", imageViewYes);
        yesButton.setOnAction(e -> {
            answear = true;
            window.close();
        });

        Image imageNo = new Image("file:no.png");
        ImageView imageViewNo = new ImageView(imageNo);
        imageViewNo.setFitHeight(20);
        imageViewNo.setFitWidth(40);
        Button noButton  = new Button("No", imageViewNo);
        noButton.setOnAction(e -> {
            answear = false;
            window.close();
        });

        buttons.getChildren().addAll(yesButton,noButton);
        buttons.setAlignment(Pos.CENTER);

        //cream o caseta verticala si adaugam elementele
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,img,link,buttons);
        layout.setAlignment(Pos.CENTER);

        //punem in fereastra caseta verticala
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //returnam raspunsul casetei
        return answear;
    }
}
