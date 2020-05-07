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
package ch.hslu.ad.sw12.exercise.n4.fibo;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Codevorlage für ein klassisches Beispiel zur Berechnung von Fibonacci Zahlen.
 */
@SuppressWarnings("serial")
public final class FibonacciTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 20;
    private final int n;
    private final int max;
    private final int min;

    /**
     * Erzeugt einen Fibonacci Task.
     *
     * @param n für die Fibonacci Berechnung.
     */
    public FibonacciTask(final int n) {
        this(n, 0, n);
    }

    private FibonacciTask(final int n, final int min, final int max) {
        this.n = n;
        this.min = min;
        this.max = max;
    }

    @Override
    protected Long compute() {
        if (n <= THRESHOLD) {
            return DemoFibonacciCalc.fiboRecursive(n);
        } else {
            var prev1 = new FibonacciTask(n - 1);
            prev1.fork();
            var prev2 = new FibonacciTask(n - 2);
            final Long sumRight = prev2.invoke();
            final Long sumLeft = prev1.join();
            return sumLeft + sumRight;
        }
    }
}
