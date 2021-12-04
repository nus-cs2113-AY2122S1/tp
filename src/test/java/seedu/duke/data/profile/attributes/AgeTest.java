package seedu.duke.data.profile.attributes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AgeTest {

    @Test
    void createAge_positiveInputs_expectTrue() {
        final Age a1 = new Age(10);
        final Age a2 = new Age(25);
        final Age a3 = new Age(45);
        final Age a4 = new Age(57);
        final Age a5 = new Age(80);
        assertTrue(a1.isValid());
        assertTrue(a2.isValid());
        assertTrue(a3.isValid());
        assertTrue(a4.isValid());
        assertTrue(a5.isValid());
    }

    @Test
    void createAge_nonPositiveInput_expectFalse() {
        final Age a1 = new Age(0);
        final Age a2 = new Age(-25);
        final Age a3 = new Age(-45);
        final Age a4 = new Age(-57);
        final Age a5 = new Age(-80);
        assertFalse(a1.isValid());
        assertFalse(a2.isValid());
        assertFalse(a3.isValid());
        assertFalse(a4.isValid());
        assertFalse(a5.isValid());
    }

}