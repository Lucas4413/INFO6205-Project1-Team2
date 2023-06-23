package edu.neu.coe.info6205.madhava;

import com.phasmidsoftware.number.core.Rational;
import junit.framework.TestCase;
import org.junit.Test;

public class MadhavaTest extends TestCase {

    final BigNumber approximatePi = BigNumber.value(3, 1415927, true);

    @Test
    public void testQuarterPi() {
        assertEquals(Rational.apply("0"), Madhava.quarterPi(1, Madhava::termFirst));
        assertEquals(Rational.apply("7/6"), Madhava.quarterPi(2, Madhava::termFirst));
        assertEquals(Rational.apply("1/5"), Madhava.quarterPi(1, Madhava::termSecond));
        assertEquals(Rational.apply("58/51"), Madhava.quarterPi(2, Madhava::termSecond));
        assertEquals(Rational.apply("1/9"), Madhava.quarterPi(1, Madhava::termThird));
        assertEquals(Rational.apply("142/123"), Madhava.quarterPi(2, Madhava::termThird));
        assertEquals(Rational.apply("5/27"), Madhava.quarterPi(1, Madhava::termFourth));
        assertEquals(Rational.apply("90/79"), Madhava.quarterPi(2, Madhava::termFourth));
    }

    @Test
    public void testTerm() {
        assertEquals(Rational.apply("-22/27"), Madhava.termFourth(1));
        assertEquals(Rational.apply("112/237"), Madhava.termFourth(2));
        assertEquals(Rational.apply("-38/117"), Madhava.termFourth(3));
    }

    @Test
    public void testPi() {
        assertEquals(approximatePi.divide(4).doubleValue(), Madhava.quarterPi(1000, Madhava::termFirst).toDouble(), 1E-3);
        assertEquals(approximatePi.divide(4).doubleValue(), Madhava.quarterPi(1000, Madhava::termSecond).toDouble(), 1E-3);
        assertEquals(approximatePi.divide(4).doubleValue(), Madhava.quarterPi(1000, Madhava::termThird).toDouble(), 1E-3);
        assertEquals(approximatePi.divide(4).doubleValue(), Madhava.quarterPi(1000, Madhava::termFourth).toDouble(), 1E-3);
    }

    @Test
    public void testError() {
        assertTrue(Math.abs(Madhava.mglSeries(1001).toDouble() - approximatePi.divide(4).doubleValue()) > Math.abs(Madhava.quarterPi(1000, Madhava::termFirst).toDouble() - approximatePi.divide(4).doubleValue()));;
        assertTrue(Math.abs(Madhava.mglSeries(1001).toDouble() - approximatePi.divide(4).doubleValue()) > Math.abs(Madhava.quarterPi(1000, Madhava::termSecond).toDouble() - approximatePi.divide(4).doubleValue()));;
        assertTrue(Math.abs(Madhava.mglSeries(1001).toDouble() - approximatePi.divide(4).doubleValue()) > Math.abs(Madhava.quarterPi(1000, Madhava::termThird).toDouble() - approximatePi.divide(4).doubleValue()));;
        assertTrue(Math.abs(Madhava.mglSeries(1001).toDouble() - approximatePi.divide(4).doubleValue()) > Math.abs(Madhava.quarterPi(1000, Madhava::termFourth).toDouble() - approximatePi.divide(4).doubleValue()));;
    }
}