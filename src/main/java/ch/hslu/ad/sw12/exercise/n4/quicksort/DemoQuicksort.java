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
package ch.hslu.ad.sw12.exercise.n4.quicksort;

import ch.hslu.ad.helper.Timer;
import ch.hslu.ad.sw11.QuickSorter;
import ch.hslu.ad.sw12.exercise.n4.array.init.RandomInitTask;
import ch.hslu.ad.sw12.exercise.n4.array.sum.SumTask;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Vergleich von verschiedenen Quicksort-Implementationen.
 */
public final class DemoQuicksort {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.ad.sw12.exercise.n4.quicksort.DemoQuicksort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoQuicksort() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 300_000_000;
        final int[] array = new int[size];
        final ForkJoinPool pool = new ForkJoinPool();

        long initSum = initialize(array, pool);
        LOG.info("Start Quicksort Concurrent.");
        Timer.stopWatchNano(LOG, func -> pool.invoke(new QuicksortTask(array)));
        long sortSum = pool.invoke(new SumTask(array));
        LOG.info("Initialize Checksum : " + initSum);
        LOG.info("Concurrent Checksum : " + sortSum + "\n");
/*
        initSum = initialize(array, pool);
        LOG.info("Start Quicksort Recursive");
        Timer.stopWatchNano(LOG, func -> QuicksortRecursive.quicksort(array));
        sortSum = pool.invoke(new SumTask(array));
        LOG.info("Initialize Checksum  : " + initSum);
        LOG.info("Recursive Checksum   : " + sortSum + "\n");

        initSum = initialize(array, pool);
        LOG.info("Start Arrays.sort");
        Timer.stopWatchNano(LOG, func -> Arrays.sort(array));
        sortSum = pool.invoke(new SumTask(array));
        LOG.info("Initialize checksum : " + initSum);
        LOG.info("Sort checksum       : " + sortSum + "\n");*/
    }

    private static long initialize(int[] array, ForkJoinPool pool) {
        pool.invoke(new RandomInitTask(array, 100));
        return pool.invoke(new SumTask(array));
    }
}
