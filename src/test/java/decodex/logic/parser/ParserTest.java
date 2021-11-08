package decodex.logic.parser;

import decodex.data.exception.CommandException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.ParserException;
import decodex.logic.Command;
import decodex.logic.commands.ExitCommand;
import decodex.logic.commands.InputCommand;
import decodex.logic.commands.ListCommand;
import decodex.logic.commands.ResetCommand;
import decodex.logic.commands.SelectCommand;
import decodex.logic.commands.ShowCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUpParserTest() {
        parser = new Parser();
    }

    @Test
    public void getCommandType_stringWithNoSeparator_expectCommandTypeString() throws ParserException {
        String testInput = "test";
        String expectedOutput = "test";
        String commandType = parser.getCommandType(testInput);
        assertEquals(expectedOutput, commandType);
    }

    @Test
    public void getCommandType_stringWithOneSpaceSeparator_expectCommandTypeString() throws ParserException {
        String testInput = "test data";
        String expectedOutput = "test";
        String commandType = parser.getCommandType(testInput);
        assertEquals(expectedOutput, commandType);
    }

    @Test
    public void getUserArguments_userInputWithOneSpaceSeparator_expectOneArgumentString() throws ParserException {
        String testInput = "test data";
        String expectedOutput = "data";
        String arguments = parser.getInputString(testInput);
        assertEquals(expectedOutput, arguments);
    }


    /* The JUnit test methods below are for testing "normal" usage flows for the respective commands.*/
    @Test
    public void parseCommand_userInputSpecifyingValidData_expectInputCommand() throws ParserException,
            CommandException {
        String userInput = "input dummyData";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof InputCommand);
    }


    @Test
    public void parseCommand_userInputSpecifyingExit_expectExitCommand() throws ParserException, CommandException {
        String userInput = "exit";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseCommand_userInputSpecifyingValidListBlank_expectListCommand() throws ParserException,
            CommandException {
        String userInput = "list";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseCommand_userInputSpecifyingValidListModules_expectListCommand() throws ParserException,
            CommandException {
        String userInput = "list modules";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseCommand_userInputSpecifyingValidListRecipes_expectListCommand() throws ParserException,
            CommandException {
        String userInput = "list recipes";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseCommand_userInputSpecifyingValidListSpaces_expectListCommand() throws ParserException,
            CommandException {
        String userInput = "list      modules     ";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseCommand_userInputSpecifyingValidListAdditionalArguments_expectListCommand() {
        String userInput = "list modules a";
        assertThrows(CommandException.class, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_userInputSpecifyingReset_expectResetCommand() throws ParserException, CommandException {
        String userInput = "reset";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof ResetCommand);
    }

    @Test
    public void parseCommand_userInputSpecifyingValidSelectModule_expectSelectCommand() throws ParserException,
            CommandException {
        String userInput = "select module base64encode";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof SelectCommand);
    }

    @Test
    public void parseCommand_userInputSpecifyingShow_expectShowCommand() throws ParserException, CommandException {
        String userInput = "show";
        Command command = parser.parseCommand(userInput);
        assertTrue(command instanceof ShowCommand);
    }

    /* The JUnit test methods below are for testing additional arguments for the respective commands.*/
    @Test
    public void parseCommand_userInputSpecifyingShowAndOneArgument_expectCommandException() {
        String userInput = "show 1";
        assertThrows(CommandException.class, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_userInputSpecifyingHelpAndOneArgument_expectCommandException() {
        String userInput = "help 1";
        assertThrows(CommandException.class, () -> parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_userInputSpecifyingExitAndOneArgument_expectCommandException() {
        String userInput = "exit 1";
        assertThrows(CommandException.class, () -> parser.parseCommand(userInput));
    }
}
