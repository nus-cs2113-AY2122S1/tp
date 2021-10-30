package taa.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class UtilTest {

    @Test
    void isStringInteger_emptyString_expectFalse() {
        assertFalse(Util.isStringInteger(""));
    }

    @Test
    void isStringDouble_emptyString_expectFalse() {
        assertFalse(Util.isStringDouble(""));
    }

    @Test
    void isStringBoolean_emptyString_expectFalse() {
        assertFalse(Util.isStringBoolean(""));
    }
}