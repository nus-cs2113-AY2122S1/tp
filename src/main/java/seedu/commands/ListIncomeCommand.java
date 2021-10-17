package seedu.commands;

import seedu.entry.Income;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.util.ArrayList;

public class ListIncomeCommand extends Command {
    @Override
    public void execute(FinancialTracker finances, Ui ui) {
        ArrayList<Income> incomes = finances.getIncomes(); 
        ui.listIncome(incomes);
    }
}
