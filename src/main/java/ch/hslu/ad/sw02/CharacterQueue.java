package ch.hslu.ad.sw02;

import ch.hslu.demo.DemoApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class CharacterQueue {
    private static final Logger LOGGER = LogManager.getLogger(DemoApp.class);

    private Character[] list;
    private int head;
    private int tail;

    CharacterQueue() {
        this.list = new Character[8];
        this.head = 0;
        this.tail = 0;
        LOGGER.info("INIT:  " + this.toString());
    }

    CharacterQueue(char c) {
        this();
        this.list[0] = c;
        LOGGER.info("INIT:  " + this.toString());
    }

    void offer(char c) {
        if (this.isEmpty()) {
        } else if (this.tail == 7 && this.head == 6) {
            this.tail = 0;
            this.head++;
        } else if (this.head == 7 && this.tail == 0) {
            this.head = 0;
            this.tail = 1;
        } else if (this.isFull()) {
            this.head++;
            this.tail++;
        } else {
            this.head++;
        }
        this.list[this.head] = c;
        LOGGER.info("OFFER: " + this.toString());
    }

    Character poll() {
        if (this.isEmpty()) {
            LOGGER.info("POLL:  " + this.toString());
            return null;
        }
        var c = this.list[this.head];
        this.list[this.head] = null;
        if (this.head == this.tail) {
        }
        else if (this.head == 0) {
            this.head = 7;
        } else {
            this.head--;
        }
        LOGGER.info("POLL:  " + this.toString());
        return c;
    }

    int size() {
        var count = 0;
        if (this.isFull()) {
            count = 8;
        } else if (this.isEmpty()) {
        } else {
            count = this.head - this.tail + 1;
        }
        LOGGER.info("SIZE:  " + this.toString());
        return count;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i < this.list.length; i++) {
            sb.append(String.format("[%c%s] - ", this.list[i], (this.head == i && this.tail == i) ? " (H,T)" : (this.tail == i ? " (T)" : (this.head == i ? " (H)" : ""))));
        }
        return sb.toString();
    }

    private boolean isEmpty() {
        return this.head == this.tail && this.list[this.head] == null;
    }

    private boolean isFull() {
        return this.head + 1 == this.tail;
    }
}
