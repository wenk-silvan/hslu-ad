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

/**
 * Modulo Counter - Code Skizze.
 */
public final class ModuloCounter {

    private volatile int count = 0;
    private final int mod;

    /**
     * Erzeugt einen Modulo Counter.
     * @param mod Modul-Wert.
     */
    public ModuloCounter(int mod) {
        this.mod = mod;
    }

    /**
     * Modulo Counter wird um eins erhöht und der Modulo-Wert gespeichert.
     */
    public synchronized void increment() {
        count = (count + 1) % mod;
    }

    /**
     * Modulo Counter wird um eins verkleinert und der Modulo-Wert gespeichert.
     */
    public synchronized void decrement() {
        count = (count - 1 + mod) % mod;
    }

    /**
     * Liefert den Zählerstand.
     * @return Zählerstand.
     */
    public int getValue() {
        return count;
    }
}
