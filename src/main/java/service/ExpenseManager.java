package service;

import entity.Expense;
import entity.ExpenseList;
import terminal.Ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpenseManager implements LoadableManager{

    private static ExpenseManager expenseMgr;

    private ExpenseManager() {
    }

    public static ExpenseManager getExpenseMgr() {
        if (expenseMgr == null) {
            expenseMgr = new ExpenseManager();
        }
        return expenseMgr;
    }
    public void addExpense(String expenseName, double expenseValue) {
        Format f = new SimpleDateFormat("dd/MM/yy");
        String today = f.format(new Date());
        Expense newExpense = new Expense(expenseName, expenseValue, today);
        ExpenseList.addExpense(newExpense);
    }

    public void deleteExpense(String expenseName) {
        ExpenseList.deleteExpense(expenseName);
    }

    public void deleteExpense(int expenseNumber) {
        ExpenseList.deleteExpense(expenseNumber - 1);
    }

    public void listExpenses() {
        Ui ui = Ui.getUi();
        String expenseListHeader = String.format("%s | %-25s | %-10s | %s", "Id.", "Name", "Value", "Date");

        ui.printMessage(expenseListHeader);
        ArrayList<Expense> expenses = ExpenseList.getExpenses();
        for (int i = 0; i < expenses.size(); i++) {
            ui.printMessage((i + 1) + ". \t| " + expenses.get(i));
        }
    }

    @Override
    public void parse(String fileString) {

    }

    @Override
    public void toFileString() {

    }
}
