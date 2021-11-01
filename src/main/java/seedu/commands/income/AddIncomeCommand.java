package seedu.commands.income;

import seedu.commands.Command;
import seedu.entry.Income;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class AddIncomeCommand extends Command {
    private Income income;

    public AddIncomeCommand(Income income) {
        this.income = income;
    }

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        finances.addIncome(income);
        ui.printIncomeAdded(income);
    }
}
