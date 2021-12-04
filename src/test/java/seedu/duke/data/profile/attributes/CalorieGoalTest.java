package seedu.duke.data.profile.attributes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CalorieGoalTest {

    @Test
    void createCalorieGoal_validGoalInputs_expectTrue() {
        final CalorieGoal c1 = new CalorieGoal(2499);
        final CalorieGoal c2 = new CalorieGoal(-2499);
        assertTrue(c1.isValid());
        assertTrue(c2.isValid());
    }

    @Test
    void createCalorieGoal_invalidGoalInputs_expectTrue() {
        final CalorieGoal c1 = new CalorieGoal(239030);
        final CalorieGoal c2 = new CalorieGoal(-23435);
        assertFalse(c1.isValid());
        assertFalse(c2.isValid());
    }

}