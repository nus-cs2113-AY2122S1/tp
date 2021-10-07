package happybit.habit;

import happybit.exception.HappyBitException;
import happybit.goal.Goal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HabitTest {

    /**
     * Test getDescription when a habit is initialised with a habit type.
     */
    @Test
    void getDescription_withHabitType_expectStringDescription() {
        final Habit testHabit = new Habit("test", HabitType.SLEEP);
        final String expectedResult = "[SL] test\n";
        final String actualResult = testHabit.getDescription();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test getDescription when a habit is initialised without a habit type.
     */
    @Test
    void getDescription_withoutHabitType_expectStringDescription() {
        final Habit testHabit = new Habit("test");
        final String expectedResult = "[DF] test\n";
        final String actualResult = testHabit.getDescription();
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test printGoalList when 3 goals are added to the habit.
     *
     * @throws HappyBitException If there are issues with the goal dates.
     */
    @Test
    void printGoalList_threeGoals_expectThreeLinesOfGoals() throws HappyBitException {
        final Habit testHabit = new Habit("test");
        final Goal testGoal1 = new Goal("goal1", "07-Oct-2021", "07-Oct-2050");
        final Goal testGoal2 = new Goal("goal2", "08-Oct-2021", "08-Oct-2050");
        final Goal testGoal3 = new Goal("goal3", "09-Oct-2021", "09-Oct-2050");
        testHabit.addGoal(testGoal1);
        testHabit.addGoal(testGoal2);
        testHabit.addGoal(testGoal3);
        assertEquals(3, testHabit.numberOfGoals());
        ArrayList<String> expectedResultList = new ArrayList<>();
        expectedResultList.add("goal1 (Start: 07-Oct-2021 | End: 07-Oct-2050)\n");
        expectedResultList.add("goal2 (Start: 08-Oct-2021 | End: 08-Oct-2050)\n");
        expectedResultList.add("goal3 (Start: 09-Oct-2021 | End: 09-Oct-2050)\n");
        for (int i = 0; i < testHabit.numberOfGoals(); i++) {
            assertEquals(expectedResultList.get(i), testHabit.goalList.get(i).getDescription());
        }
    }
}