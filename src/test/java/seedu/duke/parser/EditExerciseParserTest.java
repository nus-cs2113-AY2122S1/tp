package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.exercise.EditExerciseCommand;
import seedu.duke.command.misc.IncorrectCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EditExerciseParserTest {
    private EditExerciseParser parser;
    private Command result;

    @Test
    void parseInput_validInput_returnsEditExerciseCommand() {
        parser = new EditExerciseParser("edit 1, 2, testExercise, 5 20");
        result = parser.parseInput();
        assertTrue(result instanceof EditExerciseCommand);
    }

    @Test
    void parseInput_invalidExerciseOrWorkoutIndex_returnsIncorrectCommand() {
        parser = new EditExerciseParser("edit testExercise, 5 20");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new EditExerciseParser("edit 1, testExercise, 5 20");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new EditExerciseParser("edit , testExercise, 5 20");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new EditExerciseParser("edit a, b, testExercise, 5 20");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseInput_invalidSetsAndReps_returnsIncorrectCommand() {
        parser = new EditExerciseParser("edit 1, 2, testExercise,   ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new EditExerciseParser("edit 1, 2, testExercise, a b");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new EditExerciseParser("edit 1, 2, testExercise, 5");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }
    
    @Test
    void parseInput_invalidWorkoutIndexButValidWorkoutMode_returnsEditExerciseCommand() {
        Command.workoutMode = 1;
        parser = new EditExerciseParser("edit 1, testExercise, 1 1");
        result = parser.parseInput();
        assertTrue(result instanceof EditExerciseCommand);
    }
    
    @Test
    void parseInput_workoutIndexInputWhenInsideWorkout_returnsCorrectEditExerciseCommand() {
        Command.workoutMode = 1;
        parser = new EditExerciseParser("edit 2, 2, test, 1 1");
        EditExerciseCommand check = (EditExerciseCommand) parser.parseInput();
        assertEquals(2,check.getWorkoutIndex());
    }
}
