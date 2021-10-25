package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.exercise.DisplayExercisesCommand;
import seedu.duke.command.misc.IncorrectCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for the DisplayExerciseParser class.
 * Test also applies to DeleteWorkoutParser class as the same logic applies to both classes
 */
class DisplayExerciseParserTest {
    private DisplayExerciseParser parser;
    private Command result;

    @BeforeEach
    void setUp() {
        Command.workoutMode = 0;
    }

    @Test
    void parseInput_validInput_returnsDisplayExerciseCommand() {
        parser = new DisplayExerciseParser("display 2");
        result = parser.parseInput();
        assertTrue(result instanceof DisplayExercisesCommand);
    }

    @Test
    void parseInput_invalidInput_returnsIncorrectCommand() {
        parser = new DisplayExerciseParser("display    ");
        result = parser.parseInput();
        assertEquals(IncorrectCommand.class, result.getClass());
        parser = new DisplayExerciseParser("display");
        result = parser.parseInput();
        assertEquals(result.getClass(), IncorrectCommand.class);
        parser = new DisplayExerciseParser("display a");
        result = parser.parseInput();
        assertEquals(result.getClass(), IncorrectCommand.class);
    }
    
    @Test
    void parseInput_invalidInputButValidWorkoutMode_returnsDisplayExerciseCommand() {
        Command.workoutMode = 1;
        parser = new DisplayExerciseParser("display   ");
        result = parser.parseInput();
        assertTrue(result instanceof DisplayExercisesCommand);
    }
    
    @Test
    void parseInput_workoutIndexInputWhenInsideWorkout_returnsCorrectDisplayExerciseCommand() {
        Command.workoutMode = 1;
        parser = new DisplayExerciseParser("display 2");
        DisplayExercisesCommand check = (DisplayExercisesCommand) parser.parseInput();
        assertEquals(2,check.getWorkoutIndex());
    }
}
