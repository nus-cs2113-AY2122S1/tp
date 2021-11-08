package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.misc.IncorrectCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecommendWorkoutParserTest {

    @Test
    void parseInput_invalidUserInput_commandOfTypeIncorrectCommandReturned() {
        Parser p1 = new RecommendWorkoutParser("recommend ehbstbehg");
        Parser p2 = new RecommendWorkoutParser("recommend ");
        assertEquals(IncorrectCommand.class, p1.parseInput().getClass());
        assertEquals(IncorrectCommand.class, p2.parseInput().getClass());
    }

}