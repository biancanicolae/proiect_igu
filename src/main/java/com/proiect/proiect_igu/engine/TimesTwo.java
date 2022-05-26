package com.proiect.proiect_igu.engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//clasa pentru zoom de doua ori
public class TimesTwo extends ClasaAbstracta {

    public BufferedImage zoom (String source, String destination) {
        BufferedImage img = null;
        File f = null;
        //citim imaginea
        try{
            f = new File(source);
            img = ImageIO.read(f);
        }catch(IOException e){
            System.out.println(e);
        }

        //salvam inaltimea si latimea imaginii
        int width = img.getWidth();
        int height = img.getHeight();

        int [][]p = new int[width][height];

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                p[x][y] = img.getRGB(x,y);
            }
        }

        //redimensionam imaginea si completam spatile lipsa
        img = resize(img, 2*width, 2*height);

        int k = 0;
        for(int y = 0; y < 2*height; y=y+2){
            int j = 0;
            for(int x = 0; x < 2*width; x=x+2){

                img.setRGB(x, y, p[j][k]);
                img.setRGB(x+1, y, p[j][k]);
                img.setRGB(x, y+1, p[j][k]);
                img.setRGB(x+1, y+1, p[j][k]);
                j++;
            }
            k++;
        }

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                p[x][y] = img.getRGB(x,y);
            }
        }

        //redimensionam imaginea si completam spatile lipsa
        img = resize(img, 4*width, 4*height);

        int ka = 0;
        for(int y = 0; y < 2*height; y=y+2){
            int j = 0;
            for(int x = 0; x < 2*width; x=x+2){
                img.setRGB(x, y, p[j][ka]);
                img.setRGB(x+1, y, p[j][ka]);
                img.setRGB(x, y+1, p[j][ka]);
                img.setRGB(x+1, y+1, p[j][ka]);
                j++;
            }
            ka++;
        }

        //salvam imaginea
        BufferedImage newBuffImg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        newBuffImg.createGraphics().drawImage(img,0,0, Color.WHITE,null);

        return newBuffImg;
    }

    @Override
    public BufferedImage convert(String src, String dst) {
        return null;
    }

    public int times () {
        return 1;
    }
}
