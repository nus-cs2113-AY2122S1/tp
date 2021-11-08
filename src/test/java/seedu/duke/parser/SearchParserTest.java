package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.misc.SearchCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchParserTest {
    private SearchParser parser;
    private Command result;

    @Test
    void parseInput_validInput_returnSearchCommand() {
        parser = new SearchParser("search test");
        result = parser.parseInput();
        assertTrue(result instanceof SearchCommand);
    }

    @Test
    void parseInput_invalidInput_returnSearchCommand() {
        parser = new SearchParser("search");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
        parser = new SearchParser("search      ");
        result = parser.parseInput();
        assertTrue(result instanceof IncorrectCommand);
    }
}
