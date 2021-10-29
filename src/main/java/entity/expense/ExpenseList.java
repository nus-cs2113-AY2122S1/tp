package entity.expense;

import java.util.ArrayList;

public class ExpenseList {
    private static final ArrayList<Expense> expenses = new ArrayList<>();
    private static double runningExpenseValue = 0;

    public static double getRunningExpenseValue() {
        return runningExpenseValue;
    }

    public static void setRunningExpenseValue(double newExpenseValue) {
        runningExpenseValue = newExpenseValue;
    }

    public static void addExpense(Expense newExpense) {
        runningExpenseValue += newExpense.getValue();
        expenses.add(newExpense);
    }

    public static void deleteExpense(String expenseName) {
        for (Expense expense : expenses) {
            if (expense.getDescription().contains(expenseName)) {
                runningExpenseValue -= expense.getValue();
            }
        }
        expenses.removeIf(expense -> expense.getDescription().contains(expenseName));
    }

    public static void deleteExpense(int expenseIndex) {
        runningExpenseValue -= expenses.get(expenseIndex).getValue();
        expenses.remove(expenseIndex);
    }

    public static void updateExpense(String expenseName, double newExpenseValue) {
        for (Expense expense : expenses) {
            if (expense.getDescription().contains(expenseName)) {
                runningExpenseValue -= expense.getValue();
                runningExpenseValue += newExpenseValue;
                expense.updateValue(newExpenseValue);
            }
        }
    }

    public static void updateExpense(String expenseName, double newExpenseValue, String newCategory) {
        for (Expense expense : expenses) {
            if (expense.getDescription().contains(expenseName)) {
                runningExpenseValue -= expense.getValue();
                runningExpenseValue += newExpenseValue;
                expense.updateValue(newExpenseValue);
                expense.updateCategory(newCategory);
            }
        }
    }

    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
