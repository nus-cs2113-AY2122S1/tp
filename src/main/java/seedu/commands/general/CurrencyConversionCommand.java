package seedu.commands.general;

import seedu.budget.Budget;
import seedu.commands.Command;
import seedu.entry.Entry;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.util.ArrayList;

public class CurrencyConversionCommand extends Command {
    protected CurrencyType from;
    protected CurrencyType to;
    protected double exchangeRate;

    public CurrencyConversionCommand(CurrencyType to) {
        this.from = CurrencyType.SGD;
        this.to = to;
    }

    public double determineExchangeRate(CurrencyType to) {
        switch (to) {
        case USD:
            return exchangeRate = 0.74;
        case SGD:
            return exchangeRate = 1.35;
        default:
            return exchangeRate = 1.00;
        }
    }

    public boolean isBaseYear(CurrencyType from) {
        return !from.equals(CurrencyType.SGD);
    }

    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        if (finances.getCurrency() == to) {
            ui.printSameCurrencyTypeMessage(to);
            return;
        }
        ArrayList<Entry> entries = finances.getEntries();
        ArrayList<Budget> budgets = budgetManager.getBudgets();
        convertEntries(entries, finances);
        convertBudgets(budgets, finances, budgetManager);
        ui.printCurrencyChangedConfirmation(to);
    }

    public void convertEntries(ArrayList<Entry> entries, FinancialTracker finances) {
        for (Entry entry : entries) {
            double newValue = convertItem(from, to, entry.getValue());
            assert newValue >= 0;
            entry.setValue(newValue);
        }
        finances.setCurrency(to);
    }

    public void convertBudgets(ArrayList<Budget> budgets, FinancialTracker finances, BudgetManager budgetManager) {
        for (Budget budget : budgets) {
            double newValue = convertItem(from, to, budget.getLimit());
            budget.setLimit(newValue);
        }
        finances.setCurrency(to);
    }

    private double convertItem(CurrencyType from, CurrencyType to, double value) {
        if (isBaseYear(from)) {
            double fromRate = determineExchangeRate(from);
            assert fromRate >= 0;
            value = (value / fromRate);
        }
        double toRate = determineExchangeRate(to);
        assert toRate >= 0;
        return (value * toRate);
    }
}
