/*
package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.commands.AddExpenseCommand;
import seedu.commands.Command;
import seedu.commands.DeleteIncomeCommand;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.ListIncomeCommand;
import seedu.commands.TotalIncomeCommand;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.InvalidExpenseAmountException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeAmountException;
import seedu.exceptions.InvalidIncomeDataFormatException;

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
    public void parseCommand_invalidAddExpenseCommandWithEmptyDescription_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/a/12a");
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

    @Test
    public void convertExpenseToData_validExpense_correctDataOutput() {
        Parser testParser = new Parser();
        Expense testExpense = new Expense("buy book", 12.33);
        String testData = testParser.convertExpenseToData(testExpense);
        assertTrue(testData.equals("E, buy book, 12.33"));
    }

    @Test
    public void convertIncomeToData_validIncome_correctDataOutput() {
        Parser testParser = new Parser();
        Income testIncome = new Income("job", 1233.0);
        String testData = testParser.convertIncomeToData(testIncome);
        assertTrue(testData.equals("I, job, 1233.0"));
    }

    @Test
    public void convertDataToExpense_validExpenseData_outputExpense() throws InvalidExpenseAmountException,
            InvalidExpenseDataFormatException {
        Parser testParser = new Parser();
        Expense testExpense = testParser.convertDataToExpense("E, sfa, 12");
        assertEquals("sfa", testExpense.getDescription());
        assertTrue(testExpense.getValue() == 12);
    }

    @Test
    public void convertDataToExpense_invalidExpenseDataWithBlankDescription_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidExpenseDataFormatException.class, () -> testParser.convertDataToExpense("E, , 12"));
    }

    @Test
    public void convertDataToExpense_invalidExpenseDataWithInvalidAmount_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidExpenseAmountException.class, () -> testParser.convertDataToExpense("E, asd, 12a"));
    }

    @Test
    public void convertDataToIncome_validIncomeData_outputIncome() throws InvalidIncomeAmountException,
            InvalidIncomeDataFormatException {
        Parser testParser = new Parser();
        Income testIncome = testParser.convertDataToIncome("I, sfa, 12");
        assertEquals("sfa", testIncome.getDescription());
        assertTrue(testIncome.getValue() == 12);
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithBlankDescription_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeDataFormatException.class, () -> testParser.convertDataToIncome("I, , 12"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidAmount_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeAmountException.class, () -> testParser.convertDataToIncome("I, asd, 12a"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidMarker_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeDataFormatException.class, () -> testParser.convertDataToIncome("E, asd, 12a"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidSeparator_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeDataFormatException.class, () -> testParser.convertDataToIncome("I,asd, 12"));
    }
}
*/
