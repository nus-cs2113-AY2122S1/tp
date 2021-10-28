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
    private String fileLabel;

    private ExpenseManager() {
        fileLabel = "expense";
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
    public void parse(String[] fileString) {
        for(String line : fileString) {
            String[] splitLine = line.split(";");

            String name = splitLine[0];
            System.out.println(line);
            Double value = Double.parseDouble(splitLine[1]);
            String date = splitLine[2];

            Expense expense = new Expense(name, value, date);
            ExpenseList.addExpense(expense);
        }
    }

    @Override
    public String toFileString() {
        String fileString = "";
        ArrayList<Expense> expenses = ExpenseList.getExpenses();

        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            String name = expense.getDescription();
            String value = ((Double)expense.getValue()).toString();
            String date = expense.getDate();

            fileString += String.format("%s;%s;%s\n", name, value, date);
        }

        return fileString;
    }

    @Override
    public String getFileLabel() {
        return fileLabel;
    }
}
