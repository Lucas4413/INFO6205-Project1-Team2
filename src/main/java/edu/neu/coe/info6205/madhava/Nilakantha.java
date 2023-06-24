package edu.neu.coe.info6205.madhava;

import com.phasmidsoftware.number.core.Rational;
import com.phasmidsoftware.number.core.Rational$;
import scala.math.BigInt;

import java.math.BigInteger;

public class Nilakantha {
    // pi = 3 + 4/2*3*4 - 4/4*5*6 + 4/6*7*8 ...

    public static Rational calculatePi(int n) {
        Rational result = Rational$.MODULE$.apply(3);
        long mul = -1;

        for(int i = 2; i < n * 2; i += 2) {
            mul *= -1;
            result = result.$plus(createRational(BigInteger.valueOf(4).multiply(BigInteger.valueOf(mul)), BigInteger.valueOf(i).multiply(BigInteger.valueOf(i + 1)).multiply(BigInteger.valueOf(i + 2))));
        }
        return result;
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO))
            return a;
        else
            return gcd(b, a.mod(b));
    }

    public static Rational createRational(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = gcd(numerator, denominator);
        return new Rational(convertToBigInt(numerator.divide(gcd)), convertToBigInt(denominator.divide(gcd)));
    }

    public static BigInt convertToBigInt(BigInteger x) {
        return new BigInt(x);
    }

    public static BigInteger convertFromBigInt(BigInt x) {
        return x.underlying();
    }

    public static void main(String[] args) {
        int n = 1000;
        System.out.println(calculatePi(n));
    }
}
