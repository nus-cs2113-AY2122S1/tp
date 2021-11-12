//@@author YEOWEIHNGWHYELAB

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.ui.TextUi;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_STAT_COMMAND;

public class StatYearCommand extends StatCommand {
    private final int type;

    public static final String MESSAGE_USAGE = "Display the statistics for the current database year "
            + "being worked on.\n"
            + "Parameters: -y t/[TYPE]";

    public StatYearCommand(int type) {
        this.type = type;
    }

    public double getTotalBudgetAmount() {
        double totalBudgetAmount = 0.0;

        for (int i = 1; i <= 12; i++) {
            totalBudgetAmount += allRecordList.getBudget(i).getAmount();
        }

        return totalBudgetAmount;
    }

    public double getTotalExpenditure() {
        double totalExpenditureAmount = 0.0;

        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j < allRecordList.getExpenditureListSize(i); j += 1) {
                totalExpenditureAmount += allRecordList.getExpenditure(j, i).getAmount();
            }
        }

        return totalExpenditureAmount;
    }

    public void overallStatisticsIntro() {
        double expenditureTotal = getTotalExpenditure();
        double budgetTotal = getTotalBudgetAmount();

        System.out.print("You have a total budget of: $");
        System.out.printf("%.2f", budgetTotal);
        System.out.println();

        System.out.print("You have a spent a total of: $");
        System.out.printf("%.2f", expenditureTotal);
    }

    private void typeVerticalBarGraph() {
        double amount;

        double[] barPercentage;
        barPercentage = new double[12];

        for (int i = 1; i <= 12; i++) {
            double totalSpending = 0.0;
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

    private void typeSingleOverallGraph() {
        double amount = 0.0;
        double totalSpending = 0.0;
        double barPercentage = 0.0;

        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j < allRecordList.getExpenditureListSize(i); j += 1) {
                totalSpending += allRecordList.getExpenditure(j, i).getAmount();
            }

            amount += allRecordList.getBudget(i).getAmount();
        }

        barPercentage = (totalSpending / amount) * 100;

        TextUi.drawSingleOverallGraph(barPercentage);
    }

    @Override
    public void execute() {
        switch (type) {
        case (2):
            typeSingleOverallGraph();
            break;
        case (1):
        default:
            typeVerticalBarGraph();
            break;
        }
    }
}
