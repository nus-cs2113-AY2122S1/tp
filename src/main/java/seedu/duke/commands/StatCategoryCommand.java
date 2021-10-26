package seedu.duke.commands;

import seedu.duke.data.records.Category;
import seedu.duke.data.records.Expenditure;
import seedu.duke.data.records.Record;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;

import static java.lang.Math.round;

public class StatCategoryCommand extends StatCommand {
    int month;

    public StatCategoryCommand(int month) {
        this.month = month;
    }

    public void execute(boolean isLoadingStorage) {

        int numberOfCategories = Category.values().length;
        double[] barPercentage;
        barPercentage = new double[numberOfCategories];
        double monthSpending = allRecordList.getTotalAmountSpent(month);

        for (Category category: Category.values()) {
            String categoryString = category.toString();
            int categoryIndex = category.ordinal();
            double categorySpending = 0.0;
            categorySpending = allRecordList.getCategorySpending(month, categoryString, categorySpending);

            if (categorySpending != 0) {
                barPercentage[categoryIndex] = round((categorySpending / monthSpending) * 100);
            } else {
                barPercentage[categoryIndex] = 0;
            }
        }

        TextUi.drawVerticalPercentage(barPercentage, "category");

    }

}
