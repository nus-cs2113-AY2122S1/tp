package seedu.duke.data.budget;

import seedu.duke.data.expenditure.Expenditure;

import java.util.ArrayList;

public class Budget {
    public String description;
    public double spendingLimit;
    public int month;
    public static int numberOfExpenditure = 0;

    public static ArrayList<Expenditure> expenditureArrayList = new ArrayList<>();

    public Budget(String description, double spendingLimit, int month) {
        this.description = description;
        this.spendingLimit = spendingLimit;
        this.month = month;
    }

    public void addExpenditure(String description, double amount) {
        expenditureArrayList.add(new Expenditure(description, amount));
        numberOfExpenditure += 1;
    }

    public void printExpenditureList(int indexOfExpenditure) {
        expenditureArrayList.get(indexOfExpenditure).printExpenditure();
    }

    public void printBudgetDetails() {
        System.out.println(this.description + " $" + this.spendingLimit + " Month: " + this.month);
    }
}
