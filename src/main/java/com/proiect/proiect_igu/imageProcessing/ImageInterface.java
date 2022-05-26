//interfata ImageInterface face parte din packege-ul imageProcessing
package com.proiect.proiect_igu.imageProcessing;

import java.awt.image.BufferedImage;

//se creaza o interfata
public interface ImageInterface {
	//declarare metoda pentru citirea imaginii
    BufferedImage readImage(String path) throws InterruptedException;
    
   //declarare metoda pentru salvarea imaginii convertite
    BufferedImage saveConvertedImage(String path);
    
}
