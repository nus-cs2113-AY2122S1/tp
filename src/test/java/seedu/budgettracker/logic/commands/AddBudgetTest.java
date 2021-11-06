package seedu.budgettracker.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBudgetTest {

    @Test
    void addBudget_rawCommand_budgetListAmount_20() {
        double spendingLimit = 20.00;
        int month = 12;
        RecordList currentBudgetList = new RecordList(month);
        try {
            currentBudgetList.addBudget(spendingLimit, false);
        } catch (DuplicateBudgetException e) {
            e.printStackTrace();
        }
        assertEquals(20.00, currentBudgetList.getBudget().getAmount());
    }

    
}
