package edu.neu.coe.info6205.madhava;

import com.phasmidsoftware.number.core.Rational;
import junit.framework.TestCase;
import org.junit.Test;

public class MadhavaTest extends TestCase {

    final BigNumber approximatePi = BigNumber.value(3, 1415927, true);

    @Test
    public void testQuarterPi() {
        assertEquals(Rational.apply("0"), Madhava.quarterPiFirst(1));
        assertEquals(Rational.apply("7/6"), Madhava.quarterPiFirst(2));
        assertEquals(Rational.apply("1/5"), Madhava.quarterPiSecond(1));
        assertEquals(Rational.apply("58/51"), Madhava.quarterPiSecond(2));
        assertEquals(Rational.apply("1/9"), Madhava.quarterPiThird(1));
        assertEquals(Rational.apply("142/123"), Madhava.quarterPiThird(2));
        assertEquals(Rational.apply("5/27"), Madhava.quarterPiFourth(1));
        assertEquals(Rational.apply("90/79"), Madhava.quarterPiFourth(2));
        assertFalse(Madhava.quarterPiFourth(2).isExactDouble());
    }

    @Test
    public void testTerm() {
        assertEquals(Rational.apply("-22/27"), Madhava.termFourth(1));
        assertEquals(Rational.apply("112/237"), Madhava.termFourth(2));
        assertEquals(Rational.apply("-38/117"), Madhava.termFourth(3));
    }

    @Test
    public void testPi() {
        assertEquals(approximatePi.divide(4).doubleValue(), Madhava.quarterPiFirst(1000).toDouble(), 1E-3);
        assertEquals(approximatePi.divide(4).doubleValue(), Madhava.quarterPiSecond(1000).toDouble(), 1E-3);
        assertEquals(approximatePi.divide(4).doubleValue(), Madhava.quarterPiThird(1000).toDouble(), 1E-3);
        assertEquals(approximatePi.divide(4).doubleValue(), Madhava.quarterPiFourth(1000).toDouble(), 1E-3);
    }
}