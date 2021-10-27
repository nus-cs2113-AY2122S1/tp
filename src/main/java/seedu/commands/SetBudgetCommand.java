package seedu.commands;

import seedu.entry.ExpenseCategory;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class SetBudgetCommand extends Command {
    ExpenseCategory category;
    double amount;

    public SetBudgetCommand(ExpenseCategory category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        budgetManager.setBudget(amount, category);
        ui.printBudgetSetConfirmation(amount, category);
    }
}
