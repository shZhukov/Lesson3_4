package ru.geekbrains.Lesson3_4;

public class Mfu {
    Object printMonitor = new Object();
    Object scanMonitor = new Object();

    public static void main(String[] args) {
        Mfu mfu = new Mfu();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print("Voina i mir", 5);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("12312", 30,"scan");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("123123", 12, "copy");
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan("123123", 12, "засунул скрепку");
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    public void print(String doc, int x){
        synchronized (printMonitor){
            System.out.println("Печать начало");
            for (int i = 0; i < x; i++){
                System.out.println("Напечатал " + doc + " " + i);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Печать окончена");
        }
    }

    public void scan(String doc, int x, String what) {
        synchronized (scanMonitor) {
            System.out.println("Использование сканера начало");
            if (what == "scan") {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Сканирование " + doc + " " + i);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                if (what == "copy") {
                    synchronized (printMonitor) {
                        for (int i = 0; i < x; i++) {
                            System.out.println("Ксерокопирование " + doc + " " + i);
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Бумагу зажевало. Что вы пытаетесь сделать со сканером. Не выполнимая команда");
                    return;
                }
            }
        }
    }

}
