package service;

import entity.Budget;
import entity.BudgetList;
import terminal.Ui;

import java.util.ArrayList;

public class BudgetManager {

    public static void addBudget(double budgetValue) {
        Budget newBudget = new Budget(budgetValue);
        BudgetList.addBudget(newBudget);
    }

    public static void deleteBudget() {
        BudgetList.deleteBudget();
    }

    public static void listBudgets() {
        Ui ui = Ui.getUi();
        String budgetListHeader = String.format("%s |  %-10s |", "Id.", "Value");

        ui.printMessage(budgetListHeader);
        ArrayList<Budget> budgets = BudgetList.getBudgets();
        for (int i = 0; i < budgets.size(); i++) {
            ui.printMessage((i + 1) + ".  | " + budgets.get(i));
        }
    }
}
