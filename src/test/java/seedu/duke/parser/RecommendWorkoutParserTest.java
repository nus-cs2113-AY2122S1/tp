package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.workout.EnterWorkoutCommand;
import seedu.duke.command.workout.RecommendWorkoutCommand;

import static org.junit.jupiter.api.Assertions.*;

class RecommendWorkoutParserTest {

    @Test
    void parseInput_validUserInput_correctCommandReturned() {
        RecommendWorkoutCommand expectedCommand = new RecommendWorkoutCommand("beginner");
        Parser p = new RecommendWorkoutParser("recommend beginner");
        RecommendWorkoutCommand actualCommand = (RecommendWorkoutCommand) p.parseInput();
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    void parseInput_invalidUserInput_commandOfTypeIncorrectCommandReturned() {
        Parser p1 = new EnterWorkoutParser("recommend ehbstbehg");
        Parser p2 = new EnterWorkoutParser("recommend");
        assertEquals(IncorrectCommand.class, p1.parseInput().getClass());
        assertEquals(IncorrectCommand.class, p2.parseInput().getClass());
    }
}