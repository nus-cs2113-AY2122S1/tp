package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    //featureUnderTest_testScenario_expectedBehavior()
    @Test
    void parseCommand_emptyInput_returnsIncorrectCommand() {
        String[] emptyInputs = {"", " ", "\n"};
        for (String emptyInput : emptyInputs) {
            Command result = parser.parseCommand(emptyInput);
            assertTrue(result instanceof IncorrectCommand);
        }
    }

    @Test
    void parseCommand_unknownCommandWord_returnsIncorrectCommand() {
        String input = "lskdflskdjf";
        Command result = parser.parseCommand(input);
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseCommand_deleteWorkoutCommandWrong_returnsIncorrectCommand() {
        String[] inputs = {"delete", "delete /w ", "delete "};
        for (String input : inputs) {
            Command result = parser.parseCommand(input);
            assertTrue(result instanceof IncorrectCommand);
        }
    }

    @Test
    void parseCommand_deleteWorkoutCommandCorrect_returnsDeleteCommand() {
        int testIndex = 1;
        String[] inputs = {"delete /w" + testIndex, "delete /w   " + testIndex};
        for (String input : inputs) {
            DeleteWorkoutCommand result = (DeleteWorkoutCommand) parser.parseCommand(input);
            assertEquals(result.getWorkoutIndex(),testIndex);
        }
    }

}
