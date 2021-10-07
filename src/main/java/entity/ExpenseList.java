package entity;

import java.util.ArrayList;

public class ExpenseList {
    private static ArrayList<Expense> expenses = new ArrayList<>();

    public static void addExpense(Expense newExpense) {
        expenses.add(newExpense);
    }

    public void deleteExpense(int expenseIndex) {
        expenses.remove(expenseIndex);
    }

    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
