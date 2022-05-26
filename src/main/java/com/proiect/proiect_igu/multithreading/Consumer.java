//clasa Consumer face parte din packege-ul multitheading
package com.proiect.proiect_igu.multithreading;


public class Consumer extends Thread {
    private Buffer buff;

    //constructor cu parametri
    public Consumer(Buffer b) {
        buff = b;
    }

    //metoda folosita pentru sincronizare( pentru protectia la o eventuala
    //interferenta cu alte posibile threaduri)
    public synchronized void start() {
        super.start();
    }

    public void run() {

    	//se trateaza exceptiile
        try {
            for (int i = 0; i < 4; i++) {
                buff.get();
                sleep(1000);
            }

        } catch (InterruptedException e) {

        }
    }
}
