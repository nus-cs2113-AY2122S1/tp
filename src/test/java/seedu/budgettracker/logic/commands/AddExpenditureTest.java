package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.logic.commands.exceptions.CommandException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AddExpenditureTest {
    public static final boolean IS_LOADING = true;

    @Test
    void execute_correctExpenditureParameters_addSuccessful() {
        String description = "Test Expenditure";
        double amount = 20.00;
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        Category category = Category.GENERAL;

        AllRecordList allRecordList = new AllRecordList();

        AddExpenditureCommand addExpenditureCommand = new AddExpenditureCommand(description, amount, date, category);
        addExpenditureCommand.setAllRecordList(allRecordList);
        addExpenditureCommand.execute(IS_LOADING);

        assertEquals(1, allRecordList.getExpenditureListSize(month));
    }

    @Test
    void execute_invalidAmount_throwsCommandException() {
        String description = "Test Expenditure";
        double negativeAmount = -20.00;
        double zeroAmount = 0.00;
        LocalDate date = LocalDate.now();
        Category category = Category.GENERAL;

        AddExpenditureCommand addNegativeExpenditureCommand = new AddExpenditureCommand(description, negativeAmount, date, category);
        assertThrows(CommandException.class, addNegativeExpenditureCommand::execute);

        AddExpenditureCommand addZeroExpenditureCommand = new AddExpenditureCommand(description, zeroAmount, date, category);
        assertThrows(CommandException.class, addZeroExpenditureCommand::execute);
    }

    @Test
    void execute_invalidDate_throwsCommandException() {
        String description = "Test Expenditure";
        double amount = 20;
        LocalDate futureDate = LocalDate.now().plusYears(1);
        LocalDate pastDate = LocalDate.now().plusYears(-1);
        Category category = Category.GENERAL;

        AllRecordList allRecordList = new AllRecordList();

        AddExpenditureCommand addFutureExpenditureCommand = new AddExpenditureCommand(description, amount, futureDate, category);
        addFutureExpenditureCommand.setAllRecordList(allRecordList);
        assertThrows(CommandException.class, addFutureExpenditureCommand::execute);

        AddExpenditureCommand addPastExpenditureCommand = new AddExpenditureCommand(description, amount, pastDate, category);
        addPastExpenditureCommand.setAllRecordList(allRecordList);
        assertThrows(CommandException.class, addPastExpenditureCommand::execute);


    }

    @Test
    public void equals() {
        AddExpenditureCommand addTest1Command = new AddExpenditureCommand("Test 1",
                10.00,
                LocalDate.now(),
                Category.GENERAL);
        AddExpenditureCommand addTest2Command = new AddExpenditureCommand("Test 2",
                10.00,
                LocalDate.now(),
                Category.GENERAL);

        //same object -> returns true
        assertEquals(addTest1Command, addTest1Command);

        //different types -> returns false
        assertFalse(addTest1Command.equals(1));

        // different expenditures -> returns false
        assertFalse(addTest1Command.equals(addTest2Command));
    }
}
