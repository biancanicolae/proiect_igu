package com.proiect.proiect_igu.sample;

import javafx.beans.property.SimpleStringProperty;

public class TableRowClass {
    //clasa ajutaotare ce contine elemntele ce vor fii introduse in tabel
    public SimpleStringProperty name;
    public SimpleStringProperty size;

    public TableRowClass(String name, String size){
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleStringProperty(size);
    }

    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public String getSize(){
        return this.size.get();
    }

    public void setSize(String size){
        this.size.set(size);
    }
}
