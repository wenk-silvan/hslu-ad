package ch.hslu.ad.sw09.exercise.n3.prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class MultiplePrimeMiner implements Callable<List<BigInteger>> {
    private int countOfPrimes;
    private int certainty;

    MultiplePrimeMiner(int countOfPrimes, int certainty) {
        this.countOfPrimes = countOfPrimes;
        this.certainty = certainty;
    }

    @Override
    public List<BigInteger> call() throws Exception {
        var primes = new ArrayList<BigInteger>();
        do {
            BigInteger bi = new BigInteger(1024, new Random());
            if (bi.isProbablePrime(this.certainty)) {
                primes.add(bi);
            }
        } while(primes.size() < this.countOfPrimes);
        return primes;
    }
}
