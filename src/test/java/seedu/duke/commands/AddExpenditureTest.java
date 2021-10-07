package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.BudgetList;
import seedu.duke.data.records.Expenditure;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddExpenditureTest {
    @Test
    void addExpenditure_userInput_budgetList_1() {
        String description = "JanuaryBudget";
        double spendingLimit = 20.00;
        int month = 12;

        BudgetList currentBudgetList = new BudgetList();
        Expenditure toTest = new Expenditure(description, spendingLimit, month);
        currentBudgetList.addExpenditure(toTest);
        assertEquals(1, currentBudgetList.getSize());
    }
}
