package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.EnterWorkoutCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnterWorkoutParserTest {
    private WorkoutList workoutList;


    @BeforeEach
    public void setUp() {
        Workout workout = new Workout("workout", LocalDate.parse("2021-10-10"));
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
}