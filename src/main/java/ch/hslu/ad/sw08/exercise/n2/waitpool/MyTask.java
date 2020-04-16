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
package ch.hslu.ad.sw08.exercise.n2.waitpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Einfacher Task für die Demonstration eines Wait-Pools.
 */
public final class MyTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(MyTask.class);
    private final Object lock;
    public boolean condition;

    /**
     * Erzeugen einen Task.
     * @param lock für die Synchronisation
     */
    MyTask(final Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        LOG.info("warten...");
        synchronized (this.lock) {
            try {
                this.lock.wait();
            } catch (InterruptedException ex) {
                LOG.error(ex.getMessage());
                return;
            }
        }
        LOG.info("...aufgewacht");
    }
}
