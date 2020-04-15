package ch.hslu.ad.sw09.exercise.n3.count;
import ch.hslu.ad.sw09.exercise.n3.count.Counter;

import java.util.concurrent.locks.ReentrantLock;

public class GuardedCounter implements Counter {
    private int counter;
    private ReentrantLock lock;

    public GuardedCounter() {
        this.counter = 0;
        this.lock = new ReentrantLock(true);
    }

    /**
     * Thread-safely increments the counter.
     */
    @Override
    public void increment() {
        this.lock.lock();
        this.counter++;
        this.lock.unlock();
    }

    /**
     * Thread-safely increments the counter.
     */
    @Override
    public void decrement() {
        this.lock.lock();
        this.counter--;
        this.lock.unlock();
    }

    /**
     * Gets the value of the counter.
     * @return
     */
    @Override
    public int get() {
        return this.counter;
    }

    @Override
    public String toString() {
        return "Guarded Counter";
    }
}
