package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.BudgetList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBudgetTest {

    @Test
    void deleteBudget_budgetListSize_1() {
        BudgetList currentBudgetList = new BudgetList();
        currentBudgetList.addBudget("TestBudget1", 08.00, 01);
        currentBudgetList.addBudget("TestBudget2", 10.00, 02);
        currentBudgetList.deleteBudget(1);
        assertEquals(1, currentBudgetList.getSize());
    }
}
