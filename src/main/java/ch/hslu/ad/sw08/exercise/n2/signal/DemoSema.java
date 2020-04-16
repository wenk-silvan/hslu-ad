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
package ch.hslu.ad.sw08.exercise.n2.signal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demonstration eines Semaphors.
 */
public final class DemoSema {

    private static final Logger LOG = LogManager.getLogger(DemoSema.class);
    private static Semaphore sema = new Semaphore(2, 10);

    /**
     * Privater Konstruktor.
     */
    private DemoSema() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    try {
                        LOG.info("Thread {} waits.", Thread.currentThread().getName());
                        sema.acquire();
                        LOG.info("Thread {} is in critical section.", Thread.currentThread().getName());
                        Thread.sleep(500);
                        LOG.info("Thread {} leaves.", Thread.currentThread().getName());
                        sema.release();
                    } catch (InterruptedException ex) {
                        LOG.debug(ex.getMessage());
                    }
                }
            },"T"+i).start();
        }
    }
}
