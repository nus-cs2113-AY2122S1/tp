package seedu.duke.commands;

import seedu.duke.data.records.Category;
import seedu.duke.ui.TextUi;


public class StatCategoryCommand extends StatCommand {
    int month;

    public StatCategoryCommand(int month) {
        this.month = month;
    }

    public void execute(boolean isLoadingStorage) {
        int numberOfCategories = Category.values().length;
        double[] categoryPercentage;
        categoryPercentage = new double[numberOfCategories];
        double monthSpending = allRecordList.getTotalAmountSpent(month);

        getCategoryPercentage(categoryPercentage, monthSpending);
        String topCategory = getTopCategory();
        double topCategorySpending = getTopCategorySpending();

        TextUi.displayStats(categoryPercentage, topCategory, topCategorySpending);
    }

    private void getCategoryPercentage(double[] barPercentage, double monthSpending) {
        for (Category category: Category.values()) {
            String categoryString = category.toString();
            int categoryIndex = category.ordinal();
            double categorySpending = 0.0;
            categorySpending = allRecordList.getCategorySpending(month, categoryString, categorySpending);

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
            double categorySpending = 0.0;
            categorySpending = allRecordList.getCategorySpending(month, categoryString, categorySpending);

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
            double categorySpending = 0.0;
            categorySpending = allRecordList.getCategorySpending(month, categoryString, categorySpending);

            if (categorySpending > topCategorySpending) {
                topCategorySpending = categorySpending;
            }
        }
        return topCategorySpending;
    }

}
