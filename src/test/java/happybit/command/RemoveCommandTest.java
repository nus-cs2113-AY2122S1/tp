package happybit.command;

import happybit.exception.HappyBitException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RemoveCommandTest {
//    GoalList testGoals = new GoalList();
//    /**
//     * Test runCommand to see if removeCommand is working.
//     */
//
//    @Test
//    void runCommand_goalIndex_success() throws IndexOutOfBoundsException, HappyBitException {
//        final Goal testGoal1 = new Goal("test", "07-Oct-2021", "21-Oct-2021");
//        final Goal testGoal2 = new Goal("test", "22-Oct-2021", "30-Oct-2021");
//        final Goal testGoal3 = new Goal("test", "01-Nov-2021", "21-Nov-2021");
//        testGoals.setGoal(testGoal1);
//        testGoals.setGoal(testGoal2);
//        testGoals.setGoal(testGoal3);
//        RemoveCommand remove = new RemoveCommand(0);
//        remove.runCommand();
//        assertEquals(2, testGoals.getListLength());
//    }
//
//    @Test
//    void runCommand_outOfBoundsGoalIndex_failure() throws HappyBitException, IndexOutOfBoundsException {
//        final Goal testGoal1 = new Goal("test", "07-Oct-2021", "21-Oct-2021");
//        final Goal testGoal2 = new Goal("test", "22-Oct-2021", "30-Oct-2021");
//        final Goal testGoal3 = new Goal("test", "01-Nov-2021", "21-Nov-2021");
//        testGoals.setGoal(testGoal1);
//        testGoals.setGoal(testGoal2);
//        testGoals.setGoal(testGoal3);
//        RemoveCommand remove = new RemoveCommand(6);
//        assertThrows(IndexOutOfBoundsException.class, () -> remove.runCommand());
//    }

}