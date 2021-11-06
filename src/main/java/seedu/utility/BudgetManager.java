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
import seedu.entry.ExpenseCategory;
import seedu.reminder.BudgetReminder;
import seedu.reminder.BudgetSetReminder;
import seedu.reminder.DoubleExceededBudgetReminder;
import seedu.reminder.DoubleNearingBudgetReminder;
import seedu.reminder.ExceededBudgetNearingOverallReminder;
import seedu.reminder.NearingBudgetExceededOverallReminder;
import seedu.reminder.NoReminder;
import seedu.reminder.SingleExceededReminder;
import seedu.reminder.SingleNearingReminder;
import seedu.reminder.SingleReminder;
import seedu.reminder.UnableToSetBudgetReminder;
import seedu.reminder.UnableToSetOverallBudgetReminder;

import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetManager {
    private double threshold;
    private final OverallBudget overallBudget = new OverallBudget(0);
    private final FoodBudget foodBudget = new FoodBudget(0);
    private final TransportBudget transportBudget = new TransportBudget(0);
    private final MedicalBudget medicalBudget = new MedicalBudget(0);
    private final BillsBudget billsBudget = new BillsBudget(0);
    private final EntertainmentBudget entertainmentBudget = new EntertainmentBudget(0);
    private final MiscBudget miscBudget = new MiscBudget(0);

    ArrayList<Budget> budgets = new ArrayList<>();

    public BudgetManager() {
        this.threshold = 0.9;
        budgets.add(overallBudget);
        budgets.add(foodBudget);
        budgets.add(transportBudget);
        budgets.add(medicalBudget);
        budgets.add(billsBudget);
        budgets.add(entertainmentBudget);
        budgets.add(miscBudget);
    }

    public BudgetReminder handleBudget(Expense expense, ArrayList<Expense> expenses) {
        Budget budget = expenseCategoryToBudget(expense.getCategory());
        String month = LocalDate.now().getMonth().toString();
        double currBudgetAmount = budget.calAmount(expenses);
        double diff = budget.getLimit() - currBudgetAmount;
        if (isSingleActiveBudget(budget)) {
            if (isNearingLimit(diff, getThresholdLimit(diff))) {
                return new SingleNearingReminder(month, budget.getName(), currBudgetAmount, budget.getLimit());
            } else if (isExceededLimit(diff, getThresholdLimit(diff))) {
                return new SingleExceededReminder(month, budget.getName(), currBudgetAmount, budget.getLimit());
            } else {
                return new SingleReminder(month, budget.getName(), currBudgetAmount, budget.getLimit());
            }
        } else if (isActive(budget) & isActive(overallBudget)) {
            double currOverallAmount = overallBudget.calAmount(expenses);
            double overallDiff = overallBudget.getLimit() - currOverallAmount;
            if (isNearingLimit(diff, getThresholdLimit(diff))
                    & isNearingLimit(overallDiff, getThresholdLimit(overallDiff))) {
                return new DoubleNearingBudgetReminder(month, budget.getName(), currBudgetAmount, budget.getLimit(),
                        currOverallAmount, overallBudget.getLimit(), getTotalBudget(expenses));
            } else if (isExceededLimit(diff, getThresholdLimit(diff))
                    & isExceededLimit(overallDiff, getThresholdLimit(overallDiff))) {
                return new DoubleExceededBudgetReminder(month, budget.getName(), currBudgetAmount, budget.getLimit(),
                        currOverallAmount, overallBudget.getLimit(), getTotalBudget(expenses));
            } else if (isNearingLimit(diff, getThresholdLimit(diff))
                    & isExceededLimit(overallDiff, getThresholdLimit(overallDiff))) {
                return new NearingBudgetExceededOverallReminder(month, budget.getName(), currBudgetAmount,
                        budget.getLimit(), currOverallAmount, overallBudget.getLimit(), getTotalBudget(expenses));
            } else if (isExceededLimit(diff, getThresholdLimit(diff))
                    & isNearingLimit(overallDiff, getThresholdLimit(overallDiff))) {
                return new ExceededBudgetNearingOverallReminder(month, budget.getName(), currBudgetAmount,
                        budget.getLimit(), currOverallAmount, overallBudget.getLimit(), getTotalBudget(expenses));
            }
        }
        return new NoReminder();
    }

    private double getThresholdLimit(double budgetLimit) {
        return (1 - threshold) * budgetLimit;
    }

    private boolean isNearingLimit(double diff, double thresholdLimit) {
        return (diff > 0) & (diff <= thresholdLimit);
    }

    private boolean isExceededLimit(double diff, double thresholdLimit) {
        return diff <= 0;
    }

    private boolean isActive(Budget budget) {
        if (budget.getLimit() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSingleActiveBudget(Budget budget) {
        return (budget == overallBudget & isActive(budget))
                | (budget != overallBudget & isActive(budget) & !isActive(overallBudget))
                | (budget != overallBudget & !isActive(budget) & isActive(overallBudget));
    }

    public void setThreshold(double threshold) {
        assert (threshold >= 0) & (threshold <= 1);
        this.threshold = threshold;
    }

    public double getThreshold() {
        return this.threshold;
    }

    public BudgetReminder setBudget(double amount, ExpenseCategory category, ArrayList<Expense> expenses) {
        assert amount >= 0;
        assert category != ExpenseCategory.NULL;
        Budget budget = expenseCategoryToBudget(category);
        if (budget == overallBudget) {
            if (amount >= getTotalBudget(expenses)) {
                budget.setLimit(amount);
                return new BudgetSetReminder(budget.getName(), budget.calAmount(expenses),
                        budget.getLimit());
            } else {
                return new UnableToSetOverallBudgetReminder(budget.getName(),
                        budget.getLimit(), getTotalBudget(expenses));
            }
        } else {
            double newTotalBudget = getTotalBudget(expenses) + (amount - budget.getLimit());
            if (amount >= budget.calAmount(expenses)
                    & (newTotalBudget) <= overallBudget.getLimit()) {
                budget.setLimit(amount);
                return new BudgetSetReminder(budget.getName(), budget.calAmount(expenses),
                        budget.getLimit());
            } else {
                return new UnableToSetBudgetReminder(budget.getName(), budget.calAmount(expenses),
                        overallBudget.getLimit(), newTotalBudget);
            }
        }
    }

    public double getBudget(ExpenseCategory category) {
        assert category != ExpenseCategory.NULL;
        Budget budget = expenseCategoryToBudget(category);
        return budget.getLimit();
    }

    public ArrayList<Budget> getBudgets() {
        return budgets;
    }

    public double getTotalBudget(ArrayList<Expense> expenses) {
        double total = 0;
        for (Budget budget : budgets) {
            if (budget == overallBudget) {
                continue;
            }
            if (budget.getLimit() >= budget.calAmount(expenses)) {
                total += budget.getLimit();
            } else {
                total += budget.calAmount(expenses);
            }
        }
        return total;
    }

    private Budget expenseCategoryToBudget(ExpenseCategory category) {
        assert category != ExpenseCategory.NULL;
        Budget budget;
        switch (category) {
        case FOOD:
            budget = foodBudget;
            break;
        case TRANSPORT:
            budget = transportBudget;
            break;
        case MEDICAL:
            budget = medicalBudget;
            break;
        case BILLS:
            budget = billsBudget;
            break;
        case ENTERTAINMENT:
            budget = entertainmentBudget;
            break;
        case MISC:
            budget = miscBudget;
            break;
        default:
            budget = overallBudget;
            break;
        }
        return budget;
    }
}
