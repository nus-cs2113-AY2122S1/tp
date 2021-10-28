package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Budget;

public class EditBudgetCommand extends EditCommand {
    public int month;
    public double amount;

    public EditBudgetCommand(int month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    public void execute(boolean isLoadingStorage) {
        Budget targetBudget = allRecordList.editBudget(this.month, this.amount, isLoadingStorage);
        System.out.println("Budget successfully edited!");
        System.out.println("New budget: " + targetBudget);
    }
}
