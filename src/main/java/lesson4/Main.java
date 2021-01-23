package lesson4;

public class Main {

    private String letter = null;

    Thread t1 = new Thread(() -> {
        for (int i = 0; i < 5; i++) {
            if (letter.equals("A") || letter.equals("B")) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("A");
            letter = "A";
            notifyAll();
        }
    });
}
