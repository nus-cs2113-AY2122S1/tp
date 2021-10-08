package seedu.duke.data.records;

import java.util.ArrayList;

public class Budget extends Record {
    public static int numberOfExpenditure = 0;
    public static ArrayList<Expenditure> expenditureArrayList = new ArrayList<>();
    public String description;
    public int month;

    public Budget(double amount, int month) {
        super(amount);
        this.month = month;
//      this.description = description;
    }

    public void addExpenditure(String description, double amount, int month) {
        expenditureArrayList.add(new Expenditure(description, amount, month));
        numberOfExpenditure += 1;
    }

    public void printExpenditureList(int indexOfExpenditure) {
        expenditureArrayList.get(indexOfExpenditure).printExpenditure();
    }

    public void printBudgetDetails() {
        System.out.println(this.description + " $" + this.amount + " Month: " + this.month);
    }
}
