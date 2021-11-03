package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.misc.ClearCommand;
import seedu.duke.command.misc.IncorrectCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ClearParserTest {
    private ClearParser parser;
    private Command result;

    @BeforeEach
    void setUp() {
        Command.workoutMode = 0;
    }

    @Test
    void parseInput_validInputs_returnsClearCommand() {
        parser = new ClearParser("clear workout");
        result = parser.parseInput();
        assertTrue(result instanceof ClearCommand);
        parser = new ClearParser("clear exercise 1");
        result = parser.parseInput();
        assertTrue(result instanceof ClearCommand);
    }

    @Test
    void parseInput_emptyInputs_returnsIncorrectCommand() {
        parser = new ClearParser("clear   ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new ClearParser("clear");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new ClearParser("clear exercise   ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new ClearParser("clear exercise");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }

    @Test
    void parseInput_InvalidInputs_returnsIncorrectCommand() {
        parser = new ClearParser("clear fdjkfksd");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new ClearParser("clear exercise a");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }
}
