package seedu.commands.income;

import seedu.commands.Command;
import seedu.entry.Income;
import seedu.exceptions.DuplicateIncomeException;
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
        try {
            finances.addIncome(income);
            ui.printIncomeAdded(income);
        } catch (DuplicateIncomeException e) {
            ui.printError(e.getMessage());
        }
        ;
    }
}
