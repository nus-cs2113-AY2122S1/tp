package seedu.duke.commands;

public class StatYearCommand extends StatCommand {
    public void drawVerticalPercentatge(double[] barPercentage) {
        System.out.println("- - - - - - - - - - - -");

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 12; j++ ) {
                if (i < barPercentage[j]) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        System.out.println("- - - - - - - - - - - -");
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        double totalSpending = 0.0;
        double amount = 0.0;

        double barPercentage[];
        barPercentage = new double[12];

        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j < recordList.getExpenditureListSize(i); j += 1) {
                totalSpending += recordList.getExpenditure(j, i).getAmount();
            }
            amount = recordList.getBudget(i).getAmount();
            barPercentage[i] = (totalSpending / amount) * 100;
        }

        drawVerticalPercentatge(barPercentage);
    }
}
