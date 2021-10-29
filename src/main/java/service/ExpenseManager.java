package service;

import entity.expense.Expense;
import entity.expense.ExpenseList;
import terminal.Ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpenseManager implements LoadableManager {

    private static ExpenseManager expenseMgr;
    private final String fileLabel;

    private ExpenseManager() {
        fileLabel = "expense";
    }

    public static ExpenseManager getExpenseMgr() {
        if (expenseMgr == null) {
            expenseMgr = new ExpenseManager();
        }
        return expenseMgr;
    }

    private String getTodayDate() {
        Format f = new SimpleDateFormat("dd/MM/yy");
        return f.format(new Date());
    }

    public void addExpense(String expenseName, double expenseValue) {
        Expense newExpense = new Expense(expenseName, expenseValue, getTodayDate());
        ExpenseList.addExpense(newExpense);
    }

    public void addExpense(String expenseName, double expenseValue, String category) {
        Expense newExpense = new Expense(expenseName, expenseValue, getTodayDate(), category);
        ExpenseList.addExpense(newExpense);
    }

    public void deleteExpense(String expenseName) {
        ExpenseList.deleteExpense(expenseName);
    }

    public void deleteExpense(int expenseNumber) {
        ExpenseList.deleteExpense(expenseNumber - 1);
    }

    public void updateExpense(String expenseName, double expenseValue) {
        ExpenseList.updateExpense(expenseName, expenseValue);
    }

    public void updateExpense(String expenseName, double expenseValue, String category) {
        ExpenseList.updateExpense(expenseName, expenseValue, category);
    }

    public void listExpenses() {
        Ui ui = Ui.getUi();
        String expenseListHeader = String.format("%s | %-25s | %-10s | %-8s | %-10s",
                "Id.", "Name", "Value", "Date", "Category");

        ui.printMessage(expenseListHeader);
        ArrayList<Expense> expenses = ExpenseList.getExpenses();
        for (int i = 0; i < expenses.size(); i++) {
            ui.printMessage((i + 1) + ". \t| " + expenses.get(i));
        }
    }

    @Override
    public void parse(String[] fileString) {
        for (String line : fileString) {
            String[] splitLine = line.split(";");

            String name = splitLine[0];
            double value = Double.parseDouble(splitLine[1]);
            String date = splitLine[2];
            String category = splitLine[3];

            Expense expense = new Expense(name, value, date, category);
            ExpenseList.addExpense(expense);
            ExpenseList.addRunningExpenseValue(expense.getValue());
        }
    }

    @Override
    public String toFileString() {
        StringBuilder fileString = new StringBuilder();
        ArrayList<Expense> expenses = ExpenseList.getExpenses();

        for (Expense expense : expenses) {
            String name = expense.getDescription();
            String value = ((Double) expense.getValue()).toString();
            String date = expense.getDate();
            String category = expense.getCategory();

            fileString.append(String.format("%s;%s;%s;%s\n", name, value, date, category));
        }

        return fileString.toString();
    }

    @Override
    public String getFileLabel() {
        return fileLabel;
    }
}
