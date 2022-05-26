package com.proiect.proiect_igu.sample;
import com.proiect.proiect_igu.engine.ClasaAbstracta;
import com.proiect.proiect_igu.engine.TimesOne;
import com.proiect.proiect_igu.engine.TimesTwo;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.awt.image.BufferedImage;

public class ImageBox {

    public static String display(String bufferedImagePath, boolean isX1){
        //cream fereastra
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Zoomed Image");
        window.setMinWidth(1000);
        window.setMinHeight(1000);

        //declaratia variabilelor de care avem nevoie
        ClasaAbstracta obiect;
        Image image;
        String result;

        //in funcie de ce zoom ne dorim, apelam metoda necesara
        if(isX1){
            obiect = new TimesOne();
            BufferedImage capture = obiect.zoom(bufferedImagePath,"");
            image = SwingFXUtils.toFXImage(capture, null);
        }
        else{
            obiect = new TimesTwo();
            BufferedImage capture = obiect.zoom(bufferedImagePath,"");
            image = SwingFXUtils.toFXImage(capture, null);
        }

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

        //returnam imaginea generata
        return result;
    }
}
