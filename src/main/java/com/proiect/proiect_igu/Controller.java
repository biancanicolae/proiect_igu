package com.proiect.proiect_igu;

import com.proiect.proiect_igu.sample.*;
import com.proiect.proiect_igu.sample.TableRowClass;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    //elementele de interfata corespunzatoare celor din fisierul fxml
    @FXML
    public TextField selectedFilePath;
    @FXML
    public RadioButton isX1Selected;
    @FXML
    public RadioButton isX2Selected;
    @FXML
    public ListView historyListView;
    @FXML
    public ProgressBar progressBar;
    @FXML
    public ProgressIndicator progressIndicator;
    @FXML
    public ImageView imageView;
    @FXML
    public TableView<TableRowClass> infoTableView;

    public void selectFileButtonClicked(){
        //cand apasam pe butonul de selectie al unui fisier este deschisa o fereastra de selectie
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select BMP File");

        /*File initialFile = new File("E:\\Facultate\\Anul 4\\Semestrul 2\\IGU\\Proiect\\src\\main\\resources\\landscape.bmp");
        if(initialFile.exists()){
            fileChooser.setInitialDirectory(initialFile);
        }*/

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        //daca a fost selectat un fisier incercam sa citim imaginea selectata
        if (file != null) {
            try{
                selectedFilePath.setText(file.getAbsolutePath());
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
            }
            catch (Exception e){
                System.out.println(file.toURI().toString());
                System.out.println("Some error here" + e);
            }
        }
    }

    public void zoomButtonClicked(){
        //daca calea selectata exista
        if(!selectedFilePath.getText().isEmpty()){
            //adaugam in lista cu istoricul selectilor fisierul caruia ii aplicam zoom-ul
            historyListView.getItems().add(selectedFilePath.getText());
            File myNewFile = new File(selectedFilePath.getText());

            //daca fisierul selectat exista
            if(myNewFile.exists()){
                //adaugam in tabel numele si dimensiunea si afisam imaginea
                String result = ImageBox.display(selectedFilePath.getText(),isX1Selected.isSelected());
                infoTableView.getItems().add(new TableRowClass(myNewFile.getName(), result));

                //afisam progresul
                progressBar.setProgress(100);
                progressIndicator.setProgress(100);
            }
            else{
                AlertBox.display("Oops!","Nu am gasit fisierul selectat!");
            }
        }
        else{
            AlertBox.display("Oops!","Trebuie sa selectati un fisier!");
        }
    }

    public void convertButtonClicked(){
        //daca calea selectata exista
        if(!selectedFilePath.getText().isEmpty()){
            //adaugam in lista cu istoricul selectilor fisierul caruia ii aplicam zoom-ul
            historyListView.getItems().add(selectedFilePath.getText());
            File myNewFile = new File(selectedFilePath.getText());

            //daca fisierul selectat exista
            if(myNewFile.exists()){
                //adaugam in tabel numele si dimensiunea si afisam imaginea
                String result = ConvertedImageBox. display(selectedFilePath.getText());
                infoTableView.getItems().add(new TableRowClass(myNewFile.getName(), result));

                //afisam progresul
                progressBar.setProgress(100);
                progressIndicator.setProgress(100);
            }
            else{
                AlertBox.display("Oops!","Nu am gasit fisierul selectat!");
            }
        }
        else{
            AlertBox.display("Oops!","Trebuie sa selectati un fisier!");
        }
    }

    public void nothingButtonClicked(){
        //deschidem ferestra de alerta
        AlertBox.display("This is AlertBox!","This is AlertBox!");
    }

    public void beautifulNothingButtonClicked(){
        //deschidem ferestra FillerBox
        FillerBox.display("This is FillerBox!","This is FillerBox!");
    }

    public void x1CheckBoxClicked(){
        //implementam logica de a apasa pe un RadioButton
        if(!isX1Selected.isSelected()){
            isX1Selected.setSelected(true);
            isX2Selected.setSelected(false);
        }
        else {
            isX2Selected.setSelected(false);
        }
    }

    public void x2CheckBoxClicked(){
        //implementam logica de a apasa pe un RadioButton
        if(!isX2Selected.isSelected()){
            isX2Selected.setSelected(true);
            isX1Selected.setSelected(false);
        }
        else{
            isX1Selected.setSelected(false);
        }
    }
}
