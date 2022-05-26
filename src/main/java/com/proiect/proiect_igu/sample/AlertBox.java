package com.proiect.proiect_igu.sample;

import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

    public static void display(String title, String message){
        //cream fereastra
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);

        //cream elementele ce vor fi afisate in fereastra
        Label label = new Label();
        label.setText(message);
        Button closeButton  = new Button("Ok.");
        closeButton.setOnAction(e -> window.close());

        //cream o caseta verticala si adaugam elementele
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);

        //punem in fereastra caseta verticala
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
