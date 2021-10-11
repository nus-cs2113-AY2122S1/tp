package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.commands.AddExpenseCommand;
import seedu.commands.Command;
import seedu.commands.DeleteIncomeCommand;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.ListIncomeCommand;
import seedu.commands.TotalIncomeCommand;

public class ParserTest {
    @Test
    public void parseCommand_validHelpCommand_returnHelpCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("help");
        assertTrue(underTest.getClass() == HelpCommand.class);
    }

    @Test
    public void parseCommand_invalidListCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in           d");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void parseCommand_validListCommand_returnListIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in ");
        assertTrue(underTest.getClass() == ListIncomeCommand.class);
    }

    @Test
    public void parseCommand_invalidCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("tfshsdfh");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidAddExpenseCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/tfshsdfh a/12a");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void parseCommand_validAddExpenseCommand_returnAddExpenseCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex    d/      tfshsdfh     a/          123");
        assertTrue(underTest.getClass() == AddExpenseCommand.class);
    }

    @Test
    public void parseCommand_invalidDeleteIncomeCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in      i/12a              ");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void parseCommand_validDeleteIncomeCommand_returnDeleteIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in      i/   12              ");
        assertTrue(underTest.getClass() == DeleteIncomeCommand.class);
    }
    
    @Test
    public void parseCommand_invalidTotalIncomeCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_in                 fddgf");
        assertTrue(underTest.getClass() == InvalidCommand.class);
    }

    @Test
    public void parseCommand_validTotalIncomeCommand_returnTotalIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_in           ");
        assertTrue(underTest.getClass() == TotalIncomeCommand.class);
    }

    @Test
    public void parseCommand_invalidInput_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("gfsbsfbgfsbfgs");
        InvalidCommand test = (InvalidCommand) underTest; 
        assertTrue(test.getMessage() == "Invalid command. Use \"help\" to show the list of possible commands.");
    }

    @Test
    public void parseCommand_invalidDeleteIncomeCommand_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in i/aa");
        InvalidCommand test = (InvalidCommand) underTest;
        assertTrue(test.getMessage() == "Only numeric inputs are allowed for index.");
    }

    @Test
    public void parseCommand_invalidAddIncomeCommand_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_in d/buy book a/aa");
        InvalidCommand test = (InvalidCommand) underTest;
        assertTrue(test.getMessage() == "Only numeric inputs are allowed for amount.");
    }
}
