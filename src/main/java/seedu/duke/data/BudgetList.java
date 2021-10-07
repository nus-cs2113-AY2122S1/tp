package seedu.duke.data;

import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Record;

import java.util.ArrayList;

public class BudgetList {
    public static int numberOfRecords = 0;
    private ArrayList<Record> allRecords;

    public BudgetList() {
        allRecords = new ArrayList<>();
    }

    public void addBudget(String description, double spendingLimit, int month) {
        allRecords.add(new Budget(description, spendingLimit, month));
        numberOfRecords += 1;
    }

    public int getSize() {
        return allRecords.size();
    }

    public void printRecord(int i) {
    }

    public void addBudgetList(String description, double spendingLimit, int month) {
    }
}
