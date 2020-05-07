package ch.hslu.ad.helper;

import org.apache.logging.log4j.Logger;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public class Timer<T> {
    /**
     * Executes the given method and logs it's duration time.
     * @param function The method to execute.
     */
    public static void stopWatchMilli(final Logger log, IntConsumer function) {
        var startingTime = System.currentTimeMillis();
        function.accept(1);
        var duration = System.currentTimeMillis() - startingTime;
        log.info(String.format("The execution took %ss and %sms.", duration / 1000, duration % 1000));
        System.out.println("");
    }

    /**
     * Executes the given method, logs it's duration time and returns the value.
     * @param function The method to execute.
     */
    public T stopWatchMilli(final Logger log, Supplier<T> function) {
        var startingTime = System.currentTimeMillis();
        var result = function.get();
        var duration = System.currentTimeMillis() - startingTime;
        log.info(String.format("The execution took %ss and %sms.", duration / 1000, duration % 1000));
        return result;
    }

    /**
     * Executes the given method and logs it's duration time.
     * @param function The method to execute.
     */
    public static void stopWatchNano(final Logger log, IntConsumer function) {
        var startingTime = System.nanoTime();
        function.accept(1);
        var duration = System.nanoTime() - startingTime;
        log.info(String.format("The execution took %ss %sms %sus.", duration / 1_000_000_000, (duration % 1_000_000_000) / 1_000_000, duration % 1_000));
    }

    /**
     * Executes the given method and logs it's duration time.
     * @param function The method to execute.
     */
    public T stopWatchNano(final Logger log, Supplier<T> function) {
        var startingTime = System.nanoTime();
        var result = function.get();
        var duration = System.nanoTime() - startingTime;
        log.info(String.format("The execution took %ss %sms %sus.", duration / 1_000_000_000, (duration % 1_000_000_000) / 1_000_000, duration % 1_000));
        return result;
    }
}
