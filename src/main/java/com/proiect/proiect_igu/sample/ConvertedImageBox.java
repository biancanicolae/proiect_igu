package com.proiect.proiect_igu.sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;



public class ConvertedImageBox {

    public static String display(String bufferedImagePath){
        //cream fereastra
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Converted Image");
        window.setMinWidth(1000);
        window.setMinHeight(1000);

        //declaratia variabilelor de care avem nevoie
        com.proiect.proiect_igu.imageProcessing.Image img = new com.proiect.proiect_igu.imageProcessing.Image();
        Image image;
        String result = null;

        try {
            BufferedImage capture = img.readImage(bufferedImagePath);
            capture = img.saveConvertedImage(bufferedImagePath);
            image = SwingFXUtils.toFXImage(capture, null);

            //cream o caseta verticala si adaugam elementele
            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);

            ImageView imageView = new ImageView(image);
            layout.getChildren().addAll(imageView);
            result = "" + image.getWidth() + "x" + image.getHeight();

            //punem in fereastra casetele verticale
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();

            
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //returnam imaginea generata
        return result;
    }
}
