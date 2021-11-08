package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;
import seedu.budgettracker.logic.commands.exceptions.CommandException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddBudgetTest {

    @Test
    void addBudget_rawCommand_budgetListAmount_20() {
        double spendingLimit = 20.00;
        int month = 12;
        RecordList currentBudgetList = new RecordList(month);
        try {
            currentBudgetList.addBudget(spendingLimit);
        } catch (DuplicateBudgetException e) {
            e.printStackTrace();
        }
        assertEquals(20.00, currentBudgetList.getBudget().getAmount());
    }

    @Test
    void execute_invalidAmount_throwsCommandException() {
        double negativeAmount = -20.00;
        double zeroAmount = 0.00;
        double tooLargeAmount = 1000000001;
        int month = LocalDate.now().getMonthValue();

        AddBudgetCommand addNegativeBudgetCommand = new AddBudgetCommand(negativeAmount, month);
        assertThrows(CommandException.class, addNegativeBudgetCommand::execute);

        AddBudgetCommand addZeroBudgetCommand = new AddBudgetCommand(zeroAmount, month);
        assertThrows(CommandException.class, addZeroBudgetCommand::execute);

        AddBudgetCommand addTooLargeBudgetCommand = new AddBudgetCommand(tooLargeAmount, month);
        assertThrows(CommandException.class, addTooLargeBudgetCommand::execute);
    }

    @Test
    public void equals() {
        AddBudgetCommand addTest1Command = new AddBudgetCommand(100,
                LocalDate.now().getMonthValue());
        AddBudgetCommand addTest2Command = new AddBudgetCommand(200,
                LocalDate.now().getMonthValue());

        //same object -> returns true
        assertEquals(addTest1Command, addTest1Command);

        //different types -> returns false
        assertFalse(addTest1Command.equals(1));

        // different expenditures -> returns false
        assertFalse(addTest1Command.equals(addTest2Command));
    }
}
