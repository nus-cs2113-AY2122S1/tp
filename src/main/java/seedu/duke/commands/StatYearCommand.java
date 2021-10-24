package seedu.duke.commands;

public class StatYearCommand extends StatCommand {
    private int type = 0;

    public StatYearCommand(int type) {
        this.type = type;
    }

    public void drawVerticalPercentatge(double[] barPercentage) {
        System.out.println("Percentage of Money Spent");
        System.out.println("     JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC ");
        for (int i = 10; i > 0; i--) {
            if (i == 10) {
                System.out.print("100% ");
            } else {
                System.out.print((i * 10) + "%  ");
            }
            for (int j = 0; j < 12; j++) {
                if ((i * 10) < barPercentage[j]) {
                    System.out.print(" #  ");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void execute(boolean isLoadingStorage) {
        double totalSpending = 0.0;
        double amount = 0.0;

        double[] barPercentage;
        barPercentage = new double[12];

        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j < recordList.getExpenditureListSize(i); j += 1) {
                totalSpending += recordList.getExpenditure(j, i).getAmount();
            }
            amount = recordList.getBudget(i).getAmount();

            if (amount != 0) {
                barPercentage[i - 1] = (totalSpending / amount) * 100;
            } else {
                barPercentage[i - 1] = 0;
            }

        }

        drawVerticalPercentatge(barPercentage);
    }
}
