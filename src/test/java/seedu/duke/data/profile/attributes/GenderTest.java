package seedu.duke.data.profile.attributes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GenderTest {

    @Test
    void createGender_validGenderInputs_expectTrue() {
        final Gender g1 = new Gender('F');
        final Gender g2 = new Gender('M');
        assertTrue(g1.isValid());
        assertTrue(g2.isValid());
    }

    @Test
    void createCalorieGoal_invalidGoalInputs_expectTrue() {
        final Gender g1 = new Gender('S');
        final Gender g2 = new Gender('D');
        assertFalse(g1.isValid());
        assertFalse(g2.isValid());
    }

}