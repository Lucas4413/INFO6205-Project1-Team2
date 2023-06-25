package edu.neu.coe.info6205.madhava;

import com.phasmidsoftware.number.core.Rational;
import com.phasmidsoftware.number.core.Rational$;
import scala.math.BigInt;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import java.math.BigInteger;

public class Wallis {
    public static Rational halfPi(int n) {
        Rational result = Rational$.MODULE$.apply(1);
        for (int i = 1; i <= n; i++)
            result = result.$times(term(i));
        return result;
    }

    public static BigNumber pi(int n) {
        Rational pi = halfPi(n).$times(2);
        return BigNumber.value(pi);
    }

    public static Rational term(long i) {
        BigInteger twoI = BigInteger.valueOf(2 * i);
        BigInteger denom = twoI.subtract(BigInteger.ONE).multiply(twoI.add(BigInteger.ONE));
        return new Rational(convertToBigInt(twoI.multiply(twoI)), convertToBigInt(denom));
    }

    public static BigInt convertToBigInt(BigInteger x) {
        return new BigInt(x);
    }

    public static BigInteger convertFromBigInt(BigInt x) {
        return x.underlying();
    }

    public static void main(String[] args) {
        // Create the BigNumber instances from 1 to 100 in a array
        BigNumber[] inputs = new BigNumber[100];
        for (int i = 0; i < 100; i++){
            inputs[i] = BigNumber.value(BigInteger.valueOf(i+1));
        }

        AtomicInteger j = new AtomicInteger(1);
        // Consumer function for regular multiplication
        Consumer<BigNumber[]> regularMultiply = arr -> {
            BigNumber res = BigNumber.parse("2");
            while(j.get()<100){
                res = res.multiply(BigNumber.value(term(j.getAndIncrement())));
            }
        };
        AtomicInteger i = new AtomicInteger(1);
        // Consumer function for Karatsuba multiplication
        Consumer<BigNumber[]> karatsubaMultiply = arr -> {
            BigNumber res = BigNumber.parse("2");
            while(i.get()<100){
                res = res.karatsubaMultiply(BigNumber.value(term(i.getAndIncrement())));
            }
        };

        // Create a supplier that returns an array of BigNumber instances
        Supplier<BigNumber[]> supplier = () -> inputs;

        // Now we create two Benchmark_Timer instances
        Benchmark_Timer<BigNumber[]> regularBenchmark = new Benchmark_Timer<>("Regular multiplication", regularMultiply);
        Benchmark_Timer<BigNumber[]> karatsubaBenchmark = new Benchmark_Timer<>("Karatsuba multiplication", karatsubaMultiply);

        // Run the benchmarks
        int m = 1000; // Number of times to run the benchmarks
        double regularTime = regularBenchmark.runFromSupplier(supplier, m);
        double karatsubaTime = karatsubaBenchmark.runFromSupplier(supplier, m);

        System.out.println("Average time for regular multiplication: " + regularTime + " ns");
        System.out.println("Average time for Karatsuba multiplication: " + karatsubaTime + " ns");

        BigNumber res = BigNumber.parse("2");
        int k = 1;
        while(k<301){
            res = res.karatsubaMultiply(BigNumber.value(term(k++)));
        }
        System.out.println("The value of \uD835\uDF0B is: "+res.toString().substring(0,103));
    }
}
