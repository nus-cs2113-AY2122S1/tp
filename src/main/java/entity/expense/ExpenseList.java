package entity.expense;

import java.util.ArrayList;

public class ExpenseList {
    private static final ArrayList<Expense> expenses = new ArrayList<>();
    private static double runningExpenseValue = 0;

    public static double getRunningExpenseValue() {
        return runningExpenseValue;
    }

    public static void addRunningExpenseValue(double newExpenseValue) {
        runningExpenseValue += newExpenseValue;
    }

    public static void addExpense(Expense newExpense) {
        expenses.add(newExpense);
        runningExpenseValue += newExpense.getValue();
    }

    public static void deleteExpense(String expenseName) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getDescription().contains(expenseName)) {
                expenses.remove(i);
                runningExpenseValue -= expenses.get(i).getValue();
            }
        }
    }

    public static void deleteExpense(int expenseIndex) {
        expenses.remove(expenseIndex);
        runningExpenseValue -= expenses.get(expenseIndex).getValue();
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
