package seedu.utility.tools;

import seedu.entry.Expense;
import seedu.entry.Income;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static seedu.utility.FinancialTracker.TOTAL_EXPENSE_LIMIT;
import static seedu.utility.FinancialTracker.TOTAL_INCOME_LIMIT;

/**
 * This class abstracts out more complication calculations used in FinancialTracker 
 */
public abstract class FinancialCalculator {
    /**
     * Sorts an entire year's expense according to the month they are associated with
     * 
     * @param yearlyAccumulatedExpense A List of expenses that all share the same associated year
     * @return A sorted ArrayList where index 1 - 12 contains the total expense corresponding to the month Jan to Dec
     */
    public static ArrayList<Double> sortExpenseByMonth(List<Expense> yearlyAccumulatedExpense) {
        ArrayList<Double> monthlyBreakdown = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            double expenseForTheMonth = getMonthlyExpense(i, yearlyAccumulatedExpense);
            monthlyBreakdown.add(expenseForTheMonth);
        }
        return monthlyBreakdown;
    }
    
    private static double getMonthlyExpense(int inputMonth, List<Expense> yearlyExpense) {
        List<Expense> monthlyAccumulatedExpense = yearlyExpense.stream()
                .filter(DateOperator.sameEntryMonth(inputMonth))
                .collect(Collectors.toList());
        return getTotalExpenseOf(monthlyAccumulatedExpense);
    }

    /**
     * Calculate the total amount associated to all the expense in the list
     * 
     * @param accumulatedExpense A list containing all the expenses we want to sum the values of
     * @return The sum of all the expenses stored as a double
     */
    public static double getTotalExpenseOf(List<Expense> accumulatedExpense) {
        double totalExpense = 0;
        for (Expense expense: accumulatedExpense) {
            totalExpense += expense.getValue();
        }
        assert totalExpense < TOTAL_EXPENSE_LIMIT;
        return totalExpense;
    }

    /**
     * Sorts an entire year's income according to the month they are associated with
     *
     * @param yearlyAccumulatedIncome A List of incomes that all share the same associated year
     * @return A sorted ArrayList where index 1 - 12 contains the total income corresponding to the month Jan to Dec
     */
    public static ArrayList<Double> sortIncomeByMonth(List<Income> yearlyAccumulatedIncome) {
        ArrayList<Double> monthlyBreakdown = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            double incomeForTheMonth = getMonthlyIncome(i, yearlyAccumulatedIncome);
            monthlyBreakdown.add(incomeForTheMonth);
        }
        return monthlyBreakdown;
    }

    private static double getMonthlyIncome(int inputMonth, List<Income> yearlyIncome) {
        List<Income> monthlyAccumulatedIncome = yearlyIncome.stream()
                .filter(DateOperator.sameEntryMonth(inputMonth))
                .collect(Collectors.toList());
        return getTotalIncomeOf(monthlyAccumulatedIncome);
    }

    /**
     * Calculate the total amount associated to all the income in the list
     *
     * @param accumulatedIncome A list containing all the incomes we want to sum the values of
     * @return The sum of all the incomes stored as a double
     */
    public static double getTotalIncomeOf(List<Income> accumulatedIncome) {
        double totalIncome = 0;
        for (Income income: accumulatedIncome) {
            totalIncome += income.getValue();
        }
        assert totalIncome < TOTAL_INCOME_LIMIT;
        return totalIncome;
    }
}
