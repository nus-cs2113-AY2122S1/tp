package seedu.utility;

import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.IncomeEntryNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinancialTracker {
    private ArrayList<Expense> expenses;
    private ArrayList<Income> incomes;

    public FinancialTracker() {
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        int expenseSize = 0;
        assert (expenseSize = expenses.size()) >= 0;
        expenses.add(expense);
        assert !expenses.isEmpty();
        assert expenses.size() > expenseSize;
    }

    public void addIncome(Income income) {
        int incomeSize = 0;
        assert (incomeSize = incomes.size()) >= 0;
        incomes.add(income);
        assert !incomes.isEmpty();
        assert incomes.size() > incomeSize;
    }

    public int indexOffset(int index) {
        return index - 1;
    }

    public Expense removeExpense(int expenseIndex) throws ExpenseEntryNotFoundException {
        try {
            return expenses.remove(indexOffset(expenseIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new ExpenseEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
        }
    }

    public Income removeIncome(int incomeIndex) throws IncomeEntryNotFoundException {
        try {
            return incomes.remove(indexOffset(incomeIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new IncomeEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
        }
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
        assert totalExpense >= 0;
        return totalExpense;
    }

    public double getTotalIncome() {
        double totalIncome = 0;
        for (Income income : incomes) {
            assert income.getValue() >= 0;
            totalIncome += income.getValue();
        }
        assert totalIncome >= 0;
        return totalIncome;
    }
    
    public double getTotalExpenseBetween(LocalDate start, LocalDate end) {
        List<Expense> accumulatedExpense =
            expenses.stream()
                .filter(item -> (item.getDate().isAfter(start) || item.getDate().isEqual(start)) 
                        && (item.getDate().isBefore(end) || item.getDate().isEqual(end)))
                .collect(Collectors.toList());
        double count = 0;
        for (Expense o: accumulatedExpense) {
            count += o.getValue();    
        }
        return count;
    }

    public double getTotalIncomeBetween(LocalDate start, LocalDate end) {
        List<Income> accumulatedExpense =
                incomes.stream()
                        .filter(item -> (item.getDate().isAfter(start) || item.getDate().isEqual(start))
                                && (item.getDate().isBefore(end) || item.getDate().isEqual(end)))
                        .collect(Collectors.toList());
        double count = 0;
        for (Income o: accumulatedExpense) {
            count += o.getValue();
        }
        return count;
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
