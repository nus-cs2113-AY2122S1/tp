package seedu.duke.data.profile.attributes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NameTest {

    @Test
    void createName_validNameInputs_expectTrue() {
        final Name n1 = new Name("John");
        final Name n2 = new Name("qwertyuiop<>:{},.;'[]'+=-!@#$%^&*()~`");
        assertTrue(n1.isValid());
        assertTrue(n2.isValid());
    }

    @Test
    void createName_invalidNameInputs_expectFalse() {
        final Name n1 = new Name("Hello/ooooo");
        final Name n2 = new Name("Delim|ters");
        assertFalse(n1.isValid());
        assertFalse(n2.isValid());
    }

}