package ch.hslu.ad.sw07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Task implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Task.class);
    private int duration;
    private Thread predecessor;
    private Thread thread;

    Task(int duration) {
        this.duration = duration;
        this.predecessor = null;
    }

    Task(int duration, Thread predecessor) {
        this.duration = duration;
        this.predecessor = predecessor;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        var name = thread.getName();
        try {
            if (this.predecessor != null) {
                LOGGER.info(name + ": Waiting for " + this.predecessor.getName() + " to finish.");
                this.predecessor.join();
            }
            LOGGER.info(name + ": Work " + duration + " milliseconds.");
            Thread.sleep(this.duration);
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
            ex.printStackTrace();
        } finally {
            LOGGER.info(name + ": Finished.");
            thread.interrupt();
        }
    }

    public void stopRequest() {
        if (this.thread != null) {
            this.thread.interrupt();
        }
    }
}

public class JoinAndSleepDemo {
    private static final Logger LOGGER = LogManager.getLogger(Task.class);
    public static void main(String[] args) throws InterruptedException {
        final Thread t3 = new Thread(new Task(4000), "Task-Thread3");
        var task2 = new Task(3000, t3);
        final Thread t2 = new Thread(task2, "Task-Thread2");
        final Thread t1 = new Thread(new Task(2000, t2), "Task-Thread1");
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(5000);
        task2.stopRequest();
    }
}
