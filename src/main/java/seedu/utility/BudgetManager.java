package seedu.utility;

import seedu.budget.BillsBudget;
import seedu.budget.Budget;
import seedu.budget.EntertainmentBudget;
import seedu.budget.FoodBudget;
import seedu.budget.MedicalBudget;
import seedu.budget.MiscBudget;
import seedu.budget.OverallBudget;
import seedu.budget.TransportBudget;
import seedu.entry.Expense;

import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetManager {
    private double threshold;
    OverallBudget overallBudget = new OverallBudget(5000);
    FoodBudget foodBudget = new FoodBudget(2000);
    TransportBudget transportBudget = new TransportBudget(0);
    MedicalBudget medicalBudget = new MedicalBudget(0);
    BillsBudget billsBudget = new BillsBudget(0);
    EntertainmentBudget entertainmentBudget = new EntertainmentBudget(100);
    MiscBudget miscBudget = new MiscBudget(0);

    public BudgetManager() {
        this.threshold = 0.1;
    }

    public void handleBudget(Expense expense, ArrayList<Expense> expenses) {
        checkBudget(expense, expenses, overallBudget);

        switch (expense.getCategory()) {
        case FOOD:
            checkBudget(expense, expenses, foodBudget);
            break;
        case TRANSPORT:
            checkBudget(expense, expenses, transportBudget);
            break;
        case MEDICAL:
            checkBudget(expense, expenses, medicalBudget);
            break;
        case BILLS:
            checkBudget(expense, expenses, billsBudget);
            break;
        case ENTERTAINMENT:
            checkBudget(expense, expenses, entertainmentBudget);
            break;
        case MISC:
            checkBudget(expense, expenses, miscBudget);
            break;
        }
    }

    private void checkBudget(Expense expense, ArrayList<Expense> expenses, Budget budget) {
        if (budget.getLimit() != 0) {
            String month = LocalDate.now().getMonth().toString();
            int currAmount = budget.calAmount(expenses);
            int limit = budget.getLimit();
            int diff = limit - currAmount;
            if ((diff < threshold*limit) & (diff > 0)) {
                System.out.println("You are almost reaching the " + month + " " + budget.getName() + " budget: $" + currAmount + "/$" + limit);
                System.out.println("Would you like to readjust your " + month + " " + budget.getName() + " budget?");
            } else if (diff < 0) {
                System.out.println("You have exceeded the " + month + " " + budget.getName() + " budget: $" + currAmount + "/$" + limit);
                System.out.println("Would you like to readjust your " + month + " " + budget.getName() + " budget?");
            }
        }
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void setBudget(int amount, Budget budget) {
        budget.setLimit(amount);
    }
}
