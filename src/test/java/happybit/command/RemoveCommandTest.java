package happybit.command;

import happybit.exception.HappyBitException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RemoveCommandTest {
    /**
     * Test runCommand to see if removeCommand is working.
     */
    @Test
    void runCommand_goalName_success() {
        RemoveCommand removeCommand = new RemoveCommand();
        removeCommand.goals.add("goal 1");
        removeCommand.goals.add("goal 2");
        removeCommand.goals.add("goal 3");
        removeCommand.goals.add("goal 4");

        removeCommand.runCommand("goal 3");
        final ArrayList<String> actualResult = removeCommand.goals;
        final ArrayList<String> expectedResult = new ArrayList<>();

        expectedResult.add("goal 1");
        expectedResult.add("goal 2");
        expectedResult.add("goal 4");

        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test runCommand when parameter is empty or blank (unexpected input).
     *
     * @throws HappyBitException Exception to prompt user for correct input.
     */
    @Test
    void runCommand_emptyOrBlankGoalName_expectException() throws HappyBitException{
        RemoveCommand removeCommand = new RemoveCommand();
        removeCommand.runCommand(null);
        assertThrows(HappyBitException.class, () -> removeCommand.runCommand(null));
        assertThrows(HappyBitException.class, () -> removeCommand.runCommand("   "));
    }
}