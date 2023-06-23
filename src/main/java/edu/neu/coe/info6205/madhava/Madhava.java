package edu.neu.coe.info6205.madhava;

import com.phasmidsoftware.number.core.Rational;
import com.phasmidsoftware.number.core.Rational$;
import scala.math.BigInt;

import java.math.BigInteger;
import java.util.function.Function;

public class Madhava {
    // 1/N,   1/{N + 1/4N}, 1/{N + 1/[4N + 4/N]},

    // pi/4 = 1 - 1/3 + 1/5 - 1/7 + 1/9 -....


    // pi = 3 + 4/(3^3-3)-4/(5^3-5)+4/(7^3-7)-

    public static Rational mglSeries(int n) {
        Rational result = Rational$.MODULE$.apply(0);
        for(int i = 0; i < n; i++) {
            result = result.$plus(new Rational(convertToBigInt(BigInteger.valueOf(1)), convertToBigInt(BigInteger.valueOf(2 * i + 1))).$times(i%2 == 0 ? 1 : -1));
        }
        return result;
    }

    public static Rational quarterPi(int n, Function<Integer, Rational> termFunc) {
        Rational result = mglSeries(n);
        result = result.$plus(termFunc.apply(n));
        return result;
    }
//
//    public static Rational quarterPiFirst(int n) {
//        Rational result = mglSeries(n);
//        result = result.$plus(termFirst(n));
//        return result;
//    }
//
//    public static Rational quarterPiSecond(int n) {
//        Rational result = mglSeries(n);
//        result = result.$plus(termSecond(n));
//        return result;
//    }
//
//    public static Rational quarterPiThird(int n) {
//        Rational result = mglSeries(n);
//        result = result.$plus(termThrid(n));
//        return result;
//    }
//
//    public static Rational quarterPiFourth(int n) {
//        Rational result = mglSeries(n);
//        result = result.$plus(termFourth(n));
//        return result;
//    }

    /**
     * the first correction term is
     * 1/N
     * @param i
     * @return
     */
    public static Rational termFirst(long i) {
        BigInteger n = BigInteger.valueOf(i);
        return createRational(BigInteger.valueOf(1), n.multiply(BigInteger.valueOf(4))).$times(i%2 == 0 ? 1 : -1);
    }

    /**
     * the second correction term should be
     * 1/[N + 1/4N]
     * @param i
     * @return
     */
    public static Rational termSecond(long i) {
        BigInteger n = BigInteger.valueOf(i);
        BigInteger mole = n.multiply(BigInteger.valueOf(4));
        BigInteger denom = n.pow(2).multiply(BigInteger.valueOf(4)).add(BigInteger.valueOf(1));
        return createRational(mole, denom.multiply(BigInteger.valueOf(4))).$times(i%2 == 0 ? 1 : -1);
    }

    /**
     * the third correction term should be
     * 1/{N + 1/[4N + 4/N]}
     * @param i
     * @return
     */
    public static Rational termThird(long i) {
        BigInteger n = BigInteger.valueOf(i);
        BigInteger mole = n.pow(2).multiply(BigInteger.valueOf(4)).add(BigInteger.valueOf(4));
        BigInteger denom = n.pow(3).multiply(BigInteger.valueOf(4)).add(n.multiply(BigInteger.valueOf(4))).add(BigInteger.ONE);
        return createRational(mole, denom.multiply(BigInteger.valueOf(4))).$times(i%2 == 0 ? 1 : -1);
    }

    /**
     * the forth correction term should be
     * 1/{N + 1/[4N + (4/(n+9/n)]}
     * @param i
     * @return
     */
    public static Rational termFourth(long i) {
        BigInteger n = BigInteger.valueOf(i);
        BigInteger denom = n.pow(4).multiply(BigInteger.valueOf(4)).add(n.pow(2).multiply(BigInteger.valueOf(41))).add(BigInteger.valueOf(9));
        BigInteger mole = n.pow(3).multiply(BigInteger.valueOf(4)).add(n.multiply(BigInteger.valueOf(40)));
        return createRational(mole, denom.multiply(BigInteger.valueOf(4))).$times(i%2 == 0 ? 1 : -1);
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

//    public static void main(String[] args) {
//        final BigNumber approximatePi = BigNumber.value(3, 1415927, true);
//        System.out.println(approximatePi.divide(4).doubleValue());
//        System.out.println(mglSeries(1000).toDouble());
//        System.out.println(quarterPi(1000, Madhava::termFirst).toDouble());
//        System.out.println(quarterPi(1000, Madhava::termSecond).toDouble());
//        System.out.println(quarterPi(1000, Madhava::termThird).toDouble());
//        System.out.println(quarterPi(1000, Madhava::termFourth).toDouble());
//        System.out.println(termFirst(1000));
//        System.out.println(termSecond(1000));
//        System.out.println(termThird(1000));
//        System.out.println(termFourth(1000));
//    }
}
