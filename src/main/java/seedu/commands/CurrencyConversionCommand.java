package seedu.commands;

import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.utility.BudgetManager;
import seedu.utility.FinancialTracker;
import seedu.utility.Ui;

import java.util.ArrayList;

public class CurrencyConversionCommand extends Command {

    protected CurrencyTypes currencyType;
    protected double currencyMultiplier;

    public CurrencyConversionCommand(CurrencyTypes currencyType) {
        this.currencyType = currencyType;
        switch (currencyType) {
        case USD:
            currencyMultiplier = 1.5;
            break;
        case SGD:
            currencyMultiplier = 0.67;
            break;
        }
    }

    public void execute(FinancialTracker finances, Ui ui, BudgetManager budgetManager) {
        ArrayList<Expense> expenses = finances.getExpenses();
        ArrayList<Income> incomes = finances.getIncomes();
        convertExpenses(expenses, finances);
        convertIncomes(incomes, finances);
        ui.printCurrencyChangedConfirmation(currencyType);
    }

    public void convertExpenses(ArrayList<Expense> expenses, FinancialTracker finances) {
        for (Expense expense : expenses) {
            double newValue = finances.convertEntry(expense.getExpenseValue(), currencyMultiplier);
            assert newValue >= 0;
            expense.setExpenseValue(newValue);
        }
    }

    public void convertIncomes(ArrayList<Income> incomes, FinancialTracker finances) {
        for (Income income : incomes) {
            double newValue = finances.convertEntry(income.getIncomeValue(), currencyMultiplier);
            assert newValue >= 0;
            income.setIncomeValue(newValue);
        }
    }
}
