package ch.hslu.ad.sw02;

import ch.hslu.ad.sw01.Allocation;

public class CharacterQueue {
    private Character[] list;
    private int head;
    private int tail;

    CharacterQueue() {
        this.list = new Character[8];
        this.head = 0;
        this.tail = 0;
    }

    CharacterQueue(char c) {
        this();
        this.list[0] = c;
    }

    void offer(char c) {
        if (this.isEmpty()) {
        }
        else if (this.tail == 7 && this.head == 6) {
            this.tail = 0;
            this.head++;
        }
        else if (this.head == 7 && this.tail == 0) {
            this.head = 0;
            this.tail = 1;
        }
        else if (this.isFull()) {
            this.head++;
            this.tail++;
        } else {
            this.head++;
        }
        this.list[this.head] = c;
    }

    Character poll() {
        if (this.isEmpty()) {
            return null;
        }
        var c = this.list[this.head];
        this.list[this.head] = null;

        if (this.head == 0) {
            this.head = 7;
        } else {
            this.head--;
        }
        return c;
    }

    int size() {
        if (this.isFull()) {
            return 8;
        }
        else if (this.isEmpty()) {
            return 0;
        }
        else {
            return this.head - this.tail + 1;
        }
    }

    private boolean isEmpty() {
        return this.head == this.tail && this.list[this.head] == null;
    }

    private boolean isFull() {
        return this.head + 1 == this.tail;
    }
}
