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

import java.util.*;
import java.util.concurrent.*;
import java.util.function.IntConsumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ch.hslu.ad.helper.Timer.stopWatch;

/**
 * Demonstration einer synchrnisierten List mit n Producer und m Consumer.
 */
public final class DemoConcurrentList {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.ad.sw09.exercise.n3.conclist.DemoConcurrentList.class);

    /**
     * Privater Konstruktor.
     */
    private DemoConcurrentList() {
    }

    /**
     * Main-Demo.
     * @param args not used.
     */
    public static void main(final String[] args) {
        stopWatch(LOG, f -> calculateSum(new LinkedList<>()));
        stopWatch(LOG, f -> calculateSum(Collections.synchronizedList(new LinkedList<>())));
        stopWatch(LOG, f -> calculateSum(new LinkedBlockingDeque<>()));
    }

    /**
     * Produces and consumes sums using a list.
     * @param list The shared list.
     */
    private static void calculateSum(List<Integer> list) {
        final ExecutorService executor = Executors.newCachedThreadPool();
        try {
            final List<Future<Long>> futures = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                futures.add(executor.submit(new Producer(list, 1000)));
            }
            Iterator<Future<Long>> iterator = futures.iterator();
            long totProd = 0;
            while (iterator.hasNext()) {
                long sum = iterator.next().get();
                totProd += sum;
                LOG.info("producer sum   = " + sum);
            }
            LOG.info("producer total = " + totProd);
            long totCons = executor.submit(new Consumer(list)).get();
            LOG.info("consumer total = " + totCons);
        } catch(InterruptedException | ExecutionException ex) {
            LOG.error(ex.getMessage());
            ex.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Produces and consumes sums using a blocking queue.
     * @param queue The shared queue.
     */
    private static void calculateSum(BlockingQueue<Integer> queue) {
        final ExecutorService executor = Executors.newCachedThreadPool();
        try {
            final List<Future<Long>> futures = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                futures.add(executor.submit(new Producer(queue, 1000)));
            }
            Iterator<Future<Long>> iterator = futures.iterator();
            long totProd = 0;
            while (iterator.hasNext()) {
                long sum = iterator.next().get();
                totProd += sum;
                LOG.info("producer sum   = " + sum);
            }
            LOG.info("producer total = " + totProd);
            long totCons = executor.submit(new Consumer(queue)).get();
            LOG.info("consumer total = " + totCons);
        } catch(InterruptedException | ExecutionException ex) {
            LOG.error(ex.getMessage());
            ex.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
