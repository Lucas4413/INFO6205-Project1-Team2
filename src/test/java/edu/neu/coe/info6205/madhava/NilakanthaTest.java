package edu.neu.coe.info6205.madhava;

import com.phasmidsoftware.number.core.Rational;
import junit.framework.TestCase;
import org.junit.Test;

public class NilakanthaTest extends TestCase {

    final BigNumber approximatePi = BigNumber.value(3, 1415926536, true);

    @Test
    public void testPi() {
        assertEquals(approximatePi.doubleValue(), Nilakantha.calculatePi(1000).toDouble(), 1E-9);
        assertEquals(approximatePi.doubleValue(), Nilakantha.calculatePi(3000).toDouble(), 1E-10);
    }
}