package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.workout.EnterWorkoutCommand;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnterWorkoutParserTest {
    private WorkoutList workoutList;


    @BeforeEach
    public void setUp() {
        Command.workoutMode = 0;
        Workout workout = new Workout("workout");
        workoutList = new WorkoutList();
        workoutList.addWorkout(workout);
    }

    @Test
    void parseInput_validUserInput_correctCommandReturned() {
        EnterWorkoutCommand expectedCommand = new EnterWorkoutCommand(1);
        Parser p = new EnterWorkoutParser("enter 1");
        EnterWorkoutCommand actualCommand = (EnterWorkoutCommand) p.parseInput();
        assertEquals(expectedCommand.getWorkoutIndex(), actualCommand.getWorkoutIndex());
    }

    @Test
    void parseInput_invalidUserInput_commandOfTypeIncorrectCommandReturned() {
        Parser p1 = new EnterWorkoutParser("enter A");
        Parser p3 = new EnterWorkoutParser("enter");
        assertEquals(IncorrectCommand.class, p1.parseInput().getClass());
        assertEquals(IncorrectCommand.class, p3.parseInput().getClass());
    }
    
    @Test
    void parseInput_enterAnotherWorkoutWhenInsideOneWorkout_returnsCorrectEnterWorkoutCommand() {
        Command.workoutMode = 1;
        Parser p1 = new EnterWorkoutParser("enter 2");
        EnterWorkoutCommand check = (EnterWorkoutCommand) p1.parseInput();
        assertEquals(2,check.getWorkoutIndex());
    }
}