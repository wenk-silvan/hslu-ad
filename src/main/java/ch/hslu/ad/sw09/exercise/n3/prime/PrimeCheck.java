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
package ch.hslu.ad.sw09.exercise.n3.prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ch.hslu.ad.helper.Timer.stopWatch;

/**
 * 100 grosse Primzahlen produzieren.
 */
public final class PrimeCheck {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.ad.sw09.exercise.n3.prime.PrimeCheck.class);

    /**
     * Privater Konstruktor.
     */
    public PrimeCheck() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        int count = 100;
        int certainty = Integer.MAX_VALUE;
        //stopWatch(LOG, j -> getPrimesSequentially(count, certainty));
        stopWatch(LOG, j -> getManyPrimesParallel(4, 25, certainty));
        stopWatch(LOG, j -> getManyPrimesParallel(10, 10, certainty));
    }

    /**
     * Finds the specified amount of primes sequentially using BigIntegers.
     * @param count The amount of primes to search.
     * @param certainty The certainty by which the found number should be a prime.
     */
    private static void getPrimesSequentially(int count, int certainty) {
        int i = 1;
        while (i <= count) {
            BigInteger bi = new BigInteger(1024, new Random());
            //BigInteger bi = new BigInteger(1024, Integer.MAX_VALUE, new Random());
            if (bi.isProbablePrime(certainty)) {
                LOG.info(String.format("%s: %s...", i, bi.toString().substring(0, 20)));
                i++;
            }
        }
    }

    /**
     * Finds the specified amount of primes parallel using BigIntegers and one thread per prime.
     * @param count The amount of primes to search.
     * @param certainty The certainty by which the found number should be a prime.
     */
    private static void getSinglePrimesParallel(int count, int certainty) {
        final var executor = Executors.newCachedThreadPool();
        var futures = new ArrayList<Future<BigInteger>>();
        IntStream.rangeClosed(1, count).forEach((i) -> futures.add(executor.submit(new SinglePrimeMiner(certainty))));
        try {
            for (int i = 0; i < futures.size(); i++) {
                final var prime = futures.get(i).get();
                LOG.info(getMessage(i, prime));
            }
        } catch (InterruptedException | ExecutionException ex) {
            LOG.error(ex.getMessage());
            ex.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Finds the specified amount of primes parallel using BigIntegers and multiple threads.
     * @param countOfThreads The amount of threads to use.
     * @param primePerThread The amount of prime to find per thread.
     * @param certainty The certainty by which the found number should be a prime.
     */
    private static void getManyPrimesParallel(int countOfThreads, int primePerThread, int certainty) {
        final var executor = Executors.newFixedThreadPool(countOfThreads);
        var futures = new ArrayList<Future<List<BigInteger>>>();
        IntStream
                .rangeClosed(1, countOfThreads)
                .forEach((i) -> futures.add(executor.submit(new MultiplePrimeMiner(primePerThread, certainty))));
        try {
            for (int i = 0; i < futures.size(); i++) {
                final var primes = futures.get(i).get();
                for (int k = 0; k < primePerThread; k++) {
                    final int index = i * primePerThread + k;
                    LOG.info(getMessage(index, primes.get(k)));
                }

            }
        } catch (InterruptedException | ExecutionException ex) {
            LOG.error(ex.getMessage());
            ex.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * Creates a shortened String of the prime.
     * @param index The index of the prime.
     * @param prime The prime to shorten.
     * @return A shortened String of the prime with it's index.
     */
    private static String getMessage(int index, BigInteger prime) {
        return String.format("%s: %s...", index + 1, prime.toString().substring(0, 20));
    }
}
