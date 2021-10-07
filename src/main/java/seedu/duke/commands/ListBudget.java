package seedu.duke.commands;

import seedu.duke.data.BudgetList;

public class ListBudget {
    public void listBudget(BudgetList currentBudgetList) {
        int budgetListLength = BudgetList.numberOfBudget;
        for (int i = 0; i < budgetListLength; i += 1) {
            currentBudgetList.printBudgetList(i);
        }
    }
}
