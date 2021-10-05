package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.commands.AddExpenseCommand;
import seedu.commands.Command;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.DeleteIncomeCommand;
import seedu.commands.ListIncomeCommand;

public class ParserTest {
    @Test
    public void testParseCommandForValidHelp() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("help");
        assertTrue(underTest.getClass() == HelpCommand.class);
    }

    @Test
    public void testParseCommandForInvalidList() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in           d");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void testParseCommandForValidList() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in ");
        assertTrue(underTest.getClass() == ListIncomeCommand.class);
    }

    @Test
    public void testParseCommandForInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("tfshsdfh");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void testParseCommandForInvalidAdd() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/tfshsdfh a/12a");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void testParseCommandForValidAdd() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/tfshsdfh a/123");
        assertTrue(underTest.getClass() == AddExpenseCommand.class);
    }

    @Test
    public void testParseCommandForInvalidDeleteIndex() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in      i/12a              ");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }
    
    @Test
    public void testParseCommandForInvalidTotal() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_in                 fddgf");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }
}
