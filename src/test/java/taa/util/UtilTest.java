package taa.util;

//@@author leyondlee

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilTest {
    @Test
    void isStringInteger_emptyString_expectFalse() {
        assertFalse(Util.isStringInteger(""));
    }

    @Test
    void isStringInteger_nullString_expectFalse() {
        assertFalse(Util.isStringInteger(null));
    }

    @Test
    void isStringInteger_positiveIntegerString_expectTrue() {
        String string = "1";
        assertTrue(Util.isStringInteger(string));
    }

    @Test
    void isStringInteger_negativeIntegerString_expectTrue() {
        String string = "-1";
        assertTrue(Util.isStringInteger(string));
    }

    @Test
    void isStringInteger_zeroIntegerString_expectTrue() {
        String string = "0";
        assertTrue(Util.isStringInteger(string));
    }

    @Test
    void isStringInteger_doubleString_expectFalse() {
        String string = "0.0";
        assertFalse(Util.isStringInteger(string));
    }

    @Test
    void isStringDouble_emptyString_expectFalse() {
        assertFalse(Util.isStringDouble(""));
    }

    @Test
    void isStringDouble_nullString_expectFalse() {
        assertFalse(Util.isStringDouble(null));
    }

    @Test
    void isStringDouble_positiveDoubleString_expectTrue() {
        String string = "0.0001";
        assertTrue(Util.isStringDouble(string));
    }

    @Test
    void isStringDouble_negativeDoubleString_expectTrue() {
        String string = "-0.0001";
        assertTrue(Util.isStringDouble(string));
    }

    @Test
    void isStringDouble_zeroDoubleString_expectTrue() {
        String string = "0.0";
        assertTrue(Util.isStringDouble(string));
    }

    @Test
    void isStringDouble_integerString_expectTrue() {
        String string = "1";
        assertTrue(Util.isStringDouble(string));
    }

    @Test
    void isStringDouble_nonDoubleString_expectFalse() {
        String string = "hello";
        assertFalse(Util.isStringDouble(string));
    }

    @Test
    void isStringBoolean_emptyString_expectFalse() {
        assertFalse(Util.isStringBoolean(""));
    }

    @Test
    void isStringBoolean_nullString_expectFalse() {
        assertFalse(Util.isStringBoolean(null));
    }

    @Test
    void isStringBoolean_trueBooleanString_expectTrue() {
        String string = "true";
        assertTrue(Util.isStringBoolean(string));
    }

    @Test
    void isStringBoolean_falseBooleanString_expectTrue() {
        String string = "false";
        assertTrue(Util.isStringBoolean(string));
    }

    @Test
    void isStringBoolean_nonBooleanString_expectFalse() {
        String string = "hello";
        assertFalse(Util.isStringBoolean(string));
    }

    @Test
    void isStringDouble_twoZeroesAndTwoDp_expectTrue() {
        String string = "1.00";
        assertTrue(Util.isStringDouble(string, 2));
    }

    @Test
    void isStringDouble_threeZeroesAndTwoDp_expectFalse() {
        String string = "1.000";
        assertFalse(Util.isStringDouble(string, 2));
    }

    @Test
    void isStringDouble_oneZeroAndTwoDp_expectFalse() {
        String string = "1.0";
        assertTrue(Util.isStringDouble(string, 2));
    }

    @Test
    void isStringDouble_noZeroAndTwoDp_expectTrue() {
        String string = "1.";
        assertTrue(Util.isStringDouble(string, 2));
    }

    @Test
    void isStringDouble_twoDotsAndTwoDp_expectFalse() {
        String string = "10.0.0";
        assertFalse(Util.isStringDouble(string, 2));
    }

    @Test
    void isStringDouble_onlyOneDotAndTwoDp_expectFalse() {
        String string = ".";
        assertFalse(Util.isStringDouble(string, 2));
    }

    @Test
    void isStringDouble_dotFirstAndOneZeroAndTwoDp_expectTrue() {
        String string = ".0";
        assertTrue(Util.isStringDouble(string, 2));
    }

    @Test
    void isStringDouble_dotFirstAndTwoZeroesAndTwoDp_expectTrue() {
        String string = ".00";
        assertTrue(Util.isStringDouble(string, 2));
    }

    @Test
    void isStringDouble_dotFirstAndThreeZeroesAndTwoDp_expectFalse() {
        String string = ".000";
        assertFalse(Util.isStringDouble(string, 2));
    }
}
