package seedu.budgettracker.logic.commands;

import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.ui.TextUi;


public class StatCategoryCommand extends StatCommand {
    int month;

    public static final String MESSAGE_USAGE = "Display the statistics for a particular month’s "
            + "budget and expenditure by categories.\n"
            + "Parameters: -c m/MONTH";

    public StatCategoryCommand(int month) {
        this.month = month;
    }

    public void execute() {
        int numberOfCategories = Category.values().length;
        double[] categoryPercentage;
        categoryPercentage = new double[numberOfCategories];
        double monthSpending = allRecordList.getTotalAmountSpent(month);
        double monthBudget = allRecordList.getBudget(month).getAmount();

        getCategoryPercentage(categoryPercentage, monthSpending);
        String topCategory = getTopCategory();
        double topCategorySpending = getTopCategorySpending();

        TextUi.displayStats(month, categoryPercentage, monthBudget, topCategory, topCategorySpending);
    }

    private void getCategoryPercentage(double[] barPercentage, double monthSpending) {
        for (Category category: Category.values()) {
            String categoryString = category.toString();
            int categoryIndex = category.ordinal();
            double categorySpending;
            categorySpending = allRecordList.getCategorySpending(month, categoryString);
            if (categorySpending != 0) {
                barPercentage[categoryIndex] = (categorySpending / monthSpending) * 100;
            } else {
                barPercentage[categoryIndex] = 0;
            }
        }
    }

    private String getTopCategory() {
        String topCategory = "";
        double topCategorySpending = 0;
        for (Category category: Category.values()) {
            String categoryString = category.toString();
            double categorySpending;
            categorySpending = allRecordList.getCategorySpending(month, categoryString);

            if (categorySpending >= topCategorySpending) {
                topCategory = categoryString;
                topCategorySpending = categorySpending;
            }
        }
        return topCategory;
    }

    private double getTopCategorySpending() {
        double topCategorySpending = 0;
        for (Category category: Category.values()) {
            String categoryString = category.toString();
            double categorySpending;
            categorySpending = allRecordList.getCategorySpending(month, categoryString);

            if (categorySpending > topCategorySpending) {
                topCategorySpending = categorySpending;
            }
        }
        return topCategorySpending;
    }

}
