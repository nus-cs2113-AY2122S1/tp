package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneratorTest {
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
    void generateCommand_deleteWorkoutCommandNoWorkoutIndex_returnsIncorrectCommand() {
        String[] inputs = {"delete", "delete /w ", "delete "};
        for (String input : inputs) {
            Command result = generator.generateCommand(input);
            assertTrue(result instanceof IncorrectCommand);
        }
    }

    @Test
    void generateCommand_deleteWorkoutCommandCorrect_returnsDeleteCommand() {
        int testIndex = 1;
        String input = "delete /w " + testIndex;
        DeleteWorkoutCommand result = (DeleteWorkoutCommand) generator.generateCommand(input);
        assertEquals(result.getWorkoutIndex(), testIndex);
    }

    @Test
    void generateCommand_addExerciseCommandNoWorkoutIndex_returnsIncorrectCommand() {
        String input = "add /w /e Exercise /s 10 /r 100";
        Command result = generator.generateCommand(input);
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void generateCommand_addExerciseCommandNoSets_returnsIncorrectCommand() {
        String input = "add /w 1 /e Exercise /s  /r 100";
        Command result = generator.generateCommand(input);
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void generateCommand_addExerciseCorrect_returnsAddExerciseCommand() {
        String input = "add /w 1 /e Exercise /s 10 /r 100";
        Command result = generator.generateCommand(input);
        assertTrue(result instanceof AddExerciseCommand);
    }

    @Test
    void generateCommand_markExerciseAsDoneNoWorkoutIndex_returnsIncorrectCommand() {
        String input = "done /w 1 /e ";
        Command result = generator.generateCommand(input);
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void generateCommand_markExerciseAsDoneCorrect_returnsMarkExerciseAsDoneCommand() {
        String input = "done /w 1 /e 3";
        Command result = generator.generateCommand(input);
        assertTrue(result instanceof MarkExerciseAsDoneCommand);
    }
}
