package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.RecordList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBudgetTest {

    @Test
    void deleteBudget_budgetAmount_0() {
        RecordList currentBudgetList = new RecordList();
        currentBudgetList.addBudget(08.00, 10, false);
        currentBudgetList.deleteBudget();
        assertEquals(0.00, currentBudgetList.getBudget().getAmount());
    }
}
