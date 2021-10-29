package service;

import entity.budget.Budget;
import entity.budget.BudgetList;
import terminal.Ui;

import java.util.ArrayList;

public class BudgetManager implements LoadableManager {

    private static BudgetManager budgetMgr;
    private final String fileLabel;

    private BudgetManager() {
        fileLabel = "budget";
    }

    public static BudgetManager getBudgetMgr() {
        if (budgetMgr == null) {
            budgetMgr = new BudgetManager();
        }
        return budgetMgr;
    }

    public void addBudget(double budgetValue) {
        Budget newBudget = new Budget(budgetValue);
        ArrayList<Budget> budgets = BudgetList.getBudgets();
        if (budgets.size() == 0) {
            BudgetList.addBudget(newBudget);
        } else {
            double originalBudget = budgets.get(0).getValue();
            double updatedBudgetValue = originalBudget + budgetValue;
            BudgetList.deleteBudget();
            Budget updatedBudget = new Budget(updatedBudgetValue);
            BudgetList.addBudget(updatedBudget);
            Ui ui = Ui.getUi();
            ui.printMessage("You have already set a budget before, now it will be updated to " + updatedBudgetValue);
            ui.printMessage("If you mean to update your budget instead, type 'budget update -v " + budgetValue + "'");
        }
    }

    public void deleteBudget() {
        BudgetList.deleteBudget();
    }

    public void updateBudget(double budgetValue) {
        BudgetList.deleteBudget();
        Budget newBudget = new Budget(budgetValue);
        BudgetList.addBudget(newBudget);
    }

    public void listBudgets() {
        Ui ui = Ui.getUi();
        String budgetListHeader = String.format("%s |  %-10s |", "Id.", "Value");

        ui.printMessage(budgetListHeader);
        ArrayList<Budget> budgets = BudgetList.getBudgets();
        if (budgets.size() == 1) {
            ui.printMessage(1 + ".  | " + budgets.get(0));
        }
    }

    @Override
    public void parse(String[] fileString) {
        if (fileString.length != 0) {
            double budget = Double.parseDouble(fileString[0]);
            addBudget(budget);
        }
    }

    @Override
    public String toFileString() {
        if (BudgetList.getBudgets().size() != 0) {
            Budget budget = BudgetList.getBudgets().get(0);
            double budgetValue = budget.getValue();
            return Double.toString(budgetValue);
        } else {
            return "";
        }
    }

    @Override
    public String getFileLabel() {
        return fileLabel;
    }
}
