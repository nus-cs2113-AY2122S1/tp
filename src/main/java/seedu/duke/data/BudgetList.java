package seedu.duke.data;

import seedu.duke.data.records.Budget;
import seedu.duke.data.records.Record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BudgetList {
    public static int numberOfRecords = 0;
    private final List<Record>[] allRecords;

    /**
     * Constructor that creates a List of 13 ArrayLists, with each index representing the months.
     * 0: empty, 1: represents January, 2: represents February etc.
     */
    public BudgetList() {
        allRecords = new List[13];
        Arrays.setAll(allRecords, element -> new ArrayList<>());
    }

    public void addBudget(double spendingLimit, int month) {
        allRecords[month].add(new Budget(spendingLimit, month));
        numberOfRecords += 1;
    }

    public void deleteBudget(int month, int index) {
        allRecords[month].remove(index-1);
        numberOfRecords -= 1;
    }

    public List<Record>[] getAllRecords() {
        return allRecords;
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < 12; i++) {
            size += allRecords[i].size();
        }
        return size;
    }

    public void printRecord(int i) {
    }

    public void addBudgetList(String description, double spendingLimit, int month) {
    }
}
