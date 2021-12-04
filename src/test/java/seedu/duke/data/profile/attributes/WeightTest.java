package seedu.duke.data.profile.attributes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WeightTest {

    @Test
    void createWeight_validWeightInputs_expectTrue() {
        final Weight w1 = new Weight(60);
        final Weight w2 = new Weight(70.25);
        assertTrue(w1.isValid());
        assertTrue(w2.isValid());
    }

    @Test
    void createWeight_invalidWeightInputs_expectFalse() {
        final Weight w1 = new Weight(0);
        final Weight w2 = new Weight(-10);
        assertFalse(w1.isValid());
        assertFalse(w2.isValid());
    }
    
}