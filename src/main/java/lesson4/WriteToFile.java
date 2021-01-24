package lesson4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private static final File file = new File("file.txt");
    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WriteToFile() {
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(20);
                    String message = "String1 " + i;
                    writeString(message);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(20);
                    String message = "String2 " + i;
                    writeString(message);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(20);
                    String message = "String3 " + i;
                    writeString(message);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    // коль скоро задача писать последовательно, вроде как, не поставлена - только построчно...
    public static synchronized void writeString(String message) throws IOException {
        writer.write(message + "\n");
        writer.flush();
    }
}
