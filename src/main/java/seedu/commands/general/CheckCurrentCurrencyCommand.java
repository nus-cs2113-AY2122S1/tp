package seedu.commands.general;

import seedu.commands.Command;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

public class CheckCurrentCurrencyCommand extends Command {

    @Override
    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        CurrencyType currency = finances.getCurrency();
        ui.printCurrentCurrency(currency);
    }
}
