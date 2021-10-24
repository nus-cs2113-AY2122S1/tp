package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.command.misc.IncorrectCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarkExerciseAsDoneParserTest {
    private MarkExerciseAsDoneParser parser;
    private Command result;

    @Test
    void parseInput_validInput_returnsMarkExerciseAsDoneCommand() {
        parser = new MarkExerciseAsDoneParser("done 2, 1");
        result = parser.parseInput();
        assertTrue(result instanceof MarkExerciseAsDoneCommand);
    }

    @Test
    void parseInput_emptyInput_returnsIncorrectCommand() {
        parser = new MarkExerciseAsDoneParser("done");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new MarkExerciseAsDoneParser("done  ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseInput_invalidExerciseIndex_returnsIncorrectCommand() {
        parser = new MarkExerciseAsDoneParser("done, 1");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new MarkExerciseAsDoneParser("done a,1");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new MarkExerciseAsDoneParser("done    ,1");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseInput_invalidWorkoutIndex_returnsIncorrectCommand() {
        parser = new MarkExerciseAsDoneParser("done 1, ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new MarkExerciseAsDoneParser("done 1, a");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new MarkExerciseAsDoneParser("done 1 ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new MarkExerciseAsDoneParser("done 1,   ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }
}
