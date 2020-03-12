package javaFuction;
        import java.util.Random;
public class LeafTest
{
    static int array[] = new int[9999];
    static long sum = 0;
    public static void usingMathsRandom() {
        for (int i = 0; i < 9999; i++) {
            array[i] = (int) (Math.random() * 256);
        }

        for (int i = 0; i < 9999; i++) {
            for (int j = 0; j < 9999; j++) {
                if (array[j] >= 128) {
                    sum += array[j];
                }
            }
        }
    }

    public static void usingRandomClass() {
        Random random = new Random();
        for (int i = 0; i < 9999; i++) {
            array[i] = random.nextInt(256);
        }

        for (int i = 0; i < 9999; i++) {
            for (int j = 0; j < 9999; j++) {
                if (array[j] >= 128) {
                    sum += array[j];
                }
            }

        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        usingRandomClass();
        long end = System.currentTimeMillis();
        System.out.println("usingRandomClass " + (end - start));
        start = System.currentTimeMillis();
        usingMathsRandom();
        end = System.currentTimeMillis();
        System.out.println("usingMathsRandom " + (end - start));

    }

}