package seedu.duke.data.records;

import seedu.duke.data.records.Expenditure;

import java.util.ArrayList;

public class Budget extends Record{
    public String description;
    public double spendingLimit;
    public int month;
    public static int numberOfExpenditure = 0;

    public static ArrayList<Expenditure> expenditureArrayList = new ArrayList<>();

    public Budget(String description, double spendingLimit, int month) {
        super(spendingLimit, month);
        this.description = description;
    }

    public void addExpenditure(String description, double amount, int month) {
        expenditureArrayList.add(new Expenditure(description, amount, month));
        numberOfExpenditure += 1;
    }

    public void printExpenditureList(int indexOfExpenditure) {
        expenditureArrayList.get(indexOfExpenditure).printExpenditure();
    }

    public void printBudgetDetails() {
        System.out.println(this.description + " $" + this.spendingLimit + " Month: " + this.month);
    }
}
