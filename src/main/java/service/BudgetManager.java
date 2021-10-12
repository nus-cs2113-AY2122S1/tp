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
        String budgetListHeader = "Id.\t| Value\t|";

        ui.printMessage(budgetListHeader);
        ArrayList<Budget> budgets = BudgetList.getBudgets();
        for (int i = 0; i < budgets.size(); i++) {
            ui.printMessage((i + 1) + ". \t| " + budgets.get(i));
        }
    }
}
