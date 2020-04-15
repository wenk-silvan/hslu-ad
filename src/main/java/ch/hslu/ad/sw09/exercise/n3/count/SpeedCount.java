/*
 * Copyright 2020 Hochschule Luzern Informatik.
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
package ch.hslu.ad.sw09.exercise.n3.count;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Speed-Test für unterschiedlich impementierte Counters.
 */
public final class SpeedCount {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.ad.sw09.exercise.n3.count.SpeedCount.class);
    private static boolean counterCheck;

    /**
     * Privater Konstruktor.
     */
    private SpeedCount() {
    }

    /**
     * Test für einen Counter.
     * @param counter Zählertyp.
     * @param number Anzahl Zähl-Vorgänge.
     * @param countOfThreads Anzahl Tester-Threads.
     * @return Dauer des Tests in mSec.
     */
    public static long speedTest(Counter counter, int number, int countOfThreads) {
        counterCheck = false;
        final ExecutorService executor = Executors.newCachedThreadPool();
        final List<Future<Integer>> futures = new ArrayList<>();
        long timer = System.currentTimeMillis();
        for (int i = 0; i < countOfThreads; i++) {
            futures.add(executor.submit(new CountTask(counter, number)));
        }
        Iterator<Future<Integer>> iterator = futures.iterator();
        while (iterator.hasNext()) {
            try {
                if (iterator.next().get() == 0) {
                    counterCheck = true;
                }
            } catch (InterruptedException | ExecutionException ex) {
                LOG.debug(ex);
                return -1;
            }
        }

        long duration = System.currentTimeMillis() - timer;
        executor.shutdown();
        return duration;
    }

    /**
     * Führt den Speedtest für mit dem gegebenen Counter aus.
     * @param counter Der auszuführende Zähler.
     */
    private static void speedTestExecutor(Counter counter) {
        final int number = 1000000;
        final int rounds = 23;
        final int countOfThreads = 8;

        long sum = 0;
        for (int i = 0; i < rounds; i++) {
            sum += speedTest(counter, number, countOfThreads);
        }
        LOG.info(String.format("%s %s - average test duration = %s ms", counter.toString(), counterCheck ? "OK." : "ERROR.", sum / rounds));
    }

    /**
     * Main-Counter-Test.
     * @param args not used.
     */
    public static void main(final String args[]) {
        speedTestExecutor(new SynchronizedCounter());
        speedTestExecutor(new AtomicCounter());
        //speedTestExecutor(new GuardedCounter());
    }
}
