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
package ch.hslu.ad.sw07.n12.countermodulo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Eine Demo für den Zähler mit mehreren Threads.
 */
public class ModuloCounterTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(ModuloCounterTask.class);

    private final ModuloCounter counter;

    /**
     * Erzeugt einen Counter Task für Counter Demo.
     * @param counter Counter.
     */
    public ModuloCounterTask(ModuloCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            counter.increment();
            LOG.info("{}{}", Thread.currentThread().getName(), counter.getValue());
        }
    }
}
