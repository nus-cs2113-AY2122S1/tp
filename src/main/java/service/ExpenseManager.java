package service;

import entity.Expense;
import entity.ExpenseList;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpenseManager {
    private static ExpenseManager expenseMgr;

    private ExpenseManager() {
    }

    public static ExpenseManager getExpenseManager() {
        if (expenseMgr == null) {
            expenseMgr = new ExpenseManager();
        }

        return expenseMgr;
    }

    public static void addExpense(String expenseName, double expenseValue) {
        Format f = new SimpleDateFormat("dd/MM/yy");
        String today = f.format(new Date());
        Expense newExpense = new Expense(expenseName, expenseValue, today);
        ExpenseList.addExpense(newExpense);
    }

    public static void deleteExpense(String expenseName) {
        ExpenseList.deleteExpense(expenseName);
    }

    public static void deleteExpense(int expenseNumber) {
        ExpenseList.deleteExpense(expenseNumber - 1);
    }

    public static void listExpenses() {
        System.out.println("No.\t| Name\t\t\t\t| Value\t| Date");
        ArrayList<Expense> expenses = ExpenseList.getExpenses();
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". \t| " + expenses.get(i));
        }
    }
}
