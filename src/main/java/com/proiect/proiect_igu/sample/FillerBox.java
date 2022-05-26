package com.proiect.proiect_igu.sample;

import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class FillerBox {

    public static void display(String title, String message){
        //cream fereastra
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);

        //cream elementele ce vor fi afisate in fereastra
        //(simple label, colored button, toggle buttons, checkbox)
        Label label = new Label();
        label.setText(message);

        // Creating a Label
        Label graphicLabel = new Label("This is a graphic label");

        // Creating a graphic (image)
        Image imgLabel = new Image("file:src/main/resources/label.png");
        ImageView viewLabel = new ImageView(imgLabel);
        viewLabel.setFitHeight(80);
        viewLabel.setPreserveRatio(true);
        graphicLabel.setGraphic(viewLabel);

        // Setting font to the label
        Font font = Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 25);
        graphicLabel.setFont(font);

        // Filling color to the label
        graphicLabel.setTextFill(Color.BROWN);


        Image img = new Image("file:src/main/resources/button.png");
        ImageView view = new ImageView(img);

        view.setFitHeight(80);
        view.setPreserveRatio(true);
        //cream un buton
        Button button = new Button();
        //setam dimensiunea butonului
        button.setPrefSize(80, 80);
        //adaugam o imagine butonului
        button.setGraphic(view);
        button.setOnAction(e -> label.setText("The graphic button has been pressed!"));

        Button coloredButton = new Button("Colored Button");
        coloredButton.setStyle("-fx-background-color:\n" +
                "        linear-gradient(#f0ff35, #a9ff00),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);\n" +
                "    -fx-background-radius: 6, 5;\n" +
                "    -fx-background-insets: 0, 1;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-text-fill: #395306;");
        coloredButton.setOnAction(e -> label.setText("The colored button has been pressed!"));

        CheckBox cb1 = new CheckBox("Check Box");

        cb1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(!cb1.isSelected()){
                label.setText("Check Box not selected!");
            }
            else{
                label.setText("Check Box selected!");
            }
        });

        final ToggleGroup group = new ToggleGroup();

        ToggleButton tb1 = new ToggleButton("Toggle1");
        tb1.setToggleGroup(group);
        tb1.setSelected(true);

        ToggleButton tb2 = new ToggleButton("Toggle2");
        tb2.setToggleGroup(group);

        ToggleButton tb3 = new ToggleButton("Toggle3");
        tb3.setToggleGroup(group);

        tb1.setUserData("Toggle 1 has been toggled!");
        tb2.setUserData("Toggle 2 has been toggled!");
        tb3.setUserData("Toggle 3 has been toggled!");

        group.selectedToggleProperty().addListener((ov, toggle, new_toggle) -> {
            if (new_toggle == null)
                label.setText("No toggle!");
            else
                label.setText((String) group.getSelectedToggle().getUserData());
        });

        Button closeButton  = new Button("Ok.");
        closeButton.setOnAction(e -> window.close());

        //butoanele de toggle le punem intr-o caseta orizontala
        //pentru a le putea centra corespunzator
        HBox toggleGroup = new HBox(10);
        toggleGroup.getChildren().addAll(tb1,tb2,tb3);
        toggleGroup.setAlignment(Pos.CENTER);

        //cream o caseta verticala si adaugam elementele
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(graphicLabel,button,cb1,coloredButton,toggleGroup,label,closeButton);
        layout.setAlignment(Pos.CENTER);

        //punem in fereastra caseta verticala
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
