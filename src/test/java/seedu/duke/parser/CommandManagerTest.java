package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandManagerTest {
    private CommandManager generator;

    @BeforeEach
    public void setUp() {
        generator = new CommandManager();
    }

    @Test
    void generateCommand_emptyInput_returnsIncorrectCommand() {
        String[] emptyInputs = {"", " ", "\n"};
        for (String emptyInput : emptyInputs) {
            Command result = generator.generateCommand(emptyInput);
            assertTrue(result instanceof IncorrectCommand);
        }
    }

    @Test
    void generateCommand_unknownCommandWord_returnsIncorrectCommand() {
        String input = "lskdflskdjf";
        Command result = generator.generateCommand(input);
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void generateCommand_deleteWorkoutCommandCorrect_returnsDeleteCommand() {
        int testIndex = 1;
        String input = "delete " + testIndex;
        DeleteWorkoutCommand result = (DeleteWorkoutCommand) generator.generateCommand(input);
        assertEquals(result.getWorkoutIndex(), testIndex);
    }
}
