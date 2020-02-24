package ch.hslu.ad.sw01;

import ch.hslu.demo.DemoApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class AhaDemo {
    private static final Logger LOGGER = LogManager.getLogger(DemoApp.class);

    static int calledTask1 = 0;
    static int calledTask2 = 0;
    static int calledTask3 = 0;

    static void task(final int n) {
        task1(); task1(); task1(); task1();
        for (int i = 0; i < n; i++) {
            task2(); task2(); task2();
            for (int j = 0; j < n; j++) {
                task3(); task3();
            }
        }
        LOGGER.info("Called task 1 {} times.", calledTask1);
        LOGGER.info("Called task 2 {} times.", calledTask2);
        LOGGER.info("Called task 3 {} times.", calledTask3);
    }

    private static void task1() {
        calledTask1++;
    }

    private static void task2() {
        calledTask2++;
    }

    private static void task3() {
        calledTask3++;
    }
}
