package happybit.command;

import happybit.exception.HappyBitException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {

    /**
     * Test runCommand to see if deleteCommand is working.
     */
    @Test
    void runCommand_habitName_success() {
        DeleteCommand deleteCommand = new DeleteCommand();
        deleteCommand.habits.add("habit 1");
        deleteCommand.habits.add("habit 2");
        deleteCommand.habits.add("habit 3");
        deleteCommand.habits.add("habit 4");

        deleteCommand.runCommand("habit 3");
        final ArrayList<String> actualResult = deleteCommand.habits;
        final ArrayList<String> expectedResult = new ArrayList<>();

        expectedResult.add("habit 1");
        expectedResult.add("habit 2");
        expectedResult.add("habit 4");

        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test runCommand when parameter is empty or blank (unexpected input).
     *
     * @throws HappyBitException Exception to prompt user for correct input.
     */
    @Test
    void runCommand_emptyOrBlankHabitName_expectException() throws HappyBitException{
        DeleteCommand deleteCommand = new DeleteCommand();
        deleteCommand.runCommand(null);
        assertThrows(HappyBitException.class, () -> deleteCommand.runCommand(null));
        assertThrows(HappyBitException.class, () -> deleteCommand.runCommand("   "));
    }
}