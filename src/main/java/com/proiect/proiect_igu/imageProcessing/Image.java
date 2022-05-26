//clasa ImageAbstract face parte din packege-ul imageProcessing
package com.proiect.proiect_igu.imageProcessing;

//importam clasele necesare procesarii imaginii si trataii exceptiilor
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

//importam clasele din packge-ul multitheading
import com.proiect.proiect_igu.multithreading.Buffer;
import com.proiect.proiect_igu.multithreading.Consumer;
import com.proiect.proiect_igu.multithreading.Producer;


//se creeaza o clasa Image ce mosteneste clasa abstracta ImageAbstract
public class Image extends ImageAbstract {

	//constructor fara parametrii al clasei Image
    public Image() {
    }

  //constructor cu parametrii parametrii( inaltimea si latimea imaginii) al clasei Image
    public Image(int height, int width) {
        super(height, width);
    }

  //constructor cu parametrii( inaltimea, latimea imaginii si matricea de pixeli) al clasei Image
    public Image(int height, int width, Pixel[][] Pixels) {
        super(height, width);
        setPixels(Pixels);
    }
    
    //metoda ce returneaza inaltimea imaginii
    @Override
    public int getHeight() {
        return height;
    }

    //metoda ce seteaza inaltimea imaginii
    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    //metoda ce returneaza latimea imaginii
    @Override
    public int getWidth() {
        return width;
    }

    //metoda ce seteaza latime a imaginii
    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    //metoda ce returneaza metricea de pixeli
    @Override
    public Pixel[][] getPixels() {
        return Pixels;
    }

    //metoda ce seteaza inaltimea imaginii
    @Override
    public void setPixels(Pixel[][] Pix) {
        //se parcurge matricea de pixeli si pentru fiecare pixel se seteaza atributele 
    	//cu aceleasi ca matricea de pixeli primita ca parametru
        for(int i = 0 ; i < height ; i++ )
        {
            for(int j = 0 ; j < width ; j++ )
            {
                this.Pixels[i][j].setRGB(Pix[i][j].getRed(),Pix[i][j].getGreen(),Pix[i][j].getBlue());
            }
        }
    }
       
   //parcurgem imaginea si setam pixelii preluati din imaginea citita
    @Override
    public BufferedImage readImage(String path) throws InterruptedException {
    	//variabila folosita pentru a vedea cand se incepe citirea imaginii
    	long startTime = System.nanoTime();
    	
    	Buffer buffer = new Buffer(path);
    	Producer prod = new Producer(buffer);
        Consumer cons = new Consumer(buffer);

        //pornire thread-uri
        prod.start();
        cons.start();

        prod.join();
        cons.join();
        
      //variabila folosita pentru a vedea cand se termina citirea imaginii
        long endTime = System.nanoTime();
        
        //afisare timp necesar citirii imaginii
        System.out.println("Citirea pozei s-a executat in : "+((endTime-startTime)/1000000)+"ms");
        
        //obiect folosit pentru a obtine o imagine din buffer-ul unde s-a memorat imaginea originala
        BufferedImage img = createImageFromBytes(buffer.getImage());
        
        //setare inaltime
        super.setHeight(img.getHeight());
        
        //setare latime
        super.setWidth(img.getWidth());
        
        //creare matrice de pixeli
        Pixels=super.createPixelMatrix(height,width);
        
        //setare culori pentru matricea de pixeli creata
        for(int i=0; i<img.getHeight(); i++)
        {
            for(int j=0; j<img.getWidth(); j++)
            {
                int colour = img.getRGB(j,i);
                Pixels[i][j].setColours(colour);
                
            }
        }
        return img;
    }
    
    //metoda folosita pentru a crea o imagine din bytes
    private static BufferedImage createImageFromBytes(byte[] imageData) {
    	
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        
        //tratarea exceptiilor
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  
    //metoda folosita pentru a converti imagimea
    @Override
    public Pixel[][] convertToGrayScale(){
    	//variabila folosita pentru a vedea cand se incepe procesarea imaginii
        long startTime = System.nanoTime();
        
        //apelam createPixelMatrix pentru a crea matricea de pixeli a imaginii convertite
        Pixel[][] newPixels = createPixelMatrix(height,width);
        
        int grayScale;
          for(int i = 0 ; i < height ; i++ )
            {
                for(int j = 0 ; j < width ; j++)
                {
                	//convertim imaginea originala in GrayScale
                    grayScale = (int)((0.3)*(Pixels[i][j].getRed())+(0.59)*(Pixels[i][j].getGreen())+0.11*(Pixels[i][j].getBlue()));
                    
                    //salvam noua valoare a pixelilor
                    newPixels[i][j].setRGB(grayScale,grayScale,grayScale);
                }
            }
          
        //variabila folosita pentru a vedea cand se termina procesarea imaginii
        long endTime = System.nanoTime();
        
        //afisare timp necesar procesarii imaginii
        System.out.println("Transformarea din RGB in GrayScale s-a executat in : "+((endTime-startTime)/1000000)+"ms");
     
        //se returneaza matricea de pixeli a imaginii convertite 
        return newPixels;
    }
    

    //metoda folosita pentru a salva imaginea convertita
    @Override
	public BufferedImage saveConvertedImage(String bufferedImage){
            
            //salvam matricea de pixeli a imagigii convertite
            Pixel[][] newPixels = convertToGrayScale();

            //cream un buffer pentru a putea salva imaginea obtinuta
            BufferedImage newImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            
            //se salveaza imaginea in buffer-ul creat anterior
            for(int i = 0 ; i < height ; i++ )
            {
                for(int j = 0 ; j < width ; j++)
                {
                    newImage.setRGB(j,i,(newPixels[i][j].getRed()<<16)|(newPixels[i][j].getGreen()<<8)|(newPixels[i][j].getBlue()));
                }
            }
            

        return newImage;
    }
    
}
