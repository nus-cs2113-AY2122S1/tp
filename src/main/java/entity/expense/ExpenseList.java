package entity.expense;

import java.util.ArrayList;

public class ExpenseList {
    private static ArrayList<Expense> expenses = new ArrayList<>();

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

    public static ArrayList<Expense> getExpenses() {
        return expenses;
    }
}
