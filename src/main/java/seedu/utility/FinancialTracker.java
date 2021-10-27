package seedu.utility;

import seedu.commands.general.CurrencyType;
import seedu.entry.Entry;
import seedu.entry.Expense;
import seedu.entry.Income;
import seedu.exceptions.ExpenseEntryNotFoundException;
import seedu.exceptions.IncomeEntryNotFoundException;
import seedu.utility.datetools.DateOperator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A Financial tracker that contains 2 separate list of income and expense entries and a net balance.
 */
public class FinancialTracker {
    private ArrayList<Expense> expenses;
    private ArrayList<Income> incomes;
    private double balance;
    private CurrencyType currency = CurrencyType.SGD;

    /**
     * Constructor for financial tracker initialises two empty ArrayList, one for expenses and one for incomes.
     */
    public FinancialTracker() {
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
    }

    /**
     * Returns balance of the financial tracker.
     *
     * @return Balance of the financial tracker.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns balance of the financial tracker. This is different from getBalance() as it ensures balance will always
     * be correct, especially when converting to different currencies.
     *
     * @return Balance of the financial tracker.
     */
    public double calculateBalance() {
        double balance = 0;
        for (Income income : incomes) {
            balance += income.getValue();
        }
        for (Expense expense : expenses) {
            balance -= expense.getValue();
        }
        return balance;
    }
    
    public void addExpense(Expense expense) {
        int expenseSize = 0;
        assert (expenseSize = expenses.size()) >= 0;
        expenses.add(expense);
        assert !expenses.isEmpty();
        assert expenses.size() > expenseSize;
        balance -= expense.getValue();
    }

    public void addIncome(Income income) {
        int incomeSize = 0;
        assert (incomeSize = incomes.size()) >= 0;
        incomes.add(income);
        assert !incomes.isEmpty();
        assert incomes.size() > incomeSize;
        balance += income.getValue();
    }

    private int indexOffset(int index) {
        return index - 1;
    }

    public Expense removeExpense(int expenseIndex) throws ExpenseEntryNotFoundException {
        try {
            Expense removedExpense =  expenses.remove(indexOffset(expenseIndex));
            balance += removedExpense.getValue();
            return removedExpense;
        } catch (IndexOutOfBoundsException e) {
            throw new ExpenseEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
        }
    }

    public Income removeIncome(int incomeIndex) throws IncomeEntryNotFoundException {
        try {
            Income removedIncome = incomes.remove(indexOffset(incomeIndex));
            balance -= removedIncome.getValue();
            return removedIncome;
        } catch (IndexOutOfBoundsException e) {
            throw new IncomeEntryNotFoundException(Messages.UNABLE_TO_DELETE_MESSAGE);
        }
    }

    public ArrayList<Expense> getExpenses() {
        assert expenses != null;
        return expenses;
    }

    public ArrayList<Income> getIncomes() {
        assert incomes != null;
        return incomes;
    }

    /**
     * Returns an ArrayList of Entry elements, which include all incomes and expenses entries in the financial tracker.
     *
     * @return ArrayList of Entry elements.
     */
    public ArrayList<Entry> getEntries() {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.addAll(getExpenses());
        entries.addAll(getIncomes());
        return entries;
    }

    public CurrencyType getCurrency() {
        assert currency != null;
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    /**
     * Returns the total expense of all expenses in the financial tracker.
     *
     * @return Total expense of all expenses in the financial tracker.
     */
    public double getTotalExpense() {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            assert expense.getValue() >= 0;
            totalExpense += expense.getValue();
            if (totalExpense >= Double.MAX_VALUE) {
                return 0;
            }
        }
        assert totalExpense >= 0;
        return totalExpense;
    }

    private double getTotalExpense(List<Expense> accumulatedExpense) {
        double totalExpense = 0;
        for (Expense expense: accumulatedExpense) {
            totalExpense += expense.getValue();
        }
        return totalExpense;
    }

    /**
     * Returns the total income of all incomes in the financial tracker.
     *
     * @return Total income of all incomes in the financial tracker.
     */
    public double getTotalIncome() {
        double totalIncome = 0;
        for (Income income : incomes) {
            assert income.getValue() >= 0;
            totalIncome += income.getValue();
        }
        assert totalIncome >= 0;
        return totalIncome;
    }

    private double getTotalIncome(List<Income> accumulatedIncome) {
        double totalIncome = 0;
        for (Income income: accumulatedIncome) {
            totalIncome += income.getValue();
        }
        return totalIncome;
    }

    /**
     * Returns total expense between two given dates.
     *
     * @param startDate Starting date (Left boundary).
     * @param endDate End Date (Right boundary).
     * @return Total expense between two given dates.
     */
    public double getExpenseBetween(LocalDate startDate, LocalDate endDate) {
        List<Expense> accumulatedExpense = expenses.stream()
                .filter(DateOperator.entryDateInRange(startDate, endDate))
                .collect(Collectors.toList());
        return getTotalExpense(accumulatedExpense);
    }


    //returns the total expense in the month. Used for data visualisation
    private double getMonthlyExpense(int inputMonth, List<Expense> yearlyExpense) {
        List<Expense> monthlyAccumulatedExpense = yearlyExpense.stream()
                .filter(DateOperator.sameEntryMonth(inputMonth))
                .collect(Collectors.toList());
        return getTotalExpense(monthlyAccumulatedExpense);
    }

    //returns a list of total expense each month for the entire year. Used to plot on graph

    public ArrayList<Double> getMonthlyExpenseBreakdown(int inputYear) {
        List<Expense> yearlyAccumulatedExpense = expenses.stream()
                .filter(DateOperator.sameEntryYear(inputYear))
                .collect(Collectors.toList());
        ArrayList<Double> monthlyBreakdown = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthlyBreakdown.add(getMonthlyExpense(i,yearlyAccumulatedExpense));
        }
        return monthlyBreakdown;
    }

    /**
     * Returns total income between two given dates.
     *
     * @param startDate Starting date (Left boundary).
     * @param endDate End Date (Right boundary).
     * @return Total income between two given dates.
     */
    public double getIncomeBetween(LocalDate startDate, LocalDate endDate) {
        List<Income> accumulatedIncome = incomes.stream()
                .filter(DateOperator.entryDateInRange(startDate, endDate))
                .collect(Collectors.toList());
        return getTotalIncome(accumulatedIncome);
    }
        
    //returns the total expense in the month. Used for data visualisation
    private double getMonthlyIncome(int inputMonth, List<Income> yearlyIncome) {
        List<Income> monthlyAccumulatedIncome = yearlyIncome.stream()
                .filter(DateOperator.sameEntryMonth(inputMonth))
                .collect(Collectors.toList());
        return getTotalIncome(monthlyAccumulatedIncome);
    }

    //returns a list of total expense each month for the entire year. Used to plot on graph
    public ArrayList<Double> getMonthlyIncomeBreakdown(int inputYear) {
        List<Income> yearlyAccumulatedIncome = incomes.stream()
                .filter(DateOperator.sameEntryYear(inputYear))
                .collect(Collectors.toList());
        ArrayList<Double> monthlyBreakdown = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthlyBreakdown.add(getMonthlyIncome(i,yearlyAccumulatedIncome));
        }
        return monthlyBreakdown;
    }

    /**
     * Returns the size of the expenses ArrayList.
     *
     * @return Size of the expenses array list.
     */
    public int getExpenseSize() {
        return expenses.size();
    }

    /**
     * Returns the size of the incomes ArrayList.
     *
     * @return Size of the incomes array list.
     */
    public int getIncomeSize() {
        return incomes.size();
    }

    /**
     * Returns true if expenses list of financial tracker is empty.
     *
     * @return Whether the expense list is empty.
     */
    public boolean isExpensesEmpty() {
        return expenses.isEmpty();
    }

    /**
     * Returns true if incomes list of financial tracker is empty.
     *
     * @return Whether the income list is empty.
     */
    public boolean isIncomesEmpty() {
        return incomes.isEmpty();
    }

    /**
     * Delete all entries from both expenses and income list in financial tracker and set balance to zero.
     */
    public void clearAllEntries() {
        expenses.clear();
        incomes.clear();
        balance = 0;
    }
}
