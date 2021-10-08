package seedu.parser;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.command.AddContactCommand;
import seedu.command.Command;
import seedu.command.DeleteContactCommand;
import seedu.command.FailedCommand;
import seedu.command.ViewCommand;


public class MainParserTest {
    private MainParser mainParser;
    private String testUserInput;
    private int testIndex; // For commands that require index

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
    public void parseDeleteCommand_validIndex_expectDeleteContactIndexMatch() {
        testIndex = 1;
        testUserInput = "rm " + testIndex;
        final DeleteContactCommand testResultCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testIndex, testResultCommand.getDeletedIndex());
    }

    @Test
    public void parseAddCommand_validInputsWithIrregularSpaces_expectAddContactCommand() {
        testUserInput = "         add -n   andre -g  ng-andre  ";
        AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        AddContactCommand expectedCommand = new AddContactCommand("andre", "ng-andre");
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
    }

    @Test
    public void parseAddCommand_validInputsWithExtraCharacters_expectAddContactCommand() {
        testUserInput = "         add   1231267azldasd -n   marcus    -g  marcus-bory  ";
        AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        AddContactCommand expectedCommand = new AddContactCommand("marcus", "marcus-bory");
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
    }

    @Test
    public void parseAddCommand_validInputs_expectAddContactCommand() {
        testUserInput = "add -n andre -g ng-andre";
        final AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        final AddContactCommand expectedCommand = new AddContactCommand("andre", "ng-andre");
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
    }

    @Test
    public void parseViewCommand_validIndex_expectViewContactIndexMatch() {
        final int testIndex = 1;
        final String testUserInput = "view " + testIndex;
        final ViewCommand testResultCommand = getParsedCommand(testUserInput, ViewCommand.class);
        assertEquals(testIndex,testResultCommand.getIndex());
    }

    @Test
    public void parseAddCommand_missingName_expectFailedCommand() {
        testUserInput = " add -g      github ";
        final FailedCommand actualCommand = getParsedCommand(testUserInput, FailedCommand.class);
        final FailedCommand expectedCommand = new FailedCommand(FailedCommandType.MISSING_NAME);
        assertEquals(expectedCommand.getType(), actualCommand.getType());
    }
}

