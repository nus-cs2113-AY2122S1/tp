package seedu.duke.commands;

import seedu.duke.ui.TextUi;

public class StatYearCommand extends StatCommand {
    private int type = 0;

    public StatYearCommand(int type) {
        this.type = type;
    }


    @Override
    public void execute(boolean isLoadingStorage) {
        double totalSpending = 0.0;
        double amount = 0.0;

        double[] barPercentage;
        barPercentage = new double[12];

        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j < allRecordList.getExpenditureListSize(i); j += 1) {
                totalSpending += allRecordList.getExpenditure(j, i).getAmount();
            }
            amount = allRecordList.getBudget(i).getAmount();

            if (amount != 0) {
                barPercentage[i - 1] = (totalSpending / amount) * 100;
            } else {
                barPercentage[i - 1] = 0;
            }

        }

        TextUi.drawVerticalPercentage(barPercentage);
    }
}
