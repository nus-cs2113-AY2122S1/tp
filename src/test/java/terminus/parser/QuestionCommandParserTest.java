package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.ExitCommand;
import terminus.command.HelpCommand;
import terminus.command.content.DeleteCommand;
import terminus.command.content.ViewCommand;
import terminus.command.content.question.AddQuestionCommand;
import terminus.command.content.question.TestCommand;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

public class QuestionCommandParserTest {

    private final String tempModule = "test";
    private QuestionCommandParser commandParser;

    @BeforeEach
    void setUp() {
        this.commandParser = QuestionCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("ex it"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("helpa"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("adda"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("vie wer"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("deleterr"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand(""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("eXiT a"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("HeLp    a"));
    }

    @Test
    void parseCommand_resolveExitCommand_success()
            throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("EXIT") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("   exit   ") instanceof ExitCommand);
    }

    @Test
    void parseCommand_resolveHelpCommand_success()
            throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("help") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HELP") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("   help   ") instanceof HelpCommand);
    }

    @Test
    void parseCommand_resolveAddCommand_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add \"test1\"test2\""));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"test1\" \"test2\""));
    }

    @Test
    void parseCommand_resolveAddCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("add \"test\" \"test1\"") instanceof AddQuestionCommand);
        assertTrue(commandParser.parseCommand("add \"    test     \" \"    test1   \"") instanceof AddQuestionCommand);
        assertTrue(commandParser.parseCommand("add \"username\" \"password\"") instanceof AddQuestionCommand);
    }

    @Test
    void parseCommand_resolveDeleteCommand_exceptionThrown() throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete abcd"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete -5"));
    }

    @Test
    void parseCommand_resolveDeleteCommand_success()
            throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("delete 1") instanceof DeleteCommand);
        assertTrue(commandParser.parseCommand("delete 2") instanceof DeleteCommand);
    }

    @Test
    void parseCommand_resolveViewCommand_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("view abcd"));
    }

    @Test
    void parseCommand_resolveViewCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("view") instanceof ViewCommand);
        assertTrue(commandParser.parseCommand("view 1") instanceof ViewCommand);
    }


    @Test
    void parseCommand_resolveTestCommand_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("test abcd"));
    }

    @Test
    void parseCommand_resolveTestCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("test") instanceof TestCommand);
        assertTrue(commandParser.parseCommand("test 1") instanceof TestCommand);
    }

    @Test
    void getCommandList_containsBasicCommands() {
        assertTrue(commandParser.getCommandList().contains("exit"));
        assertTrue(commandParser.getCommandList().contains("add"));
        assertTrue(commandParser.getCommandList().contains("back"));
        assertTrue(commandParser.getCommandList().contains("delete"));
        assertTrue(commandParser.getCommandList().contains("view"));
        assertTrue(commandParser.getCommandList().contains("help"));
        assertTrue(commandParser.getCommandList().contains("test"));
    }

    @Test
    void getWorkspace_isNote() {
        assertEquals(tempModule + " > question", commandParser.getWorkspace());
    }

    @Test
    void getHelpMenu_isNotEmpty() {
        assertTrue(commandParser.getHelpMenu().length > 0);
    }
}
