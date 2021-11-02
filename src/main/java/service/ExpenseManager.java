package service;

import entity.budget.BudgetList;
import entity.expense.Expense;
import entity.expense.ExpenseList;
import terminal.Ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static constants.WarningMessage.expenseNearingBudgetWarning;
import static constants.WarningMessage.budgetNowZeroWarning;
import static constants.WarningMessage.expenseExceedBudgetWarning;

public class ExpenseManager implements LoadableManager {

    // Default Warning Amt
    private double warningAmt = 100.0;
    private static ExpenseManager expenseMgr;
    private final String fileLabel;
    private final String expenseListHeader = String.format("%-5s | %-25s | %-10s | %-8s | %-10s",
            "Id.", "Name", "Value", "Date", "Category");

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

    public void checkIfBudgetExceeded() {
        Ui ui = Ui.getUi();
        double totalBudgetValue = BudgetList.getBudgets().get(0).getValue();
        double totalExpenseValue = ExpenseList.getRunningExpenseValue();
        double currDiffToBurstBudget = totalBudgetValue - totalExpenseValue;
        if (currDiffToBurstBudget < warningAmt
                && currDiffToBurstBudget > 0) {
            ui.printMessage(expenseNearingBudgetWarning);
        } else if (currDiffToBurstBudget == 0) {
            ui.printMessage(budgetNowZeroWarning);
        } else if (currDiffToBurstBudget < 0) {
            ui.printMessage(expenseExceedBudgetWarning);
        }
    }

    public void setWarningAmt(double newWarningAmt) {
        warningAmt = newWarningAmt;
    }

    public void addExpense(String expenseName, double expenseValue) {
        Expense newExpense = new Expense(expenseName, expenseValue, getTodayDate());
        ExpenseList.addExpense(newExpense);
        checkIfBudgetExceeded();
    }

    public void addExpense(String expenseName, double expenseValue, String category) {
        Expense newExpense = new Expense(expenseName, expenseValue, getTodayDate(), category);
        ExpenseList.addExpense(newExpense);
        checkIfBudgetExceeded();
    }

    public void deleteExpense(String expenseName) {
        ExpenseList.deleteExpense(expenseName);
    }

    public void deleteExpense(int expenseNumber) {
        ExpenseList.deleteExpense(expenseNumber - 1);
    }

    public void updateExpense(String expenseName, double expenseValue) {
        ExpenseList.updateExpense(expenseName, expenseValue);
        checkIfBudgetExceeded();
    }

    public void updateExpense(String expenseName, double expenseValue, String category) {
        ExpenseList.updateExpense(expenseName, expenseValue, category);
        checkIfBudgetExceeded();
    }

    public void listExpenses() {
        Ui ui = Ui.getUi();
        ui.printMessage(expenseListHeader);
        ArrayList<Expense> expenses = ExpenseList.getExpenses();
        for (int i = 0; i < expenses.size(); i++) {
            String expenseItem = String.format("%-5s %5s", (i + 1) + ".", "| " + expenses.get(i));
            ui.printMessage(expenseItem);
        }

        String totalExpenseValue = Double.toString(ExpenseList.getRunningExpenseValue());
        String totalExpenseValuePrintInfo = String.format("%-5s %34s", "Total:", totalExpenseValue);
        ui.printMessage(totalExpenseValuePrintInfo);
    }

    public void listExpenses(String category) {
        Ui ui = Ui.getUi();
        ui.printMessage(expenseListHeader);
        ArrayList<Expense> expenses = ExpenseList.getExpenses();
        ArrayList<Expense> expenseInCategory = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().contains(category)) {
                expenseInCategory.add(expense);
            }
        }

        for (int i = 0; i < expenseInCategory.size(); i++) {
            String expenseItemInCategory = String.format("%-5s %5s", (i + 1) + ".", "| "
                    + expenseInCategory.get(i));
            ui.printMessage(expenseItemInCategory);
        }
    }

    @Override
    public void parse(String[] fileString) {
        double newRunningExpenseValue = 0;
        for (String line : fileString) {
            String[] splitLine = line.split(";");

            String name = splitLine[0];
            double value = Double.parseDouble(splitLine[1]);
            String date = splitLine[2];
            String category = splitLine[3];

            Expense expense = new Expense(name, value, date, category);
            ExpenseList.addExpense(expense);
            newRunningExpenseValue += expense.getValue();
        }
        ExpenseList.setRunningExpenseValue(newRunningExpenseValue);
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
