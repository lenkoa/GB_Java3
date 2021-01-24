package lesson4;

public class PrintAbc {

    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        PrintAbc abc = new PrintAbc();
        Thread t1 = new Thread(abc::printA);
        Thread t2 = new Thread(abc::printB);
        Thread t3 = new Thread(abc::printC);
        t1.start();
        t2.start();
        t3.start();
    }

    public void printA(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A'){
                        mon.wait();
                    }
                    System.out.println("A");
                    currentLetter = 'B';
                    mon.notifyAll();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B'){
                        mon.wait();
                    }
                    System.out.println("B");
                    currentLetter = 'C';
                    mon.notifyAll();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C'){
                        mon.wait();
                    }
                    System.out.println("C");
                    currentLetter = 'A';
                    mon.notifyAll();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}