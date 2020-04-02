package ch.hslu.ad.sw07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdditionTask implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(AdditionTask.class);
    private volatile Thread thread;
    private volatile boolean isStopped = false;
    private int from;
    private int to;
    private int number;
    private int duration;

    public AdditionTask(int from, int to, int number, int duration) {
        this.from = from;
        this.to = to;
        this.number = number;
        this.duration = duration;
    }


    public void stopRequest() {
        this.isStopped = true;
        if (this.thread != null) {
            this.thread.interrupt();
        }
    }

    public boolean isStopped() {
        return this.isStopped;
    }

    @Override
    public void run() {
        try {
            var time = System.currentTimeMillis();
            while(!Thread.currentThread().isInterrupted()) {
                this.thread = Thread.currentThread();
                var sum = this.getCrossSum();
                LOGGER.info(this.thread.getName() + ": SUM" + this.number + " -> " + sum);
                if (System.currentTimeMillis() - time > this.duration) {
                    this.stopRequest();
                }
                else {
                    Thread.sleep(15);
                }
            }
        } catch (Exception ex) {
            this.stopRequest();
            LOGGER.error(ex.getMessage());
            ex.printStackTrace();
        } finally {
            LOGGER.info(this.thread.getName() + ": interrupted");
        }
    }

    private long getCrossSum() {
        long sum = 0;
        for (int i = this.from; i < this.to; i++) {
            sum += i;
        }
        return sum;
    }
}
