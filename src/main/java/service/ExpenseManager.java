package service;

import entity.Expense;
import entity.ExpenseList;
import terminal.Ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpenseManager {

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
        Ui ui = Ui.getUi();
        String expenseListHeader = String.format("%s | %-25s | %-10s | %s", "Id.", "Name", "Value", "Date");

        ui.printMessage(expenseListHeader);
        ArrayList<Expense> expenses = ExpenseList.getExpenses();
        for (int i = 0; i < expenses.size(); i++) {
            ui.printMessage((i + 1) + ". \t| " + expenses.get(i));
        }
    }
}
