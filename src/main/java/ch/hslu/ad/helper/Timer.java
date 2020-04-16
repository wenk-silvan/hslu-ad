package ch.hslu.ad.helper;

import org.apache.logging.log4j.Logger;

import java.util.function.IntConsumer;

public class Timer {
    /**
     * Executes the given method and logs it's duration time.
     * @param function The method to execute.
     */
    public static void stopWatch(final Logger log, IntConsumer function) {
        var startingTime = System.currentTimeMillis();
        function.accept(1);
        var duration = System.currentTimeMillis() - startingTime;
        log.info(String.format("The execution took %ss and %sms.", duration / 1000, duration % 1000));
        System.out.println("");
    }
}
