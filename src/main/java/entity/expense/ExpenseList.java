package entity.expense;

import java.util.ArrayList;

public class ExpenseList {
    private static final ArrayList<Expense> expenses = new ArrayList<>();

    public static void addExpense(Expense newExpense) {
        expenses.add(newExpense);
    }

    public static void deleteExpense(String expenseName) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getDescription().contains(expenseName)) {
                expenses.remove(i);
            }
        }
    }

    public static void deleteExpense(int expenseIndex) {
        expenses.remove(expenseIndex);
    }

    public static void updateExpense(String expenseName, double expenseValue) {
        for (Expense expense : expenses) {
            if (expense.getDescription().contains(expenseName)) {
                expense.updateValue(expenseValue);
            }
        }
    }

    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
