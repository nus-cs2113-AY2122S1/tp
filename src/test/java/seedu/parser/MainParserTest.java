package seedu.parser;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.command.Command;
import seedu.command.DeleteContactCommand;
import seedu.parser.MainParser;


public class MainParserTest {
    private MainParser mainParser;

    @BeforeEach
    public void setUp() {
        mainParser = new MainParser();
    }

    /**
     * Returns type of Command the parseCommand returns. This is required because most of the MainParser's
     * internal parsing methods are private.
     * @param userInput Expected user input
     * @param commandClass Expected Command class/type returned
     * @param <T> Type of Command expected
     * @return Command Returns a Command for verification
     */
    @SuppressWarnings("unchecked")
    private <T extends Command> T getParsedCommand(String userInput, Class<T> commandClass) {
        // This is because user input is normally trimmed by TextUi
        String trimmedUserInput = userInput.trim();
        final Command result = mainParser.parseCommand(trimmedUserInput);
        assertTrue(commandClass.isInstance(result));
        return (T) result;
    }
    @Test
    public void parseDeleteCommand() {
        final int testIndex = 1;
        final String testUserInput = "rm " + testIndex;
        final DeleteContactCommand testResultCommand = assertParseCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testIndex,testResultCommand.getDeletedIndex());
    }


    private <T extends Command> T assertParseCommand(String userInput, Class<T> commandClass) {
        final Command result = mainParser.parseCommand(userInput);
        assertTrue(commandClass.isInstance(result));
        return (T) result;
    }
}

