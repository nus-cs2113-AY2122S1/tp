package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.BudgetList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBudgetTest {
    @Test
    void getDescription_rawCommand_expectDescription() {
        String inputCommand = "AddBudget JanuaryBudget 20.00 12";
        AddBudget currentBudget = new AddBudget();
        assertEquals("JanuaryBudget", currentBudget.getDescription("AddBudget JanuaryBudget 20.00 12"));
    }

    @Test
    void getAmount_rawCommand_expectAmount() {
        String inputCommand = "AddBudget JanuaryBudget 20.00 12";
        AddBudget currentBudget = new AddBudget();
        assertEquals(20.00, currentBudget.getAmount("AddBudget JanuaryBudget 20.00 12"));
    }

    @Test
    void getMonth_rawCommand_expectMonth() {
        String inputCommand = "AddBudget JanuaryBudget 20.00 12";
        AddBudget currentBudget = new AddBudget();
        assertEquals(12, currentBudget.getMonth("AddBudget JanuaryBudget 20.00 12"));
    }

    @Test
    void addBudget_rawCommand_budgetList_sizeOf1() {
        String description = "JanuaryBudget";
        double spendingLimit = 20.00;
        int month = 12;

        BudgetList currentBudgetList = new BudgetList();
        currentBudgetList.addBudgetList(description, spendingLimit, month);
        assertEquals(1, currentBudgetList.budgetArrayList.size());
    }
}
