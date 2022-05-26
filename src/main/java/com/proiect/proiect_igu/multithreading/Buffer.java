//clasa Buffer face parte din packege-ul multithreading
package com.proiect.proiect_igu.multithreading;

//importam clasele necesare pentru lucrul cu fisiere si tratare exceptii
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Buffer {
    private int number = 0;
    private int input_length;
    private int imageSizeToRead;

    private byte[] img;
    private byte[] image_quarter;
    private InputStream inputStream;

    private boolean available = false;

    public Buffer(String filename) {
    
        try {
        	//se citesc datele imaginii
            this.inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
        	//se trateaza exceptiile
            e.printStackTrace();
        }

        try {
        	//salvam numarul de octeti ramasi care pot fi cititi
            this.input_length = inputStream.available();
        } catch (IOException e) {
        	//se trateaza exceptiile
            e.printStackTrace();
        }
        
        //se creeaza un vector de byes de lungime input_lenght
        this.img = new byte[input_length];
        
        //salvam cate un sfert din informatia ce trebuie citita
        this.imageSizeToRead = input_length/4;
    }


    //metoda ce returneaza vectorul img
	public byte[] getImage() {
        return img;
    }

    public synchronized void put() {
    	
    	//cat timp nu sunt date disponibile( available = false la inceput)
        while (available) {
            try {
            	//se asteapta
                wait();

            } catch (InterruptedException e) {
            	//se trateaza exceptiile
                e.printStackTrace();
            }
        }
        
        //cream un vector ce va retine fiecare sfert de informatie citit
        image_quarter = new byte[this.imageSizeToRead];

        try {
        	
            //citirea propriu-zisa a imaginii( mai specific a fiecarui sfert din informatie)
            this.inputStream.read(image_quarter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //setam variabila pe true pentru a anunta ca avem date disponibile
        available = true;
        
        //se realizaeaza comunicarea intre thread-uri
        notifyAll();
    }

    public synchronized void get() {

    	//daca nu avem date disponibile
        while (!available) {
            try {
            	//se asteapta
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        //citim datele primite in vectorul img
        for(int i = 0; i < this.imageSizeToRead; i ++) {
            this.img[this.number] = this.image_quarter[i];
            this.number++;
        }
        
        //setam variabila pe false pentru a anunta ca nu mai avem date disponibile
        available = false;
        
        //se realizaeaza comunicarea intre thread-uri
        notifyAll();
    }
}
