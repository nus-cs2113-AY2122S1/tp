package seedu.duke.data.profile.attributes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HeightTest {

    @Test
    void createHeight_validHeightInputs_expectTrue() {
        final Height h1 = new Height(190.0);
        final Height h2 = new Height(165.0);
        assertTrue(h1.isValid());
        assertTrue(h2.isValid());
    }

    @Test
    void createHeight_invalidHeightInputs_expectFalse() {
        final Height h1 = new Height(0);
        final Height h2 = new Height(-10);
        assertFalse(h1.isValid());
        assertFalse(h2.isValid());
    }

}