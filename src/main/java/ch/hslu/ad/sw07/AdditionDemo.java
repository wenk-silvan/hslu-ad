package ch.hslu.ad.sw07;

import java.util.Timer;

public class AdditionDemo {
    public static void main(String[] args) {
        createThread(1, 10, 1);
        createThread(1, 100, 2);
        createThread(1, 1000, 3);
        createThread(1, 5000, 4);
        createThread(1, 10000, 5);
        createThread(1, 50000, 6);
    }

    private static void createThread(int from, int to, int number) {
        final AdditionTask task = new AdditionTask(from, to, number, 500);
        final Thread thread = new Thread(task, "Task-Thread" + number);
        thread.start();
    }
}
