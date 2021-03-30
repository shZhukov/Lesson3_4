package ru.geekbrains.Lesson3_4;

import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

public class Main {
    static Object monitor = new Object();
    static char letter = 'A';

    public static void main(String[] args) {
	// write your code here
        try {
            threeTread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void threeTread() throws InterruptedException{
        Thread a1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < 5; i++){
                        synchronized (monitor){
                            while (letter != 'A'){
                                monitor.wait();
                            }
                            System.out.print("A");
                            letter = 'B';
                            monitor.notifyAll();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread b1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < 5; i++){
                        synchronized (monitor){
                            while (letter != 'B'){
                                monitor.wait();
                            }
                            System.out.print("B");
                            letter = 'C';
                            monitor.notifyAll();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread c1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < 5; i++){
                        synchronized (monitor){
                            while (letter != 'C'){
                                monitor.wait();
                            }
                            System.out.print("C");
                            letter = 'A';
                            monitor.notifyAll();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        a1.start();
        b1.start();
        c1.start();
    }
}
