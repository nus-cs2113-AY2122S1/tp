package seedu.commands;

import seedu.entry.Entry;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.util.ArrayList;

public class CurrencyConversionCommand extends Command {
    protected CurrencyTypes from;
    protected  CurrencyTypes to;
    protected double exchangeRate;

    public CurrencyConversionCommand(CurrencyTypes to) {
        this.from = CurrencyTypes.SGD;
        this.to = to;
    }

    public double determineExchangeRate(CurrencyTypes to) {
        switch (to) {
        case USD:
            return exchangeRate = 0.74;
        case SGD:
            return exchangeRate = 1.35;
        default:
            return exchangeRate = 1.0;
        }
    }

    public boolean isNotBaseYear(CurrencyTypes from) {
        return !from.equals(CurrencyTypes.SGD);
    }

    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        if (finances.getCurrency() == to) {
            ui.printSameCurrencyTypeMessage(to);
            return;
        }
        ArrayList<Entry> entries = finances.getEntries();
        convertEntries(entries, finances);
        ui.printCurrencyChangedConfirmation(to);
    }

    public void convertEntries(ArrayList<Entry> entries, FinancialTracker finances) {
        for (Entry entry : entries) {
            double newValue = convertEntry(from, to, entry.getValue());
            assert newValue >= 0;
            entry.setValue(newValue);
        }
        finances.setCurrency(to);
    }

    private double convertEntry(CurrencyTypes from, CurrencyTypes to, double value) {
        if (isNotBaseYear(from)) {
            double fromRate = determineExchangeRate(from);
            assert fromRate >= 0;
            value = (value / fromRate);
        }
        double toRate = determineExchangeRate(to);
        assert toRate >= 0;
        return (value * toRate);
    }
}
