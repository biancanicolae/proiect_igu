//clasa Pixel face parte din packege-ul imageProcessing
package com.proiect.proiect_igu.imageProcessing;

public class Pixel {
    private int red;
    private int green;
    private int blue;

    //constructor folosit pentru initializare variabile
    public Pixel() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }
    
    //constructor cu parametrii
    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    //metoda ce returneaza componenta red a unui pixel
    public int getRed() {
        return red;
    }

    //metoda ce seteaza componenta red a unui pixel
    public void setRed(int red) {
        this.red = red;
    }

    //metoda ce returneaza componenta green a unui pixel
    public int getGreen() {
        return green;
    }

  //metoda ce seteaza componenta green a unui pixel
    public void setGreen(int green) {
        this.green = green;
    }

  //metoda ce returneaza componenta blue a unui pixel
    public int getBlue() {
        return blue;
    }

  //metoda ce seteaza componenta blue a unui pixel
    public void setBlue(int blue) {
        this.blue = blue;
    }
    
  //metoda ce seteaza componentele RGB ale unui pixel
    public void setRGB(int red, int green, int blue)
    {
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
        
        
    }

    //metoda care preia rezultatul functiei getRGB( metoda ce aprtine clasei BufferedImage) 
    //si "desparte" rezultatul in culorile RGB care compun pixelul
    public void setColours(int colour){
        blue = colour & 0xff;
        green = (colour>>8)&0xff;
        red = (colour>>16)&0xff;
    }
}
