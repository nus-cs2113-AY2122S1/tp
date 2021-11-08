package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;


class CreateWorkoutParserTest {
    private CreateWorkoutParser parser;
    private Command result;

    @Test
    void parseInput_emptyInputs_returnsIncorrectCommand() {
        parser = new CreateWorkoutParser("create ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new CreateWorkoutParser("create    ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseInput_validInputs_returnsCreateWorkoutCommand() {
        parser = new CreateWorkoutParser("create testWorkout");
        result = parser.parseInput();
        assertTrue(result instanceof CreateWorkoutCommand);
        parser = new CreateWorkoutParser("create testWorkout, 2021-12-09");
        result = parser.parseInput();
        assertTrue(result instanceof CreateWorkoutCommand);
    }

    @Test
    void parseInput_invalidDeadline_returnsIncorrectCommand() {
        parser = new CreateWorkoutParser("create testWorkout, 09-12-2021");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }
    
    @Test
    void parseInput_missingWorkoutNameButValidDeadline_returnsIncorrectCommand() {
        parser = new CreateWorkoutParser("create , 2021-12-25");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }
}
