package seedu.duke.data.profile.attributes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ActivityFactorTest {

    @Test
    void createActivityFactor_validFactors_expectTrue() {
        final ActivityFactor a1 = new ActivityFactor(1);
        final ActivityFactor a2 = new ActivityFactor(2);
        final ActivityFactor a3 = new ActivityFactor(3);
        final ActivityFactor a4 = new ActivityFactor(4);
        final ActivityFactor a5 = new ActivityFactor(5);
        assertTrue(a1.isValid());
        assertTrue(a2.isValid());
        assertTrue(a3.isValid());
        assertTrue(a4.isValid());
        assertTrue(a5.isValid());
    }

    @Test
    void createActivityFactor_invalidFactors_expectFalse() {
        final ActivityFactor a1 = new ActivityFactor(-1);
        final ActivityFactor a2 = new ActivityFactor(6);
        assertFalse(a1.isValid());
        assertFalse(a2.isValid());
    }

}