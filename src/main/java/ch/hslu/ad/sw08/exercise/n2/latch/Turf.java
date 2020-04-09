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
package ch.hslu.ad.sw08.exercise.n2.latch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Eine Rennbahn für das Pferderennen.
 */
public final class Turf {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.ad.sw08.exercise.n2.latch.Turf.class);

    /**
     * Privater Konstruktor.
     */
    private Turf() {
    }

    /**
     * Main-Demo.
     * @param args not used.
     */
    public static void main(final String[] args) throws InterruptedException {
        final Synch starterBox = new Latch();

        var t1 = new Thread(new RaceHorse(starterBox), "Horse " + 1);
        t1.start();
        var t2 = new Thread(new RaceHorse(starterBox), "Horse " + 2);
        t2.start();
        var t3 = new Thread(new RaceHorse(starterBox), "Horse " + 3);
        t3.start();
        var t4 = new Thread(new RaceHorse(starterBox), "Horse " + 4);
        t4.start();
        var t5 = new Thread(new RaceHorse(starterBox), "Horse " + 5);
        t5.start();

        Thread.sleep(10);
        LOG.info("Start...");
        starterBox.release();
        Thread.sleep(1000);
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        t4.interrupt();
        t5.interrupt();
    }
}
