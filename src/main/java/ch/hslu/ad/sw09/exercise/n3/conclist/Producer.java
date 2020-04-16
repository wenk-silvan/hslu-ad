/*
 * Copyright 2020 Hochschule Luzern - Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.sw09.exercise.n3.conclist;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Produzent, der eine maximale Anzahl Werte produziert und diese in eine Queue speichert.
 */
public final class Producer implements Callable<Long> {

    private final List<Integer> list;
    private final BlockingQueue<Integer> queue;
    private final int maxRange;

    /**
     * Erzeugt einen Produzent, der eine bestimmte Anzahl Integer-Werte produziert.
     * @param list Liste zum Speichern der Integer-Werte.
     * @param max Anzahl Integer-Werte.
     */
    public Producer(final List<Integer> list, final int max) {
        this.list = list;
        this.queue = null;
        this.maxRange = max;
    }

    /**
     * Erzeugt einen Produzent, der eine bestimmte Anzahl Integer-Werte produziert.
     * @param queue Queue zum Speichern der Integer-Werte.
     * @param max Anzahl Integer-Werte.
     */
    public Producer(final BlockingQueue<Integer> queue, final int max) {
        this.list = null;
        this.queue = queue;
        this.maxRange = max;
    }

    /**
     * Liefert die Summe aller zusammengezählter Integer Werte.
     * @return Summe.
     */
    @Override
    public Long call() {
        if (this.list != null) {
            this.list.addAll(IntStream
                    .rangeClosed(1, this.maxRange)
                    .boxed()
                    .collect(Collectors.toList()));

            return IntStream
                    .rangeClosed(1, this.maxRange)
                    .mapToLong(i -> i)
                    .sum();
        } else if (this.queue != null) {
            this.queue.addAll(IntStream
                    .rangeClosed(1, this.maxRange)
                    .boxed()
                    .collect(Collectors.toList()));

            return IntStream
                    .rangeClosed(1, this.maxRange)
                    .mapToLong(i -> i)
                    .sum();
        }
        return (long) 0;
    }
}
