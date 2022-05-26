//clasa abstracta ImageAbstract face parte din package-ul imageProcessing
package com.proiect.proiect_igu.imageProcessing;

//clasa abstracta implementeaza intefata ImageInterface
public abstract class ImageAbstract implements ImageInterface {
    int height;
    int width;
    Pixel[][] Pixels;
    
 
    //constructor fara parametri
    public ImageAbstract() {
    }
    
    //constructor cu parametrii( inaltimea si latimea imaginii)
    public ImageAbstract(int height, int width) {
        this.height = height;
        this.width = width;
        Pixels = createPixelMatrix(height,width);
    }
    
    //metoda ce creeaza matricea de pixeli a imaginii
    public Pixel[][] createPixelMatrix(int height,int width){
    	
        Pixel[][] myPixels= new Pixel[height][width];
        
        for(int i = 0 ; i< height ; i++ )
        {
            for(int j = 0 ; j < width ; j ++ )
            {
                myPixels[i][j] = new Pixel();
                myPixels[i][j].setRGB(0,0,0);
            }
        }

        return myPixels;
    }
     
    
    //definire matoda pentru conversia imaginii
	public abstract Pixel[][] convertToGrayScale();
    
	//metoda ce returneaza inaltimea imaginii
    public int getHeight() {
        return height;
    }

  //metoda ce seteaza inaltimea imaginii
    public void setHeight(int height) {
        this.height = height;
    }

  //metoda ce returneaza latimea imaginii
    public int getWidth() {
        return width;
    }

  //metoda ce seteaza latimea imaginii
    public void setWidth(int width) {
        this.width = width;
    }

  //metoda ce returneaza matricea de pixeli
    public Pixel[][] getPixels() {
        return Pixels;
    }

    public void setPixels(Pixel[][] Pix) {
        //se parcurge matricea de pixeli si pentru fiecare pixel 
    	//se seteaza atributele cu aceleasi ca matricea de pixeli primita ca parametru
        for(int i = 0 ; i < height ; i++ )
        {
            for(int j = 0 ; j < width ; j++ )
            {
                this.Pixels[i][j].setRGB(Pix[i][j].getRed(),Pix[i][j].getGreen(),Pix[i][j].getBlue());
            }
        }
    }
    
}