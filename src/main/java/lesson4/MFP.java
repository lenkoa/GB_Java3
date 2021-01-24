package lesson4;

public class MFP {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        DocPrinter print1 = new DocPrinter(3, 1);
        print1.start();
        DocScanner scan1 = new DocScanner(4,1);
        scan1.start();
        DocPrinter print2 = new DocPrinter(4, 2);
        print2.start();
        DocScanner scan2 = new DocScanner(3,2);
        scan2.start();
        DocScanner scan3 = new DocScanner(3,3);
        scan3.start();


    }

    static class DocScanner extends Thread {
        int pages;
        int documentID;

        public DocScanner(int pages, int documentID) {
            this.pages = pages;
            this.documentID = documentID;
        }

        public void run() {
            synchronized (lock2) {
                for (int i = 1; i <= pages; i++) {
                    try {
                        Thread.sleep(50);
                        System.out.println("Отсканирована страница " + i + " документа " + documentID);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class DocPrinter extends Thread {
        int pages;
        int documentID;

        public DocPrinter(int pages, int documentID) {
            this.pages = pages;
            this.documentID = documentID;
        }

        public void run() {
            synchronized (lock1) {
                for (int i = 1; i <= pages; i++) {
                    try {
                        Thread.sleep(50);
                        System.out.println("Отпечатана страница " + i + " документа " + documentID);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
