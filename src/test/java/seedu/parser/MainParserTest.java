package seedu.parser;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.command.Command;
import seedu.command.DeleteContactCommand;
import seedu.command.ViewCommand;
import seedu.parser.MainParser;


public class MainParserTest {
    private MainParser mainParser;

    @BeforeEach
    public void setUp() {
        mainParser = new MainParser();
    }

    @Test
    public void parseDeleteCommand() {
        final int testIndex = 1;
        final String testUserInput = "rm " + testIndex;
        final DeleteContactCommand testResultCommand = assertParseCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testIndex,testResultCommand.getDeletedIndex());
    }

    @Test
    public void parseViewCommand() {
        final int testIndex = 1;
        final String testUserInput = "view " + testIndex;
        final ViewCommand testResultCommand = assertParseCommand(testUserInput, ViewCommand.class);
        assertEquals(testIndex,testResultCommand.getIndex());
    }


    private <T extends Command> T assertParseCommand(String userInput, Class<T> commandClass) {
        final Command result = mainParser.parseCommand(userInput);
        assertTrue(commandClass.isInstance(result));
        return (T) result;
    }
}

