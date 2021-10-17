package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.commands.AddExpenseCommand;
import seedu.commands.Command;
import seedu.commands.DeleteIncomeCommand;
import seedu.commands.HelpCommand;
import seedu.commands.InvalidCommand;
import seedu.commands.ListExpenseCommand;
import seedu.commands.ListIncomeCommand;
import seedu.commands.TotalIncomeCommand;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.InvalidExpenseAmountException;
import seedu.exceptions.InvalidExpenseDataFormatException;
import seedu.exceptions.InvalidIncomeAmountException;
import seedu.exceptions.InvalidIncomeDataFormatException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParserTest {
    @Test
    public void parseCommand_validHelpCommand_returnHelpCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("help");
        assertSame(underTest.getClass(), HelpCommand.class);
    }

    @Test
    public void parseCommand_invalidListCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in           d");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_validListIncomeCommand_returnListIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_in ");
        assertSame(underTest.getClass(), ListIncomeCommand.class);
    }

    @Test
    public void parseCommand_validListExpenseCommand_returnListIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("list_ex");
        assertSame(underTest.getClass(), ListExpenseCommand.class);
    }

    @Test
    public void parseCommand_invalidCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("tfshsdfh");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidAddExpenseCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/tfshsdfh a/12a c/qewew");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidAddExpenseCommandWithEmptyDescription_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex d/a/12a");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_validAddExpenseCommand_returnAddExpenseCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_ex    d/      tfshsdfh     a/          123  c/2wq2");
        assertSame(underTest.getClass(), AddExpenseCommand.class);
    }

    @Test
    public void parseCommand_invalidDeleteIncomeCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in      i/12a              ");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidDeleteExpenseCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_ex      i/12a              ");
        InvalidCommand test = (InvalidCommand) underTest; 
        assertSame("Only numeric inputs are allowed for index.", test.getMessage());
    }

    @Test
    public void parseCommand_validDeleteIncomeCommand_returnDeleteIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in      i/   12              ");
        assertSame(underTest.getClass(), DeleteIncomeCommand.class);
    }

    @Test
    public void parseCommand_invalidTotalIncomeCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_in                 fddgf");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_invalidTotalExpenseCommand_returnInvalidCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_ex                 fddgf");
        assertSame(underTest.getClass(), InvalidCommand.class);
    }

    @Test
    public void parseCommand_validTotalIncomeCommand_returnTotalIncomeCommand() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("total_in           ");
        assertSame(underTest.getClass(), TotalIncomeCommand.class);
    }

    @Test
    public void parseCommand_invalidInput_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("gfsbsfbgfsbfgs");
        InvalidCommand test = (InvalidCommand) underTest;
        assertSame("Invalid command. Use \"help\" to show the list of possible commands.", test.getMessage());
    }

    @Test
    public void parseCommand_invalidDeleteIncomeCommand_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("del_in i/aa");
        InvalidCommand test = (InvalidCommand) underTest;
        assertSame("Only numeric inputs are allowed for index.", test.getMessage());
    }

    @Test
    public void parseCommand_invalidAddIncomeCommand_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("add_in d/buy book a/aa c/qwqe");
        InvalidCommand test = (InvalidCommand) underTest;
        assertSame("Only numeric inputs are allowed for amount.", test.getMessage());
    }

    @Test
    public void parseCommand_invalidExitCommand_correctOutputWarningMessage() {
        Parser testParser = new Parser();
        Command underTest = testParser.parseCommand("bye");
        InvalidCommand test = (InvalidCommand) underTest;
        assertSame("Invalid command. Use \"help\" to show the list of possible commands.", test.getMessage());
    }

    @Test
    public void convertExpenseToData_validExpense_correctDataOutput() {
        Parser testParser = new Parser();
        LocalDate date = LocalDate.parse("2121-11-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Expense testExpense = new Expense("buy book", 12.33, "qwe", date);
        String testData = testParser.convertExpenseToData(testExpense);
        assertEquals("E, buy book, 12.33, qwe, 2121-11-11", testData);
    }

    @Test
    public void convertIncomeToData_validIncome_correctDataOutput() {
        Parser testParser = new Parser();
        LocalDate date = LocalDate.parse("2121-11-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Income testIncome = new Income("job", 1233.0, "qwe", date);
        String testData = testParser.convertIncomeToData(testIncome);
        assertEquals("I, job, 1233.0, qwe, 2121-11-11", testData);
    }

    @Test
    public void convertDataToExpense_validExpenseData_outputExpense() throws InvalidExpenseAmountException,
            InvalidExpenseDataFormatException {
        Parser testParser = new Parser();
        Expense testExpense = testParser.convertDataToExpense("E, sfa, 12, q, 2121-11-11");
        assertEquals("sfa", testExpense.getDescription());
        assertEquals(12, testExpense.getValue());
    }

    @Test
    public void convertDataToExpense_invalidExpenseDataWithBlankDescription_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidExpenseDataFormatException.class, () -> testParser.convertDataToExpense("E, , 12"));
    }

    @Test
    public void convertDataToExpense_invalidExpenseDataWithInvalidAmount_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidExpenseAmountException.class,
                    () -> testParser.convertDataToExpense("E, asd, 12a, qwe, 21-11-11"));
    }

    @Test
    public void convertDataToIncome_validIncomeData_outputIncome() throws InvalidIncomeAmountException,
            InvalidIncomeDataFormatException, DateTimeException {
        Parser testParser = new Parser();
        Income testIncome = testParser.convertDataToIncome("I, sfa, 12, qwe, 2121-11-11");
        assertEquals("sfa", testIncome.getDescription());
        assertEquals(12, testIncome.getValue());
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithBlankDescription_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeDataFormatException.class, 
                    () -> testParser.convertDataToIncome("I, , 12, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidAmount_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeAmountException.class, 
                    () -> testParser.convertDataToIncome("I, asd, 12a, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidMarker_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeDataFormatException.class, 
                    () -> testParser.convertDataToIncome("E, asd, 12a, q, 2121-11-11"));
    }

    @Test
    public void convertDataToIncome_invalidIncomeDataWithInvalidSeparator_throwsException() {
        Parser testParser = new Parser();
        assertThrows(InvalidIncomeDataFormatException.class, 
                    () -> testParser.convertDataToIncome("I,asd, 12, q, 2121-11-11"));
    }
}
