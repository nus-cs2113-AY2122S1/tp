package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseCommandForHelp() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("help");
        assertTrue(underTest.getClass() == HelpCommand.class);
    }

    @Test
    public void testParseCommandForListIncome() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in dshdtfshsdfh");
        assertTrue(underTest.getClass() == ListIncomeCommand.class);
    }

    @Test
    public void testParseCommandForInvalid() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("tfshsdfh");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void testParseCommandForInvalidAddExpense() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/tfshsdfh");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void testParseCommandForInvalidDeleteIndex() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in i/123              ");
        assertTrue(underTest.getClass() == DeleteIncomeCommand.class);
    }
}
