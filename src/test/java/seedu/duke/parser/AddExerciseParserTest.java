package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.command.misc.IncorrectCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddExerciseParserTest {
    private AddExerciseParser parser;
    private Command result;

    @BeforeEach
    void setUp() {
        Command.workoutMode = 0;
    }

    @Test
    void parseInput_validInputs_returnsAddExerciseCommand() {
        parser = new AddExerciseParser("add testExercise, 5 20, 1");
        result = parser.parseInput();
        assertEquals(result.getClass(), AddExerciseCommand.class);
    }

    @Test
    void parseInput_emptyInput_returnsIncorrectCommand() {
        parser = new AddExerciseParser("add ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new AddExerciseParser("add      ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseInput_missingDescription_returnsIncorrectCommand() {
        parser = new AddExerciseParser("add  ,5 20, 1");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseInput_invalidSetsAndReps_returnsIncorrectCommand() {
        parser = new AddExerciseParser("add testExercise,520 , 1");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new AddExerciseParser("add testExercise, a b, 1");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new AddExerciseParser("add testExercise,  , 1");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseInput_invalidWorkoutIndex_returnsIncorrectCommand() {
        parser = new AddExerciseParser("add testExercise, 5 20, ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new AddExerciseParser("add testExercise, 5 20, a");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new AddExerciseParser("add testExercise, 5 20 ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }
    
    @Test
    void parseInput_invalidWorkoutIndexButValidWorkoutMode_returnsAddExerciseCommand() {
        Command.workoutMode = 1;
        parser = new AddExerciseParser("add testExercise, 5 20");
        result = parser.parseInput();
        assertTrue(result instanceof AddExerciseCommand);
    }
    
    @Test
    void parseInput_workoutIndexInputWhenInsideWorkout_returnsCorrectAddExerciseCommand() {
        Command.workoutMode = 1;
        parser = new AddExerciseParser("add test, 5 5, 2");
        AddExerciseCommand check = (AddExerciseCommand) parser.parseInput();
        assertEquals(2,check.getWorkoutIndex());
    }
}
