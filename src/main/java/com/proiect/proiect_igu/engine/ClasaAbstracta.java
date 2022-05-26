package com.proiect.proiect_igu.engine;

import java.awt.*;
import java.awt.image.BufferedImage;

//clasa pentru a demonstra conceptul de clasa abstracta in java
public abstract class ClasaAbstracta implements Interfata {
    public int times () {
        return 0;
    }

    public abstract BufferedImage zoom(String src, String dst);

    //functia de resize pentru a modifica dimensiunea fotografiei
    public static BufferedImage resize(BufferedImage img, int newWhidth, int newHeight) {
        Image tmp = img.getScaledInstance(newWhidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newWhidth, newHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public abstract BufferedImage convert(String src, String dst);

    //functia de resize pentru a coverti fotografiei
    public static BufferedImage convertToGrayscale(BufferedImage img, int newWhidth, int newHeight) {
        Image tmp = img.getScaledInstance(newWhidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newWhidth, newHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
