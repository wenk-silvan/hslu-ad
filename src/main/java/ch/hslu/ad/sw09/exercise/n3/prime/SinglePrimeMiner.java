package ch.hslu.ad.sw09.exercise.n3.prime;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.Callable;

public class SinglePrimeMiner implements Callable<BigInteger> {
    private int certainty;

    SinglePrimeMiner(int certainty) {
        this.certainty = certainty;
    }

    @Override
    public BigInteger call() throws Exception {
        do {
            BigInteger bi = new BigInteger(1024, new Random());
            if (bi.isProbablePrime(this.certainty)) {
                return bi;
            }
        } while(true);
    }
}
