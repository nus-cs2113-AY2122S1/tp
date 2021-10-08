package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.RecordList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteBudgetTest {

    @Test
    void deleteBudget_budgetListSize_1() {
        RecordList currentBudgetList = new RecordList();
        currentBudgetList.addBudget(08.00, 01);
        currentBudgetList.addBudget(10.00, 02);
        currentBudgetList.deleteBudget(1, 1);
        assertEquals(1, currentBudgetList.getSize());
    }
}
