package seedu.utility;

import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.IncomeEntryNotFoundException;

import java.util.ArrayList;

public class FinancialTracker {
    private ArrayList<Expense> expenses;
    private ArrayList<Income> incomes;

    public FinancialTracker() {
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
    }
    
    public void addExpense(Expense expense) {
        expenses.add(expense);
        assert !expenses.isEmpty();
    }

    public void addIncome(Income income) {
        incomes.add(income);
        assert !incomes.isEmpty();
    }
    
    public Expense removeExpense(int expenseIndex) throws ExpenseEntryNotFoundException {
        try {
            return expenses.remove(expenseIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ExpenseEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
        }
    }

    public Income removeIncome(int incomeIndex) throws IncomeEntryNotFoundException {
        return null;
    }

    public ArrayList<Expense> listExpenses() {
        assert expenses != null;
        return expenses;
    }

    public ArrayList<Income> listIncomes() {
        assert incomes != null;
        return incomes;
    }

    public double getTotalExpense() {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            assert expense.getValue() >= 0;
            totalExpense += expense.getValue();
        }
        return totalExpense;
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        for (Income income : incomes) {
            assert income.getValue() >= 0;
            totalIncome += income.getValue();
        }
        return totalIncome;
    }

    //method used for testing
    public int getExpenseSize() {
        return expenses.size();
    }

    //method used for testing
    public int getIncomeSize() {
        return incomes.size();
    }
    
    public boolean isExpensesEmpty() {
        return expenses.isEmpty();
    }

    public boolean isIncomesEmpty() {
        return incomes.isEmpty();
    }

    //method used for testing
    public Expense getExpense(int expenseIndex) {
        return expenses.get(expenseIndex);
    }

    //method used for testing
    public Income getIncome(int incomeIndex) {
        return incomes.get(incomeIndex);
    }
}
