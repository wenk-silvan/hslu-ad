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
package ch.hslu.ad.sw07.n12.countersimple;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ein Task für einen einfachen Zähler.
 */
public class SimpleCounterTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(SimpleCounterTask.class);

    private final SimpleCounter counter;

    /**
     * Erzeugt einen SimpleCounter Task für SimpleCounter Demo.
     * @param counter SimpleCounter.
     */
    public SimpleCounterTask(SimpleCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            LOG.info("{}{}", Thread.currentThread().getName(), counter.increment());
        }
    }
}
