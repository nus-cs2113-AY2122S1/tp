package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.BudgetList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBudgetTest {

    @Test
    void deleteBudget_budgetListSize_1() {
        BudgetList currentBudgetList = new BudgetList();
        currentBudgetList.addBudget(08.00, 1);
        currentBudgetList.addBudget( 10.00, 2);
        currentBudgetList.deleteBudget(1, 1);
        assertEquals(1, currentBudgetList.getSize());
    }
}
