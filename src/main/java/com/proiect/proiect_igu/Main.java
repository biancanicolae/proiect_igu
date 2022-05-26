package com.proiect.proiect_igu;

import com.proiect.proiect_igu.sample.ConfirmBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application {

    //declaram ferestra principala
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //configuram ferestra
        window = primaryStage;
        window.setTitle("Proiect IGU - Convert Image to Gray Scale");

        //configuram butonul de inchidere
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //incarcam interfata creata cu SceneBuilder
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));

        window.setScene(new Scene(root, 1500, 800));
        window.show();
    }

    private void closeProgram(){
        //functie pentru a inchide fereastra
        boolean answer = ConfirmBox.display("Exiting program","Are you sure you want to exit?");
        if(answer){
            window.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

